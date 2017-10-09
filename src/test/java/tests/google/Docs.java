package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;

public class Docs extends Main{

    @Test(enabled = false)
    public void docsTest(){
        String folderName = "Test Folder";
        MainPage mainPage = new MainPage();
        mainPage.goToDocs()
                .createNewFile()
                .verifyFile();
    }
    
    @Test(enabled = false)
    public void createDocAndCheckItInGoogleDrive(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToDocs()
    			.createNewFile()
    			.fillDocWithData()
    			.goToGoogleDrive()
    			.verifyFileInListInGoogleDrive();
    }
    
    @Test(enabled = false)
    public void verifyUserNameAndEmailInGoogleDrive(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToGoogleDriveDirectly()
    			.verifyUserNameAndEmail();
    }
    
    @Test(enabled = false)
    public void verifyFilesCountInDifferentViews(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToGoogleDriveDirectly()
    			.verifyFilesCountInDifferentViews();
    }
    
    @Test(enabled = false)
    public void verifyNewFolderCreation(){
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.createNewFolder()
    			.verifyNewFolderCreated();
    }
    
    @Test
    public void verifyMenuItemsTitles(){
    	MainPage mainPage = new MainPage();
    	
    }
}
