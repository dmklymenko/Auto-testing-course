package elements;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tests.Main.getDriver;

import java.util.concurrent.TimeUnit;

public abstract class Element {
    protected By by;

    public Element(By by){
        this.by = by;
    }

    protected static WebElement composeWebElement(By by){
        return getDriver().findElement(by);
    }

    public void waitForElementToBeInvisible() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementToBeVisible() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToBeClickable() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitAndClick() {
//        TestCase.setImplicitlyWait(0);
//        log.info("Attempt to wait until element found " + by + " will be appeared");
        new FluentWait(getDriver())
                .withTimeout(10000, TimeUnit.MILLISECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .withMessage("Element found by " + by + " is still invisible, but should not be")
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        try{
                            getDriver().findElement(by).click();
                            return true;
                        }catch(WebDriverException ignored){
                        }
                        return false;
                    }
                });
//        TestCase.setImplicitlyWait(TestCase.DEFAULT_WAIT);
    }
    
    public void rightClick(){
    	WebElement element = composeWebElement(by);
    	
    	try {
			Actions action = new Actions(getDriver()).contextClick(element);
			action.build().perform();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
    }

    public boolean isPresent() {
        try {
            try {
                composeWebElement(by).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        }catch (StaleElementReferenceException e){
            composeWebElement(by).isDisplayed();
        }
        return true;
    }
    
    public void scrollToElement() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", composeWebElement(by));
        } catch (StaleElementReferenceException ignore) {
            //ignore this exception
        }
    }
}
