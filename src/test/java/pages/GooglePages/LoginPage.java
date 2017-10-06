package pages.GooglePages;

import elements.Button;
import elements.TextInput;
import org.openqa.selenium.By;

import static tests.Main.waitInSeconds;

public class LoginPage {

    private static TextInput emailInput = new TextInput(By.cssSelector("input[type='email']"));
    private TextInput passwordInput = new TextInput(By.cssSelector("input[type='password']"));
        
    private Button emailNextButton = new Button(By.xpath("//div/content/span"));
    private Button passwordNextButton = new Button(By.xpath("(//div/content/span)[2]"));

    public void login(){
        emailInput.fillIn("autopioneer1@gmail.com");
        emailNextButton.click();
        waitInSeconds(2);
        passwordInput.fillIn("123123123A");
        passwordNextButton.click();
    }


    public static boolean loginPageisShown() {
        return emailInput.isPresent();
    }
}
