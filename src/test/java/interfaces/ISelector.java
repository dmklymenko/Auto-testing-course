package interfaces;

import org.openqa.selenium.By;

public interface ISelector {
	public By getId();
	public By getXpath();
	public By getCssSelector();
}
