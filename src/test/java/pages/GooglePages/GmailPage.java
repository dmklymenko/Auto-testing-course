package pages.GooglePages;

import org.openqa.selenium.By;

import elements.Button;
import elements.Label;
import elements.TextInput;
import interfaces.ISelector;

import static org.testng.Assert.assertTrue;
import static tests.Main.waitInSeconds;

public class GmailPage {
	private Label welcomePopupTitle = new Label(GmailPageEnum.WELCOME_POPUP_TITLE.getXpath());
	private Label newEmailRecipientsLabel = new Label(GmailPageEnum.NEW_EMAIL_RECIPIENTS_LABEL.getXpath());
	
	private Button welcomePopupCloseButton = new Button(GmailPageEnum.WELCOME_POPUP_CLOSE_BUTTON.getXpath());
	private Button composeButton = new Button(GmailPageEnum.COMPOSE_BUTTON.getXpath()); 
	private Button newEmailSendButton = new Button(GmailPageEnum.NEW_EMAIL_SEND_BUTTON.getXpath());
	private Button refreshButton = new Button(GmailPageEnum.REFRESH_BUTTON.getXpath());
	
	private TextInput newEmailSendToInput = new TextInput(GmailPageEnum.NEW_EMAIL_SEND_TO_INPUT.getXpath());
	private TextInput newEmailSubjectInput = new TextInput(GmailPageEnum.NEW_EMAIL_SUBJECT_INPUT.getXpath());
	private TextInput newEmailBodyInput = new TextInput(GmailPageEnum.NEW_EMAIL_BODY_INPUT.getXpath());
	
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
	
	
	/* ===============================================================
	 * ЛОКАТОРЫ
	 * ===============================================================	 */
	private enum GmailPageEnum implements ISelector{
		// Buttons
		 WELCOME_POPUP_CLOSE_BUTTON("//*[@id='close-button']"),
		 COMPOSE_BUTTON("//div[text() = 'COMPOSE']"), 
		 NEW_EMAIL_SEND_BUTTON("//div[text() = 'Send']"),
		 REFRESH_BUTTON("//div/div[@aria-label = 'Обновить' or @aria-label = 'Refresh']"),
		
		// Labels
		WELCOME_POPUP_TITLE("//*[@id='welcome-step-2']/h1"),
		NEW_EMAIL_RECIPIENTS_LABEL("//div[text() = 'To' or text() = 'Recipients']"),
		
		// TextInputs
		NEW_EMAIL_SEND_TO_INPUT("(//div/textarea)[1]"),
		NEW_EMAIL_SUBJECT_INPUT("//input[@name = 'subjectbox']"),
		NEW_EMAIL_BODY_INPUT("//div[@aria-label = 'Message Body']");
		
		// Constructor etc.
		private String locator;
		
		private GmailPageEnum (String locator){
			this.locator = locator;
		}
		
		public String getLocator() {
			return locator;
		}
		
		public By getId() {
			return By.id(getLocator());
		}
		
		public By getXpath() {
			return By.xpath(getLocator());
		}
		
		public By getCssSelector() {
			return By.cssSelector(getLocator());
		}
	}
}
