package pages.GooglePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static tests.Main.getDriver;
import static tests.Main.getCurrentTimeStamp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import elements.Button;
import elements.Label;
import elements.TextInput;

public class GoogleDrivePageMain {
	  
	private Label userEmailLabel = new Label(By.xpath("//div/a[contains(@aria-label, 'Change profile picture')]/parent::*/div/div[2]"));
	
	private TextInput newFolderNameInput = new TextInput(By.xpath("//div/div/input"));
	
	private Button accountPopup = new Button(By.xpath("(//div/a[contains(@aria-label, 'Google')])[3]"));
	private Button changeViewButton = new Button(By.xpath("//div[@role = 'button' and @data-tooltip-unhoverable][6]"));
	private Button starredContextMenuItem = new Button(By.xpath("(//div/span[2]/span/div)[8]"));
	private Button starredSidebarMenuItem = new Button(By.xpath("(//div[@data-name = 'name']/span)[6]"));
	private Button createNewItemButton = new Button(By.xpath("//div/button[@type = 'button'][1]"));
	private Button createNewFolderButton = new Button(By.xpath("//span/span/div[text() = 'Папка' or text() = 'Folder']"));
	private Button createNewDocButton = new Button(By.xpath("//span/span/div[text() = 'Google Документы' or text() = 'Google Docs']"));
	private Button confirmationOKButton = new Button(By.xpath("//button[@name = 'ok']"));
		
	/* ===============================================================
	 * Проверки
	 * =============================================================== */
	public GoogleDrivePageMain verifyFileInListInGoogleDrive(String fileTitleForChecking) {
		// В методе динамически создаем элемент для проверки
		assertTrue(new Label(By.xpath("//span[text()='" + fileTitleForChecking + "']")).isPresent());
		return new GoogleDrivePageMain();
	}

	public GoogleDrivePageMain verifyUserNameAndEmail() {
		accountPopup.click();
		assertEquals(userEmailLabel.getText(), "autopioneer1@gmail.com");
		return new GoogleDrivePageMain();
	}

	public GoogleDrivePageMain verifyFilesCountInDifferentViews() {
		// определяем количество элементов на странице в исходном виде
		List<WebElement> filesInList = getDriver().findElements(By.xpath("//div[@data-target = 'doc']"));
		int listViewNumberOfFiles = filesInList.size();
		
		// переключаем вид и проверяем, что количество элементов не изменилось
		changeViewButton.click();
		filesInList = getDriver().findElements(By.xpath("//div[@data-target = 'doc' and @data-is-doc-name]"));
		int gridViewNumberOfFiles = filesInList.size();
		
		assertEquals(gridViewNumberOfFiles, listViewNumberOfFiles);
		
		return new GoogleDrivePageMain();
	}
	
	public GoogleDrivePageMain verifyNewFolderCreated(String folderTitleForChecking) {
		assertTrue(new Label(By.xpath("//div/span[text() = '" + folderTitleForChecking + "' and @data-is-doc-name]")).isPresent());
		
		return new GoogleDrivePageMain();
	}

	public GoogleDrivePageMain verifyMenuItemsTitles() {
		// Массив эталонных значений
		String[] expectedMenuItemsTitles = {"My Drive",
											"Computers",
											"Shared with me",
											"Recent",
											"Google Photos",
											"Starred",
											"Trash",
											"Backups"};
		
		// Берем массив названий элементов меню сайдбара и проверяем соответствия
		List<WebElement> actualdMenuItemsTitles = getDriver().findElements(By.xpath("//div[@role = 'treeitem']//span"));

		// Если количество элементов разное, то сразу ошибка
		if(expectedMenuItemsTitles.length != actualdMenuItemsTitles.size()){
			fail();
		}
		else{
			for(int i = 0; i < expectedMenuItemsTitles.length; i++){
				assertEquals(actualdMenuItemsTitles.get(i).getText(), expectedMenuItemsTitles[i]);
			}
		}
		return new GoogleDrivePageMain();
	}
	
	/* ===============================================================
	 * Создание новых items (папки, файлы etc.)
	 * ===============================================================	 */
	public GoogleDrivePageMain createNewFolder(String newFolderTitle) {
		createNewItemButton.waitAndClick();
		createNewFolderButton.waitAndClick();
		newFolderNameInput.waitAndClick();
		newFolderNameInput.clear();
		newFolderNameInput.fillIn(newFolderTitle);
		confirmationOKButton.click();
		
		return new GoogleDrivePageMain();
	}
	
	public GoogleDrivePageMain createNewDocFile(String fileName) {
		createNewItemButton.waitAndClick();
		createNewDocButton.waitAndClick();
		
		// Переключаемся в новую вкладку, т.к. новый документ открывается в ней
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		NewDocumentPage newDocumentPage = new NewDocumentPage();
		newDocumentPage.fillDocWithData(fileName);
		
		// После заполнения документа закрываем вкладку с документом и возвращаемся в первую
		getDriver().close();
		getDriver().switchTo().window(tabs.get(0));
		return newDocumentPage.goToGoogleDrive();
	}
	
	/* ===============================================================
	 * Прочие действия с items
	 * ===============================================================	 */

	public GoogleDrivePageMain markFileAsStarred(String fileName) {
		// найти созданный файл в списке
		Button starredFileLabel = new Button(By.xpath("//span[text()='" + fileName + "']"));
		// Отметить файл
		starredFileLabel.rightClick();
		starredContextMenuItem.waitAndClick();
		return new GoogleDrivePageMain();
	}
	
	public StarredFilesPage goToStarredFilesFolder() {
		starredSidebarMenuItem.click();
		return new StarredFilesPage();
	}

}
