package tests;

import org.testng.annotations.Test;
import pages.MainPage;

public class Search extends Main{

    @Test
    public void searchWithKeyboard() {
        MainPage mainPage = new MainPage();
        mainPage.activateKeyboard()
                .enterSearchCriteria()
                .performSearch()
                .clickOnSearchResultLink("Что такое Selenium? / Хабрахабр");
    }
    
    @Test
    public void searchArticleAndCheckItInUserProfile(){
    	String articleTitle = "Что такое Selenium? / Хабрахабр";
    	String authorName = "";
    	
    	MainPage mainPage = new MainPage();
    	mainPage.enterSearchCriteria()
    			.performSearch()
    			.clickOnSearchResultLink(articleTitle)
    			.openAuthorProfile()
    			.verifyAuthorProfile(author)
    			.openTabWithArticles()
    			.verifyArticleInList(articleTitle);
    	
    }
} // END -- class
