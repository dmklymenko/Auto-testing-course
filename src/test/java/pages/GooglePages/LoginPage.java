package pages.GooglePages;

import interfaces.ISelector;
import pageElements.Button;
import pageElements.TextInput;

import static tests.Main.waitInSeconds;

import org.openqa.selenium.By;

import config.ConfigProperties;

public class LoginPage {
    private Button emailNextButton = new Button(LoginPageEnum.EMAIL_NEXT_BUTTON.getByXpath());
    private Button passwordNextButton = new Button(LoginPageEnum.PASSWORD_NEXT_BUTTON.getByXpath());

    private static TextInput emailInput = new TextInput(LoginPageEnum.EMAIL_INPUT.getByCssSelector());
    private TextInput passwordInput = new TextInput(LoginPageEnum.PASSWORD_INPUT.getByCssSelector());
    
    
    /* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
    public void login(){
        emailInput.fillIn(ConfigProperties.getEmailProperty("loginEmail"));
        emailNextButton.click();
        waitInSeconds(2);
        passwordInput.fillIn(ConfigProperties.getEmailProperty("password"));
        passwordNextButton.click();
    }

    public static boolean loginPageisShown() {
        return emailInput.isPresent();
    }
    
    
    /* ===============================================================
	 * ЛОКАТОРЫ
	 * ===============================================================	 */
    private enum LoginPageEnum implements ISelector{
		// Buttons
    	EMAIL_NEXT_BUTTON("//div/content/span"),
        PASSWORD_NEXT_BUTTON("(//content/span)[2]"),
		
		// Labels
		
		
		// TextInputs
    	EMAIL_INPUT("input[type='email']"),
        PASSWORD_INPUT("input[type='password']");
		
		// Constructor etc.
		private String locator;
		
		private LoginPageEnum (String locator){
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
