package pages.GooglePages;

import elements.Button;
import elements.TextInput;
import enums.MainPageEnum;
import pages.Habr.SearchResultsPage;
import tests.Main;

import static pages.GooglePages.LoginPage.loginPageisShown;
import static pages.GooglePages.GmailLoginPage.gmailLoginPageIsShown;
import static tests.Main.getDriver;
import static tests.Main.waitInSeconds;

import org.openqa.selenium.By;

public class MainPage {

    private Button activateKeyboardButton = new Button(MainPageEnum.ACTIVATEKEYBOARDBUTTON.getId());
    private Button cButton = new Button(By.id("K67"));
    private Button eButton = new Button(By.id("K84"));
    private Button lButton = new Button(By.id("K75"));
    private Button nButton = new Button(By.id("K89"));
    private Button iButton = new Button(By.id("K66"));
    private Button uButton = new Button(By.id("K69"));
    private Button mButton = new Button(By.id("K86"));
    private Button searchButton = new Button(MainPageEnum.SEARCHBUTTON.getCssSelector());
    private Button navigationButton = new Button(MainPageEnum.NAVIGATIONBUTTON.getXpath());
    private Button moreButton = new Button(MainPageEnum.MOREBUTTON.getXpath());
    private Button doccumentsButton = new Button(MainPageEnum.DOCUMENTSBUTTON.getXpath());
    private Button emailButton = new Button(MainPageEnum.EMAILBUTTON.getXpath());

    private TextInput recoveryEmailInput = new TextInput(MainPageEnum.RECOVERYEMAILINPUT.getXpath());
    private TextInput searchInput = new TextInput(MainPageEnum.SEARCHINPUT.getXpath());
    
    /* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
    public MainPage activateKeyboard(){
        activateKeyboardButton.click();
        return this;
    }

    public MainPage enterSearchCriteria() {
        cButton.click();
        eButton.click();
        lButton.click();
        eButton.click();
        nButton.click();
        iButton.click();
        uButton.click();
        mButton.click();
        return this;
    }

    public SearchResultsPage performSearch() {
        searchButton.click();
        return new SearchResultsPage();
    }
    
    // Переход из главной страницы в гугл документы
    public DocumentsPage goToDocs() {
        navigationButton.waitAndClick();
        waitInSeconds(1);
        moreButton.waitAndClick();
        waitInSeconds(1);
        doccumentsButton.waitAndClick();
        waitInSeconds(1);
        if(loginPageisShown()){
            new LoginPage().login();
        }
        if(recoveryEmailInput.isPresent()){
            recoveryEmailInput.fillIn("sergiitst2@gmail.com");
        }
        return new DocumentsPage();
    }
    
    // Переход из главной страницы в гугл почту
    public GmailPage goToGmail(){
    	navigationButton.waitAndClick();
        waitInSeconds(1);
        emailButton.click();
        waitInSeconds(1);
        if(gmailLoginPageIsShown()){
        	new GmailLoginPage().clickToSignInButton();
        }
        
        if(loginPageisShown()){
            new LoginPage().login();
        }
        
        return new GmailPage();
    }

	public MainPage enterSearchPhrase(String articleTitle) {
		searchInput.fillIn(articleTitle);
		return this;
	}

	public GoogleDrivePageMain goToGoogleDriveDirectly() {
		getDriver().get("https://drive.google.com/drive/my-drive");
		
		if(loginPageisShown()){
            new LoginPage().login();
        }
		
		return new GoogleDrivePageMain();
	}


}
