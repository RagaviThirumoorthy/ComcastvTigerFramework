package TestNGPracticeTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderEg1 {
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] arr = new Object[3][2];
		arr[0][0] = "Danerys";
		arr[0][1] = "Targerian";
		arr[1][0] = "Arya";
		arr[1][1] = "Stark";
		arr[2][0] = "Jon";
		arr[2][1] = "Snow";
		
		return arr;
	}
	
	@Test(dataProvider ="getData")
	public void createContact(String fname, String lname) {
		
		System.out.println(fname+" "+lname);
	}
	

}
