package utils;

import java.util.ArrayList;

import tests.Main;

public class TabsSwitcher {
	private static ArrayList<String> tabs; 	// Список открытых вкладок браузера

	public static void switchToNewOpenedTab(){
		tabs = new ArrayList<String>(Main.getDriver().getWindowHandles());
		// Переключаемся на последнюю открытую вкладку
		Main.getDriver().switchTo().window(tabs.get(tabs.size()-1));
	}

	public static void closeCurrentTabAndSwitchToPrevious(){
		// Предполагается, что текущая вкладка - это самая последняя открытая вкладка
		Main.getDriver().close();
		// Удаляем закрытую вкладку из списка и переключаемся в предыдущую вкладку
		tabs.remove(tabs.size()-1);
		Main.getDriver().switchTo().window(tabs.get(tabs.size()-1));
	}

}
