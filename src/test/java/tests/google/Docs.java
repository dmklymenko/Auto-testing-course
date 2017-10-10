package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;

public class Docs extends Main{

    @Test(enabled = true)
    public void docsTest(){
        String folderName = "Test Folder";
        MainPage mainPage = new MainPage();
        mainPage.goToDocs()
                .createNewFile()
                .verifyFile();
    }
    
}
