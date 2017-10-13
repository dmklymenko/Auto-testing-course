package pages.GooglePages;

import org.openqa.selenium.By;

import elements.Button;
import interfaces.ISelector;

public class GmailLoginPage {
	
	private static Button signInToGmailButton = new Button(GmailLoginPageEnum.SIGN_IN_TO_GMAIL_BUTTON.getXpath());
	
	
	/* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
	public static boolean gmailLoginPageIsShown(){
		return signInToGmailButton.isPresent();
	}
	
	void clickToSignInButton(){
		signInToGmailButton.click();
	}
	
	
	/* ===============================================================
	 * ЛОКАТОРЫ
	 * ===============================================================	 */
	private enum GmailLoginPageEnum implements ISelector{
		// Buttons
		SIGN_IN_TO_GMAIL_BUTTON("//a[contains(@data-g-label, 'Sign in')]");
		
		// Labels
		
		
		// TextInputs
		
		
		// Constructor etc.
		private String locator;
		
		private GmailLoginPageEnum(String locator){
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
