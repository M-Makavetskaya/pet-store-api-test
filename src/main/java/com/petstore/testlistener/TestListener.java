package com.petstore.testlistener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	Logger log = LogManager.getLogger(TestListener.class);

	public void onTestStart(ITestResult result) {
		log.info("Test has started: [" + result.getName() + "]");
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test passed: [" + result.getName() + "]");
	}

	public void onTestFailure(ITestResult result) {
		log.error("Test failed: [" + result.getName() + "]");
	}

	public void onTestSkipped(ITestResult result) {
		log.error("Test skipped: [" + result.getName() + "]");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
