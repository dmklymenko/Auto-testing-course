package pages.GooglePages;

import elements.Button;
import elements.TextInput;
import pages.Habr.SearchResultsPage;
import tests.Main;

import static pages.GooglePages.LoginPage.loginPageisShown;
import static tests.Main.getDriver;
import static tests.Main.waitInSeconds;

import org.openqa.selenium.By;

public class MainPage {

    private Button activateKeyboardButton = new Button(By.id("gs_ok0"));
    private Button cButton = new Button(By.id("K67"));
    private Button eButton = new Button(By.id("K84"));
    private Button lButton = new Button(By.id("K75"));
    private Button nButton = new Button(By.id("K89"));
    private Button iButton = new Button(By.id("K66"));
    private Button uButton = new Button(By.id("K69"));
    private Button mButton = new Button(By.id("K86"));
    private Button searchButton = new Button(By.cssSelector("input.lsb"));
    private Button navigationButton = new Button(By.xpath("(//div/a[contains(@title, 'Google')])[1]"));
    private Button moreButton = new Button(By.xpath("(//div/a[contains(@aria-label, 'Google')])[1]"));
    private Button doccumentsButton = new Button(By.xpath("//span[contains(text(), 'Документ')]"));

    private TextInput recoveryEmailInput = new TextInput(By.cssSelector("placeholder='you@example.com'"));
    private TextInput searchInput = new TextInput(By.xpath("//input[@title = 'Поиск']"));

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
        if(getDriver().getCurrentUrl() == "https://hangouts.google.com/"){
        	getDriver().get(new Main().getBaseUrl());
        	goToDocs();
        }
        if(loginPageisShown()){
            new LoginPage().login();
        }
        if(recoveryEmailInput.isPresent()){
            recoveryEmailInput.fillIn("sergiitst2@gmail.com");
        }
        return new DocumentsPage();
    }

	public MainPage enterSearchPhrase(String articleTitle) {
		searchInput.fillIn(articleTitle);
		return this;
	}


}
