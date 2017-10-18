package pageElements;

import org.openqa.selenium.By;

public class Label extends Element {
    public Label(By by) {
        super(by);
    }
    
    public String getText(){
    	return composeWebElement(by).getText();
    }
    
} // END -- class
