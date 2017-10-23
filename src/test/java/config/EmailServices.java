package config;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;

import static tests.Main.waitInSeconds;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import pageElements.Button;

public class EmailServices {
	private static final String MAIL_STORE_PROTOCOL_PROPERTY = "mail.store.protocol";
    private static final String IMAPS = "imaps";
    private static final String HOST = "imap.gmail.com";
    private static final String PARENT_FOLDER = "[Gmail]/";
    private static final String FOLDER_TO_GET = "INBOX";
	
	
	private static Store connectToEmailBox() {
		Properties props = new Properties();
		Properties properties = System.getProperties();
		properties.setProperty(MAIL_STORE_PROTOCOL_PROPERTY, IMAPS);
		Store store = null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			   store = session.getStore(IMAPS);
			   store.connect(HOST, ConfigProperties.getEmailProperty("loginEmail"), ConfigProperties.getEmailProperty("password"));
			   
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		return store;
	  } // END -- connectToEmailBox
		   
		//Complex public methods
		    public static List<EmailData> connectAndGetAllEmailsFromFolder(String folderName) {
		        Store store = connectToEmailBox();
		        Folder folder = null;
		        List<EmailData> emailsList = null;
		        try {
		            folder = waitForEmailInFolder(store, folderName);
		            Message[] messages = getMessages(folder);			
		            emailsList = getEmailDataFromMessages(messages);
//		            removeAllMessages(store, messages, folder);
		        } catch (Exception e){
		            fail("Error working with remote email service");
		        } finally {
		            disconnectFromEmailBox(store, folder);
		        }
		        return emailsList;
		    }
		    
		    private static void disconnectFromEmailBox(Store store, Folder folder) {
		    	if (folder != null && folder.isOpen()) {
		            try {
		                folder.close(true);
		                if (store != null) {
		                    store.close();
		                }
		            } catch (javax.mail.MessagingException e) {
		                e.printStackTrace();
		            }
		        }
		    }

			private static List<EmailData> getEmailDataFromMessages(Message[] messages) throws IOException {
		    	List<EmailData> emailsList;
		        emailsList = new ArrayList<EmailData>();
		    	
		        for (int i = messages.length - 1; i < messages.length; i++) {
		        	Message msg = messages[i];
		        
			        try {
//		                CustomLogger.log("Subject: " + msg.getSubject());
//			            CustomLogger.log("From: " + msg.getFrom()[0]);
//			            CustomLogger.log("To: " + msg.getAllRecipients()[0]);
//			            CustomLogger.log("Date: " + msg.getReceivedDate());
//			            CustomLogger.log("Body: \n" + msg.getContent());
			            emailsList.add(new EmailData(msg.getSubject(), msg.getFrom()[0], msg.getAllRecipients()[0], msg.getReceivedDate(), msg.getContent()));
		            } catch (javax.mail.MessagingException e) {
		                e.printStackTrace();
		            }
		        }
		        return emailsList;
		        
			} // END -- getEmailDataFromMessages

			private static Message[] getMessages(Folder folder) {
		    	 if (!folder.isOpen()) {
		             try {
		                 folder.open(Folder.READ_WRITE);
		             } catch (javax.mail.MessagingException e) {
		                 e.printStackTrace();
		             }
		         }
		    	 
		    	 Message[] messages = null;
		    	 try {
		             messages = folder.getMessages();
		         } catch (javax.mail.MessagingException e) {
		             e.printStackTrace();
		         }
		    	 try {
		             CustomLogger.log("Number of Messages : " + folder.getMessageCount());
		         } catch (javax.mail.MessagingException e) {
		             e.printStackTrace();
		         }
		    	 return messages;
			} // END -- getMessages

			private static Folder waitForEmailInFolder(Store store, String folderName) throws MessagingException {
		        Folder workFolder = null;
		        // Wait for new email in Inbox
		        waitInSeconds(5);
		        new Button(By.xpath("(//div[contains(@class, 'G-Ni')]/div[@role = 'button'])[7]")).waitAndClick();
		        
		        try {
		            workFolder = (Folder) store.getFolder(folderName);					
		        } catch (javax.mail.MessagingException e) {
		            e.printStackTrace();
		        }

		        //  Wait for new email in Inbox
//		        int seconds = 0;
//		        try {
//		            while (workFolder.getMessageCount() == 0) {
//		                workFolder = (Folder) store.getFolder(PARENT_FOLDER + folderName);
//		                try {
//		                    Thread.sleep(1000);
//		                } catch (InterruptedException e) {
//		                    e.printStackTrace();
//		                }
//		                seconds++;
//		                if (seconds == 5) {
//		                    fail("Email has not been delivered after 5 min waiting");
//		                    break;
//		                }
//		            }
//		        } catch (javax.mail.MessagingException e) {
//		            e.printStackTrace();
//		        }
		        
		        return workFolder;
		    }

}
