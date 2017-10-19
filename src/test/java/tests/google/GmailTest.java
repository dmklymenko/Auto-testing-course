package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;
import utils.DateTime;
import utils.EmailDataProvider;

public class GmailTest extends Main{
	
	@Test(enabled = false)
	public void verifyCreatingAndGettingNewEmail(){
		MainPage mainPage = new MainPage();
		EmailDataProvider newEmail = new EmailDataProvider();
		
		mainPage.goToGmail()
				.createAndSendNewEmail(newEmail)
				.verifyGettingNewEmail(newEmail);
	}
	
	
	@Test(enabled = false)
	public void verifyAttributesOfReceivedEmail(){
		EmailDataProvider newEmail = new EmailDataProvider();
		
		MainPage mainPage = new MainPage();
		mainPage.goToGmailDirectly()
				.createAndSendNewEmail(newEmail)
				.openReceivedEmail(newEmail.getSubject())
				.verifyAttributesOfReceivedEmail(newEmail);
	}
	
	
	@Test(enabled = true)
	public void verifyNewEmailsFormattedAsBold(){
		EmailDataProvider newEmail_1 = new EmailDataProvider();
		EmailDataProvider newEmail_2 = new EmailDataProvider();
		
		MainPage mainPage = new MainPage();
		mainPage.goToGmailDirectly()
				.clearInbox()
				.createAndSendNewEmail(newEmail_1)
				.createAndSendNewEmail(newEmail_2)
				.verifyNewEmailsFormattedAsBold(newEmail_1, newEmail_2);
	}
	
}
