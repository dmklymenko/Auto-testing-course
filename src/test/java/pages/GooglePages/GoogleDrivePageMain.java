package pages.GooglePages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import static tests.Main.getDriver;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import elements.Button;
import elements.Label;

public class GoogleDrivePageMain {
	
	private String fileTitle;
	
	private Button accountPopup = new Button(By.xpath("(//div/a[contains(@aria-label, 'Google')])[3]"));  
	private Label userEmailLabel = new Label(By.xpath("//div/a[contains(@aria-label, 'Change profile picture')]/parent::*/div/div[2]"));
	private Button changeViewButton = new Button(By.xpath("//div[@role = 'button' and @data-tooltip-unhoverable][6]"));
		
	public GoogleDrivePageMain(){	
	}
	
	public GoogleDrivePageMain(String fileTitle){
		this.fileTitle = fileTitle;
	}
	
	public Label getSpecifiedLabelElement(String filename){
		return new Label(By.xpath("(//span[text()='" + fileTitle + "'])[2]"));
	}

	public void verifyFileInListInGoogleDrive() {
		// В методе динамически создаем элемент для проверки
		assertTrue(getSpecifiedLabelElement(fileTitle).isPresent());
	}

	public GoogleDrivePageMain verifyUserNameAndEmail() {
		accountPopup.click();
		assertEquals(userEmailLabel.getText(), "autopioneer1@gmail.com");
		return new GoogleDrivePageMain();
	}

	public void verifyFilesCountInDifferentViews() {
		// определяем количество элементов на странице в исходном виде
		List<WebElement> filesInList = getDriver().findElements(By.xpath("//div[@data-target = 'doc']"));
		int listViewNumberOfFiles = filesInList.size();
		
		// переключаем вид и проверяем, что количество элементов не изменилось
		changeViewButton.click();
		filesInList = getDriver().findElements(By.xpath("//div[@data-target = 'doc' and @data-is-doc-name]"));
		int gridViewNumberOfFiles = filesInList.size();
		
		assertEquals(gridViewNumberOfFiles, listViewNumberOfFiles);
	}
	

}
