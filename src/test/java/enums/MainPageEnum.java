package enums;

import org.openqa.selenium.By;

import interfaces.ISelector;
import pageElements.Button;

public enum MainPageEnum implements ISelector {
    // Buttons
	ACTIVATEKEYBOARDBUTTON("gs_ok0"),
	SEARCHBUTTON("input.lsb"),
    NAVIGATIONBUTTON("(//div/a[contains(@title, 'Google')])[1]"),
    MOREBUTTON("(//div/a[contains(@aria-label, 'Google')])[1]"),
    DOCUMENTSBUTTON("//span[contains(text(), 'Документ')]"),
    EMAILBUTTON("//span[text() = 'Почта' or text() = 'Пошта' or text() = 'Gmail']"),
    
	// TextInputs
	RECOVERYEMAILINPUT("placeholder='you@example.com'"),
	SEARCHINPUT("//input[@title = 'Поиск']");
	
	// Constructor etc.
	private String locator;
	
	private MainPageEnum(String locator){
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
