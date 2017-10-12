package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;
import utils.DateTime;

public class GmailTest extends Main{
	@Test(enabled = true)
	public void verifyCreatingAndGettingNewEmail(){
		MainPage mainPage = new MainPage();
		String emailTitle = "New test Email " + DateTime.getCurrentDateTimeStamp();
		
		mainPage.goToGmail()
				.createAndSendNewEmail(emailTitle)
				.verifyGettingNewEmail(emailTitle);
	}
}
