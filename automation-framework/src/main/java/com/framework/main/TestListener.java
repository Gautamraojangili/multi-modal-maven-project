package com.framework.main;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter{
	
	WebDriver driver;
	private static final Logger LOG = LogManager.getLogger(TestListener.class);
	private static List<String> failures = new ArrayList<String>();
	public static List<String> finishedCases = new ArrayList<String>();

	
	@Override
	public void onTestFailure(ITestResult tr) {

		// include author in printout if present
		String testClassName = tr.getTestClass().getName();
		String methodName = tr.getName();
		try {
			Class<?> class_ = Class.forName(testClassName);
			Method m = class_.getMethod(methodName, null);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			// do nothing
			e.printStackTrace();
		}

		LOG.info(methodName + " failure, execution time: " + ((tr
				.getEndMillis() - tr.getStartMillis()) / 1000.0) + " sec, suite: " + testClassName + "\n" + ExceptionUtils.getStackTrace(tr
						.getThrowable()));
		failures.add(methodName);
		finishedCases.add(methodName);
	}
	@Override
	public void onTestSkipped(ITestResult tr) {
		LOG.info(" " + tr.getName() + " skipped, execution time: " + ((tr.getEndMillis()
				- tr.getStartMillis()) / 1000.0) + " sec, suite: " + tr.getTestClass().getName());
		if (tr.getThrowable() != null) {
			tr.getThrowable().printStackTrace();
		}
		finishedCases.add(tr.getName());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		LOG.info(" " + tr.getName() + " success, execution time: " + ((tr.getEndMillis()
				- tr.getStartMillis()) / 1000.0) + " sec, suite: " + tr.getTestClass().getName());
		finishedCases.add(tr.getName());
	}

	@Override
	public void onTestStart(ITestResult tr) {
		LOG.info(" " + tr.getName() + " start, " + tr.getTestClass().getName());
	}

	@Override
	public void onStart(ITestContext context) {
		LOG.info(" Test suite: " + context.getSuite().getName() + " started : " + context
				.getStartDate());
	}

	@Override
	public void onFinish(ITestContext context) {
		LOG.info(" Test suite: " + context.getSuite().getName() + " finished: " + context
				.getEndDate());
	}

}
