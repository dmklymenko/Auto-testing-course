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
	
	
	@Test(enabled = true)
	public void verifyAttributesOfReceivedEmail(){
		EmailDataProvider newEmail = new EmailDataProvider();
		
		MainPage mainPage = new MainPage();
		mainPage.goToGmailDirectly()
				.createAndSendNewEmail(newEmail)
				.openReceivedEmail(newEmail.getSubject())
				.verifyAttributesOfReceivedEmail(newEmail);
	}
	
}
