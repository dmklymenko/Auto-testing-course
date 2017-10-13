package pages.GooglePages;

import elements.Button;
import elements.TextInput;
import interfaces.ISelector;

import static tests.Main.waitInSeconds;

import org.openqa.selenium.By;

public class LoginPage {
    private Button emailNextButton = new Button(LoginPageEnum.EMAIL_NEXT_BUTTON.getXpath());
    private Button passwordNextButton = new Button(LoginPageEnum.PASSWORD_NEXT_BUTTON.getXpath());

    private static TextInput emailInput = new TextInput(LoginPageEnum.EMAIL_INPUT.getCssSelector());
    private TextInput passwordInput = new TextInput(LoginPageEnum.PASSWORD_INPUT.getCssSelector());
    
    
    /* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
    public void login(){
        emailInput.fillIn("autopioneer1@gmail.com");
        emailNextButton.click();
        waitInSeconds(2);
        passwordInput.fillIn("123123123A");
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
