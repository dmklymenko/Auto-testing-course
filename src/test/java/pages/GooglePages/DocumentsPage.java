package pages.GooglePages;

import enums.DocumentsPageEnum;
import pageElements.Button;
import pageElements.Label;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertTrue;
import static tests.Main.waitInSeconds;

import java.sql.Driver;

public class DocumentsPage {

    private Button createNewFileButton = new Button(DocumentsPageEnum.CREATE_NEW_FILE_BUTTON.getByXpath());

    public NewDocumentPage createNewFile() {
        createNewFileButton.waitAndClick();
        return new NewDocumentPage();
    }
    
}
