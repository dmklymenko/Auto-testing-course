package enums;

import org.openqa.selenium.By;

import elements.Button;
import interfaces.ISelector;

public enum GoogleDrivePageMainEnum implements ISelector {
	// Buttons
	ACCOUNT_POPUP("(//div/a[contains(@aria-label, 'Google')])[3]"),
	CHANGE_VIEW_BUTTON("//div[@role = 'button' and @data-tooltip-unhoverable][6]"),
	STARRED_CONTEXT_MENU_ITEM("(//div/span[2]/span/div)[8]"),
	STARRED_SIDEBAR_MENU_ITEM("(//div[@data-name = 'name']/span)[6]"),
	CREATE_NEW_ITEM_BUTTON("//div/button[@type = 'button'][1]"),
	CREATE_NEW_FOLDER_BUTTON("//span/span/div[text() = 'Папка' or text() = 'Folder']"),
	CREATE_NEW_DOC_BUTTON("//span/span/div[text() = 'Google Документы' or text() = 'Google Docs']"),
	CONFIRMATION_OK_BUTTON("//button[@name = 'ok']"),
	
	// Labels
	USER_EMAIL_LABEL("//div/a[contains(@aria-label, 'Change profile picture')]/parent::*/div/div[2]"),
	
	// TextInputs
	NEW_FOLDER_NAME_INPUT("//div/div/input");
	
	// Constructor etc.
	private String locator;
	
	private GoogleDrivePageMainEnum(String locator){
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
