package pages.GooglePages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import elements.Label;

public class GoogleDrivePageMain {
	
	private String fileTitle;
		
	public GoogleDrivePageMain(){	
	}
	
	public GoogleDrivePageMain(String fileTitle){
		this.fileTitle = fileTitle;
	}
	
	public Label getSpecifiedLabelElement(String filename){
		return new Label(By.xpath("(//span[text()='" + fileTitle + "'])[2]"));
	}

	public void verifyFileInListInGoogleDrive() {
		// В методе динамически создаем элемент для проверки
		assertTrue(getSpecifiedLabelElement(fileTitle).isPresent());
	}
	

}
