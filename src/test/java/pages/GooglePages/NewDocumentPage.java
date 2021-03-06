package pages.GooglePages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import interfaces.ISelector;
import pageElements.Label;
import pageElements.TextInput;

import static org.testng.AssertJUnit.assertTrue;
import static utils.DateTime.getCurrentDateTimeStamp;
import static tests.Main.getDriver;
import static tests.Main.waitInSeconds;

public class NewDocumentPage {
	
	private TextInput docTitleInput = new TextInput(NewDocumentPageEnum.DOC_TITLE_INPUT.getByXpath());
	private TextInput docBodyInput = new TextInput(NewDocumentPageEnum.DOC_BODY_INPUT.getByXpath());
	
	private Label documentLabel = new Label(NewDocumentPageEnum.DOCUMENT_LABEL.getByXpath());
	
	/* ===============================================================
	 * ПРОВЕРКИ
	 * =============================================================== */
    public void verifyFile(){
        waitInSeconds(2);
        assertTrue(documentLabel.isPresent());
    }
	
    
    /* ===============================================================
	 * ДЕЙСТВИЯ С ЭЛЕМЕНТАМИ СТРАНИЦЫ И НА СТРАНИЦЕ etc.
	 * ===============================================================	 */
	public NewDocumentPage fillDocWithData(String fileName) {
		docTitleInput.fillIn(fileName);
		docTitleInput.fillIn(Keys.TAB);
		waitInSeconds(2); // Ожидаем пока документ сохранится
		return this;
	}
	
	public GoogleDrivePageMain goToGoogleDrive() {
		getDriver().get("https://drive.google.com/drive/my-drive");
		return new GoogleDrivePageMain();
	}
	
	
	/* ===============================================================
	 * ЛОКАТОРЫ
	 * ===============================================================	 */
	private enum NewDocumentPageEnum implements ISelector{
		// Buttons
		
		
		// Labels
		DOCUMENT_LABEL("//body[@itemtype='http://schema.org/CreativeWork/DocumentObject']"),
		
		// TextInputs
		DOC_TITLE_INPUT("//input[@class='docs-title-input']"),
		DOC_BODY_INPUT("//span[2]/span/span");
		
		// Constructor etc.
		private String locator;
		
		private NewDocumentPageEnum (String locator){
			this.locator = locator;
		}
		
		public String getLocator() {
			return locator;
		}
		
		public By getById() {
			return By.id(getLocator());
		}
		
		public By getByXpath() {
			return By.xpath(getLocator());
		}
		
		public By getByCssSelector() {
			return By.cssSelector(getLocator());
		}
	}
	
} // END -- class NewDocumentPage
