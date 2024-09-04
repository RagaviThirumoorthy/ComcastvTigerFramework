package TestNGPracticeTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vTiger.generic.BaseTest.BaseClass;
import com.vTiger.generic.listenerUtility.ListenerUtility;

@Listeners(ListenerUtility.class)
public class createInvoiceTest extends BaseClass{
	
	
	@Test(retryAnalyzer = com.vTiger.generic.listenerUtility.RetryAnalyzerUtility.class)
	public void createInvoice() {
		Reporter.log("createInvoice test", true);
		String actTitle = driver.getTitle();
		Assert.assertEquals("Login", actTitle);
	}
	
	@Test
	public void billInvoice() {
		Reporter.log("billInvoice test", true);
	}

}
