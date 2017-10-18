package interfaces;

import org.openqa.selenium.By;
/**
 * Интерфейс для Enum, которые хранят локаторы элементов страницы, предоставляя им возможность возвращать объекты By
 */
public interface ISelector {
	public By getById();
	public By getByXpath();
	public By getByCssSelector();
}
