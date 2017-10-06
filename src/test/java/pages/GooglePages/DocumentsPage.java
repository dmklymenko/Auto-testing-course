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

    private Button createNewFileButton = new Button(By.xpath("//*[@id=':1d']"));

    public NewDocumentPage createNewFile() {
        createNewFileButton.waitAndClick();
        return new NewDocumentPage();
    }
    
}
