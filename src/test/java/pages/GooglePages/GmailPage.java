package pages.GooglePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import interfaces.ISelector;
import pageElements.Button;
import pageElements.Label;
import pageElements.TextInput;
import utils.EmailDataProvider;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static tests.Main.waitInSeconds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static tests.Main.getDriver;

public class GmailPage {
	private Label welcomePopupTitle = new Label(GmailPageEnum.WELCOME_POPUP_TITLE.getByXpath());
	private Label newEmailRecipientsLabel = new Label(GmailPageEnum.NEW_EMAIL_RECIPIENTS_LABEL.getByXpath());
	
	private Button welcomePopupCloseButton = new Button(GmailPageEnum.WELCOME_POPUP_CLOSE_BUTTON.getByXpath());
	private Button composeButton = new Button(GmailPageEnum.COMPOSE_BUTTON.getByXpath()); 
	private Button newEmailSendButton = new Button(GmailPageEnum.NEW_EMAIL_SEND_BUTTON.getByXpath());
	private Button refreshButton = new Button(GmailPageEnum.REFRESH_BUTTON.getByXpath());
	
	private TextInput newEmailSendToInput = new TextInput(GmailPageEnum.NEW_EMAIL_SEND_TO_INPUT.getByXpath());
	private TextInput newEmailSubjectInput = new TextInput(GmailPageEnum.NEW_EMAIL_SUBJECT_INPUT.getByXpath());
	private TextInput newEmailBodyInput = new TextInput(GmailPageEnum.NEW_EMAIL_BODY_INPUT.getByXpath());
	
	
	/* ===============================================================
	 * ПРОВЕРКИ
	 * =============================================================== */
	public GmailPage verifyGettingNewEmail(EmailDataProvider newEmail) {
		int i = 0;
		boolean emailIsReceived = false;
			// Обновляем страницу, чтобы увидеть новое входящее письмо
			do {
				i++;
				refreshButton.waitAndClick();
				waitInSeconds(1);
				if(new Label(By.xpath("//div/span/b[text() = '" + newEmail.getSubject() + "']")).isPresent()){
					emailIsReceived = true;
				}
			} while (i < 4 && !emailIsReceived); // Всего 3 цикла, т.к. неявное ожидание добавляет времени
		assertTrue(emailIsReceived);
		
		return this;
	}
	
	
	public GmailPage verifyNewEmailsFormattedAsBold(EmailDataProvider...originalUnreadEmails) {
		
		return this;
	}
	
	
	/* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */

	public GmailPage createAndSendNewEmail(EmailDataProvider email) {
		if (welcomePopupIsShown()) {
			welcomePopupCloseButton.click();
		}
		composeButton.waitAndClick();
		newEmailSendToInput.waitForElementToBeClickable();
		newEmailSendToInput.fillIn(email.getRecipient());
		newEmailSubjectInput.fillIn(email.getSubject());
		newEmailBodyInput.fillIn(email.getText());
		newEmailSendButton.click();
		return this;
	}
	
	
	public EmailPage openReceivedEmail(String emailTitle) {
		
		Label newReceivedEmailLabel = new Label(By.xpath("//div/span/b[text() = '" + emailTitle + "']"));
		// Обновляем страницу, чтобы увидеть новое входящее письмо
		int i = 0;
		do {
			
//			refreshButton.waitAndClick(); 
			getDriver().get(getDriver().getCurrentUrl());
			waitInSeconds(1);
			if(newReceivedEmailLabel.isPresent()){
				break;
			}
		} while (i < 4); // Всего 3 цикла, т.к. неявное ожидание добавляет времени
		
		newReceivedEmailLabel.waitAndClick();
		return new EmailPage();
	}
	
	
	public GmailPage clearInbox() {
		List<WebElement> emailsList = new ArrayList<WebElement>();
		boolean inboxIsEmpty = false;
		
		do {
			// Получаем список писем
			emailsList = getDriver().findElements(By.xpath("//div/table/tbody/tr[contains(@class, 'zA')]/td/div[@role='checkbox']"));
			if (emailsList == null) {
				inboxIsEmpty = true;
			} else {
				for (int i = 0; i < emailsList.size(); i++) {
					// Выделяем все письма на странице
					emailsList.get(i).click();
				}
				// Кликаем "Удалить"
				new Button(By.xpath("//div[@role='button' and @act='10']")).waitAndClick();
				inboxIsEmpty = true;
			} 
		} while (!inboxIsEmpty);
		
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
		 REFRESH_BUTTON("//div/div[@aria-label = 'Обновить' or @aria-label = 'Refresh']/div"),
		
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
		
		public By getById() {
			return By.id(getLocator());
		}
		
		public By getByXpath() {
			return By.xpath(getLocator());
		}
		
		public By getByCssSelector() {
			return By.cssSelector(getLocator());
		}
	}


}
