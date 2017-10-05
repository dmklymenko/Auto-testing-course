package pages.GooglePages;

import elements.Button;
import elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertTrue;
import static tests.Main.waitInSeconds;

import java.sql.Driver;

public class DocumentsPage {

    private Button createNewFileButton = new Button(By.xpath("//img[@src='https://ssl.gstatic.com/docs/templates/thumbnails/docs-blank_1.png']/.."));

    public NewDocumentPage createNewFile() {
        createNewFileButton.waitAndClick();
        return new NewDocumentPage();
    }
    
    // Переход из открытого гугл документа в гугл драйв
	public GoogleDrivePageMain goToGoogleDrive() {
		new ChromeDriver().get("https://drive.google.com/drive/my-drive");
		return new GoogleDrivePageMain();
	}

}
