package pages.Habr;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import pageElements.Button;
import pageElements.Label;

public class SearchResultsPage {

    private Button searchResultLink(String linkText){
        return new Button(By.linkText(linkText));
    }

    public ArticlePage clickOnSearchResultLink(String linkText) {
        searchResultLink(linkText).click();
        return new ArticlePage();
    }
}
