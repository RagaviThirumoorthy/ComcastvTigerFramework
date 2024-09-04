package com.vTiger.generic.listenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerUtility implements IRetryAnalyzer {

	int count = 1;
	int upperLimit = 5;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<=upperLimit) {
			count++;
			return true;
		}
		return false;
	}

}
