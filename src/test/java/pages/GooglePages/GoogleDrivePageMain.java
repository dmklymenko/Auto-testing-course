package pages.GooglePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static tests.Main.getDriver;
import static tests.Main.getCurrentTimeStamp;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import elements.Button;
import elements.Label;
import elements.TextInput;

public class GoogleDrivePageMain {
	
	private String fileTitleForChecking;
	
	private Button accountPopup = new Button(By.xpath("(//div/a[contains(@aria-label, 'Google')])[3]"));  
	private Label userEmailLabel = new Label(By.xpath("//div/a[contains(@aria-label, 'Change profile picture')]/parent::*/div/div[2]"));
	private Button changeViewButton = new Button(By.xpath("//div[@role = 'button' and @data-tooltip-unhoverable][6]"));
	private Button createNewItemButton = new Button(By.xpath("//div/button[@type = 'button'][1]"));
	private Button createNewFolderButton = new Button(By.xpath("//span/span/div[text() = 'Папка' or text() = 'Folder']"));
	private TextInput newFolderInput = new TextInput(By.xpath("//div/div/input"));
	private Button confirmationOKButton = new Button(By.xpath("//button[@name = 'ok']"));
		
	public GoogleDrivePageMain(){	
	}
	
	public GoogleDrivePageMain(String fileTitle){
		this.fileTitleForChecking = fileTitle;
	}
	
	private Label getSpecifiedLabelElement(String filename){
		return new Label(By.xpath("(//span[text()='" + fileTitleForChecking + "'])[2]"));
	}

	public void verifyFileInListInGoogleDrive() {
		// В методе динамически создаем элемент для проверки
		assertTrue(getSpecifiedLabelElement(fileTitleForChecking).isPresent());
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

	public GoogleDrivePageMain createNewFolder() {
		createNewItemButton.waitAndClick();
		createNewFolderButton.waitAndClick(); // element not visible. Найти workaround
		newFolderInput.waitAndClick();
		String newFolderTitle = "New test Folder " + getCurrentTimeStamp();
		newFolderInput.clear();
		newFolderInput.fillIn(newFolderTitle);
		confirmationOKButton.click();
		
		return new GoogleDrivePageMain(newFolderTitle);
	}

	public GoogleDrivePageMain verifyNewFolderCreated() {
		assertTrue(new Label(By.xpath("//div/span[text() = '" + fileTitleForChecking + "' and @data-is-doc-name]")).isPresent());
		
		return new GoogleDrivePageMain();
	}
	

}
