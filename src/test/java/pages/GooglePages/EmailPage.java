package pages.GooglePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import config.ConfigProperties;
import pageElements.Label;
import utils.DateTime;
import utils.EmailDataProvider;

public class EmailPage {
	private Label subject = new Label(By.xpath("//tr//div/div/h2"));
	private Label recipient = new Label(By.xpath("//h3/span[2]"));
	private Label emailText = new Label(By.xpath("//table//div/div/div/div/div/div[@class = 'Bk']"));

	
	/* ===============================================================
	 * ПРОВЕРКИ
	 * =============================================================== */
	public EmailPage verifyAttributesOfReceivedEmail(EmailDataProvider newEmail) {
		assertTrue(subject.getText().contains(newEmail.getSubject()));
		assertTrue(recipient.getText().contains(newEmail.getRecipient()));
		assertTrue(emailText.getText().contains(newEmail.getText()));
		
		return this;
	}

}
