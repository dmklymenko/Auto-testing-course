package pages.GooglePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static tests.Main.getDriver;
import static utils.DateTime.getCurrentDateTimeStamp;
import static tests.Main.switchToNewOpenedTab;
import static tests.Main.closeCurrentTabAndSwitchToPrevious;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import elements.Button;
import elements.Label;
import elements.TextInput;
import enums.GoogleDrivePageMainEnum;

public class GoogleDrivePageMain {
	private Button accountPopup = new Button(GoogleDrivePageMainEnum.ACCOUNT_POPUP.getXpath());
	private Button changeViewButton = new Button(GoogleDrivePageMainEnum.CHANGE_VIEW_BUTTON.getXpath());
	private Button starredContextMenuItem = new Button(GoogleDrivePageMainEnum.STARRED_CONTEXT_MENU_ITEM.getXpath());
	private Button starredSidebarMenuItem = new Button(GoogleDrivePageMainEnum.STARRED_SIDEBAR_MENU_ITEM.getXpath());
	private Button createNewItemButton = new Button(GoogleDrivePageMainEnum.CREATE_NEW_ITEM_BUTTON.getXpath());
	private Button createNewFolderButton = new Button(GoogleDrivePageMainEnum.CREATE_NEW_FOLDER_BUTTON.getXpath());
	private Button createNewDocButton = new Button(GoogleDrivePageMainEnum.CREATE_NEW_DOC_BUTTON.getXpath());
	private Button confirmationOKButton = new Button(GoogleDrivePageMainEnum.CONFIRMATION_OK_BUTTON.getXpath());
	  
	private Label userEmailLabel = new Label(GoogleDrivePageMainEnum.USER_EMAIL_LABEL.getXpath());
	
	private TextInput newFolderNameInput = new TextInput(GoogleDrivePageMainEnum.NEW_FOLDER_NAME_INPUT.getXpath());
		
	/* ===============================================================
	 * ПРОВЕРКИ
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
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
	
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
	
	public GoogleDrivePageMain createNewDocFile(String fileName) throws InterruptedException {
		createNewItemButton.waitAndClick();
		createNewDocButton.waitAndClick();
		Thread.sleep(1000);
		switchToNewOpenedTab();
		
		NewDocumentPage newDocumentPage = new NewDocumentPage();
		newDocumentPage.fillDocWithData(fileName);
		
		closeCurrentTabAndSwitchToPrevious();
		
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
