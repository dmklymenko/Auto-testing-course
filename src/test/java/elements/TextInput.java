package elements;

import static tests.Main.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class TextInput extends Element {

    public TextInput(By by) {
        super(by);
    }

    public void fillIn(String text) {
        composeWebElement(by).sendKeys(text);
    }
    
    public void fillIn(Keys keyboardKey) {
    	composeWebElement(by).sendKeys(keyboardKey);
	}
    
    public void fillInWithJS(String text, String xpathOfElement) {
    	// Вставляем текст в body гугл документа через JS
    	JavascriptExecutor js = (JavascriptExecutor)getDriver();
    	String jsScript = "function getElementByXpath(path) {" + 
    			  "return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;}; " + 
    			  "var text = getElementByXpath('//span[2]/span/span'); text.innerHTML = 'Inserted text in body of doc.';";
    	js.executeScript(jsScript);   	
    } // END -- fillInWithJS

	

} // END -- class TextInput
