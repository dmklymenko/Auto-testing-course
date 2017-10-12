package pages.GooglePages;

import org.openqa.selenium.By;

import elements.Button;
import elements.Label;
import elements.TextInput;

import static org.testng.Assert.assertTrue;
import static tests.Main.waitInSeconds;

public class GmailPage {
	private Label welcomePopupTitle = new Label(By.xpath("//*[@id='welcome-step-2']/h1"));
	private Label newEmailRecipientsLabel = new Label(By.xpath("//div[text() = 'To' or text() = 'Recipients']"));
	
	private Button welcomePopupCloseButton = new Button(By.xpath("//*[@id='close-button']"));
	private Button composeButton = new Button(By.xpath("//div[text() = 'COMPOSE']")); 
	private Button newEmailSendButton = new Button(By.xpath("//div[text() = 'Send']"));
	private Button refreshButton = new Button(By.xpath("//div/div[@aria-label = 'Обновить' or @aria-label = 'Refresh']"));
	
	private TextInput newEmailSendToInput = new TextInput(By.xpath("(//div/textarea)[1]"));
	private TextInput newEmailSubjectInput = new TextInput(By.xpath("//input[@name = 'subjectbox']"));
	private TextInput newEmailBodyInput = new TextInput(By.xpath("//div[@aria-label = 'Message Body']"));
	
	/* ===============================================================
	 * ПРОВЕРКИ
	 * =============================================================== */
	public GmailPage verifyGettingNewEmail(String emailTitle) {
		int i = 0;
		boolean emailIsReceived = false;
			do {
				i++;
				refreshButton.waitAndClick();
				waitInSeconds(1);
				if(new Label(By.xpath("//div/span/b[text() = '" + emailTitle + "']")).isPresent()){
					emailIsReceived = true;
				}
			} while (i < 4 && !emailIsReceived); // Всего 3 цикла, т.к. неявное ожидание добавляет времени
		assertTrue(emailIsReceived);
		
		return this;
	}
	
	/* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */

	public GmailPage createAndSendNewEmail(String emailSubject) {
		if (welcomePopupIsShown()) {
			welcomePopupCloseButton.click();
		}
		composeButton.click();
		newEmailSendToInput.waitForElementToBeClickable();
		newEmailSendToInput.fillIn("autopioneer1@gmail.com");
		newEmailSubjectInput.fillIn(emailSubject);
		newEmailBodyInput.fillIn("Lorem dolor");
		newEmailSendButton.click();
		
		return this;
	}
	
	public boolean welcomePopupIsShown(){
		return welcomePopupTitle.isPresent();
	}

}
