package tests.google;

import org.testng.annotations.Test;

import pages.GooglePages.MainPage;
import tests.Main;
import static utils.DateTime.getCurrentDateTimeStamp;

public class DriveTest extends Main{
	
	@Test(enabled = false)
    public void createDocAndCheckItInGoogleDrive(){
		String fileName = "New test doc " + getCurrentDateTimeStamp();
		
    	MainPage mainPage = new MainPage();
    	mainPage.goToDocs()
    			.createNewFile()
    			.fillDocWithData(fileName)
    			.goToGoogleDrive()
    			.verifyFileInListInGoogleDrive(fileName);
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
    	String newFolderTitle = "New test Folder " + getCurrentDateTimeStamp();
    	
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.createNewFolder(newFolderTitle)
    			.verifyNewFolderCreated(newFolderTitle);
    }
    
    @Test(enabled = false)
    public void verifyMenuItemsTitles(){
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.verifyMenuItemsTitles();
    }
    
    @Test(enabled = true)
    public void verifyAddingFilesToStarredFolder() throws InterruptedException{
    	String starredFileName = "New starred file " + getCurrentDateTimeStamp();
    	
    	MainPage mainPage = new MainPage();
    	
    	mainPage.goToGoogleDriveDirectly()
    			.createNewDocFile(starredFileName)
    			.markFileAsStarred(starredFileName)
    			.goToStarredFilesFolder()
    			.verifyFileInStarredFolder(starredFileName);
    }
}
