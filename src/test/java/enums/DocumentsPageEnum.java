package enums;

import org.openqa.selenium.By;

import interfaces.ISelector;

public enum DocumentsPageEnum implements ISelector{
	// Buttons
	CREATE_NEW_FILE_BUTTON("//*[@id=':1d']");
	
	// Labels
	
	
	// TextInputs
	
	
	// Constructor etc.
		private String locator;
		
		private DocumentsPageEnum(String locator){
			this.locator = locator;
		}

		public String getLocator() {
			return locator;
		}

		public By getId() {
			return By.id(getLocator());
		}
		
		public By getXpath() {
			return By.xpath(getLocator());
		}

		public By getCssSelector() {
			return By.cssSelector(getLocator());
		}
}
