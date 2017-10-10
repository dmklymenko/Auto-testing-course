package tests.google;

import static tests.Main.getCurrentTimeStamp;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;

public class Drive extends Main{
	
	@Test(enabled = true)
    public void createDocAndCheckItInGoogleDrive(){
		String fileName = "New test doc " + getCurrentTimeStamp();
		
    	MainPage mainPage = new MainPage();
    	mainPage.goToDocs()
    			.createNewFile()
    			.fillDocWithData(fileName)
    			.goToGoogleDrive()
    			.verifyFileInListInGoogleDrive(fileName);
    }
    
    @Test(enabled = true)
    public void verifyUserNameAndEmailInGoogleDrive(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToGoogleDriveDirectly()
    			.verifyUserNameAndEmail();
    }
    
    @Test(enabled = true)
    public void verifyFilesCountInDifferentViews(){
    	MainPage mainPage = new MainPage();
    	mainPage.goToGoogleDriveDirectly()
    			.verifyFilesCountInDifferentViews();
    }
    
    @Test(enabled = true)
    public void verifyNewFolderCreation(){
    	String newFolderTitle = "New test Folder " + getCurrentTimeStamp();
    	
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.createNewFolder(newFolderTitle)
    			.verifyNewFolderCreated(newFolderTitle);
    }
    
    @Test(enabled = true)
    public void verifyMenuItemsTitles(){
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.verifyMenuItemsTitles();
    }
    
    @Test(enabled = true)
    public void verifyAddingFilesToStarredFolder(){
    	String starredFileName = "New starred file " + getCurrentTimeStamp();
    	
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.createNewDocFile(starredFileName)
    			.markFileAsStarred(starredFileName)
    			.goToStarredFilesFolder()
    			.verifyFileInStarredFolder(starredFileName);
    }
}
