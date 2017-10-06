package pages.Habr;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import elements.Button;
import elements.Label;

public class AuthorProfilePage {
	
	private Label authorNameLabel = new Label(By.xpath("//div[2]/div[1]/a[2]"));
	private Button articlesTab = new Button(By.className("tabs-menu__item-text"));

	public AuthorProfilePage verifyAuthorProfile(String authorName) {
		assertTrue(authorNameLabel.getText().toLowerCase().contains(authorName.toLowerCase()));
		return this;		
	}

	public AuthorProfilePage openTabWithArticles() {
		articlesTab.click();
		return this;
	}


}
