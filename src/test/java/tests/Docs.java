package tests;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;

public class Docs extends Main{

    @Test(enabled = false)
    public void docsTest(){
        String folderName = "Test Folder";
        MainPage mainPage = new MainPage();
        mainPage.goToDocs()
                .createNewFile()
                .verifyFile();
    }
    
    @Test
    public void createDocAndCheckItInGoogleDrive(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToDocs()
    			.createNewFile()
    			.fillDocWithData()
    			.goToGoogleDrive()
    			.verifyFileInListInGoogleDrive();
    }
}
