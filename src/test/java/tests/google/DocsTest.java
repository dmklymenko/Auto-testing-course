package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;

public class DocsTest extends Main{

    @Test(enabled = true)
    public void docsTest(){
        String folderName = "Test Folder";
        MainPage mainPage = new MainPage();
        mainPage.goToDocs()
                .createNewFile()
                .verifyFile();
    }
    
}
