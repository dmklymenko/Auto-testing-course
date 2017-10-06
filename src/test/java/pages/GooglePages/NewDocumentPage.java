package pages.GooglePages;

import org.openqa.selenium.By;

import elements.Label;
import elements.TextInput;

import static org.testng.AssertJUnit.assertTrue;
import static tests.Main.getCurrentTimeStamp;
//import tests.Main.getCurrentTimeStamp;
import static tests.Main.waitInSeconds;

public class NewDocumentPage {
	
	private TextInput docTitleInput = new TextInput(By.xpath("//input[@class='docs-title-input']"));
	private TextInput docBodyInput = new TextInput(By.xpath("//span[2]/span/span"));
	private Label documentLabel = new Label(By.xpath("//body[@itemtype='http://schema.org/CreativeWork/DocumentObject']"));
	
	public NewDocumentPage fillDocWithData() {
		String docTitle = "New test DOC " + getCurrentTimeStamp();
		docBodyInput.fillInWithJS("Just enjoy!", "//span[2]/span/span"); // использ. xpath textAreaInput (body гугл дока)
		docTitleInput.fillIn(docTitle);
		return new NewDocumentPage();
	}
	
    public void verifyFile(){
        waitInSeconds(2);
        assertTrue(documentLabel.isPresent());
    }

	public GoogleDrivePageMain goToGoogleDrive() {
		// реализовать логику перехода в гугл драйв
		return new GoogleDrivePageMain();
	}
	
	
	
} // END -- class NewDocumentPage
