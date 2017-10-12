package pages.GooglePages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import elements.Label;

public class StarredFilesPage {

	public StarredFilesPage verifyFileInStarredFolder(String fileTitleForChecking) {
		// Убеждаемся, что мы на нужной странице
		Label starredPageLabel = new Label(By.xpath("//div[text() = 'Помеченные' or contains(text(), 'Starred')]"));
		assertTrue(starredPageLabel.isPresent());
		//Проверяем наличие отмеченного элемента в списке
		Label starredFileLabel = new Label(By.xpath("//div/div/span[text()='" + fileTitleForChecking + "']"));
		assertTrue(starredFileLabel.isPresent());
		return new StarredFilesPage();
	}

}
