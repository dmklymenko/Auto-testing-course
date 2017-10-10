package pages.GooglePages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import elements.Label;

public class StarredFilesPage {

	public StarredFilesPage verifyFileInStarredFolder(String fileTitleForChecking) {		
		assertTrue(new Label(By.xpath("//div/div/span[text()='" + fileTitleForChecking + "']")).isPresent());
		return new StarredFilesPage();
	}

}
