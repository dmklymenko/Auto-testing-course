package pages.GooglePages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import elements.Label;
import elements.TextInput;

import static org.testng.AssertJUnit.assertTrue;
import static utils.DateTime.getCurrentTimeStamp;
import static tests.Main.getDriver;
import static tests.Main.waitInSeconds;

public class NewDocumentPage {
	
	private TextInput docTitleInput = new TextInput(By.xpath("//input[@class='docs-title-input']"));
	private TextInput docBodyInput = new TextInput(By.xpath("//span[2]/span/span"));
	private Label documentLabel = new Label(By.xpath("//body[@itemtype='http://schema.org/CreativeWork/DocumentObject']"));
	
	public NewDocumentPage fillDocWithData(String fileName) {
		docTitleInput.fillIn(fileName);
		docTitleInput.fillIn(Keys.TAB);
		waitInSeconds(2); // Ожидаем пока документ сохранится
		return new NewDocumentPage();
	}
	
    public void verifyFile(){
        waitInSeconds(2);
        assertTrue(documentLabel.isPresent());
    }

	public GoogleDrivePageMain goToGoogleDrive() {
		getDriver().get("https://drive.google.com/drive/my-drive");
		return new GoogleDrivePageMain();
	}
	
} // END -- class NewDocumentPage
