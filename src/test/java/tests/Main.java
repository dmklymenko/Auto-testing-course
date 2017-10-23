package tests;

import static tests.Main.getDriver;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import config.CaptureScreenShotOnFailureListener;
import config.ConfigProperties;
import config.LoggingEventListener;

import org.openqa.selenium.*;

@Listeners(CaptureScreenShotOnFailureListener.class)
public class Main {
    private WebDriver driver;
    private static EventFiringWebDriver eDriver;
    private LoggingEventListener eventLogListener;
    private String baseUrl = ConfigProperties.getConfigProperty("baseUrl");

    @BeforeMethod
    public void setUp(){
    	// Initializing instance of Chrome WebDriver
    	System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_2_29.exe");
        ChromeOptions options = new ChromeOptions();
      options.addArguments("--lang=en_US"); // Internationalization -> Set browser language
        driver = new ChromeDriver(options);
        
        // Initializing EventFiringWebDriver using Chrome WebDriver instance
        eDriver = new EventFiringWebDriver(driver);
        
        
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventLogListener = new LoggingEventListener();
        eDriver.register(eventLogListener);
        
        eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get(baseUrl);
        maximizeScreen(eDriver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
    	stopDriver();
    }

    public static WebDriver getDriver(){
        return eDriver;
    }
    
    public static void stopDriver(){
    	eDriver.quit();
    }
    
    public String getBaseUrl(){
    	return baseUrl;
    }

    public static void waitInSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static void maximizeScreen(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point(0, 0);
        driver.manage().window().setPosition(position);
        Dimension maximizedScreenSize =
                new Dimension(screenSize.width, screenSize.height);
        driver.manage().window().setSize(maximizedScreenSize);
    }
    
    public static String getUrlStatusCode(String url){
        String result = null;
        try{
            URL address = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)address.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            result = ""+code;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
