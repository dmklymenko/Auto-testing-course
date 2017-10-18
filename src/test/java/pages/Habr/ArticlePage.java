package pages.Habr;

import org.openqa.selenium.By;

import pageElements.Button;

public class ArticlePage {
	
	private Button authorProfilePage(String authorProfileLink){
		return new Button(By.linkText(authorProfileLink));
	}

	public AuthorProfilePage goToAuthorProfile(String authorProfileLink){
		authorProfilePage(authorProfileLink).click();
		return new AuthorProfilePage();
	}
	
}
