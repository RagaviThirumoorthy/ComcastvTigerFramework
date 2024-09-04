package TestNGPracticeTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vTiger.generic.fileUtility.ExcelUtility;

public class DataProviderEg2 {
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		
		ExcelUtility eUtil = new ExcelUtility();
		int rowCount = eUtil.getNumberOfRows("products");
		int cellCount = eUtil.getNumberOfCells("products", 1);
		System.out.println(rowCount);
		System.out.println(cellCount);
		
		Object[][] arr = new Object[rowCount-1][cellCount];
		
		for(int i = 0; i<rowCount-1;i++) {
			for(int j=0;j<cellCount;j++) {
				arr[i][j] = eUtil.getDataFromExcel("products", i+1, j);
				System.out.println(arr[i][j]);
			}
		}
		
		return arr;
		
	}
	
	@Test(dataProvider = "getData")
	public void printPrice(String brandName, String productName) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.flipkart.com/");
		
		driver.findElement(By.name("q")).sendKeys(brandName, Keys.ENTER);
		String x = "//div[text()='"+productName+"']/../../div[contains(@class,'BfVC2z')]/descendant::div[@class='Nx9bqj _4b5DiR']";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
	}

}
