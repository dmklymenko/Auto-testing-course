package pages.GooglePages;

import org.openqa.selenium.By;

import elements.Button;

public class GmailLoginPage {
	
	private static Button signInToGmailButton = new Button(By.xpath("//a[contains(@data-g-label, 'Sign in')]"));
	
	
	
	/* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
	public static boolean gmailLoginPageIsShown(){
		return signInToGmailButton.isPresent();
	}
	
	void clickToSignInButton(){
		signInToGmailButton.click();
	}
	
}
