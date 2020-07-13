package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import scripts.BaseClass;

public class Reporting extends TestListenerAdapter{
	
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extReporter;
		public ExtentTest logger;

		public void onStart(ITestContext tc) { 
			htmlReporter = new ExtentHtmlReporter("Report.html");
			extReporter = new ExtentReports();
			extReporter.attachReporter(htmlReporter);
		}

		public void onTestSuccess(ITestResult tr) {
			//Listener method to indicate passed tests in test report
			/*ExtentTest test = extReporter.createTest(tr.getMethod().getMethodName());
			test.pass("Test Passed");*/

			logger = extReporter.createTest(tr.getName());
			logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		}

		public void onTestFailure(ITestResult tr) {
			//Listener method to indicate failed tests in test report
			logger = extReporter.createTest(tr.getName());
			logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
			String src=tr.getMethod().getMethodName();
			BaseClass.failed(src);
			//**Method to capture screenshot on failure through Extent Reports Version 3 can be added here**
		}

		public void onTestSkipped(ITestResult tr) {
			//Listener method to indicate skipped tests in test report
			logger = extReporter.createTest(tr.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.YELLOW));
		}

		public void onFinish(ITestContext tc) {
			//Listener method to generate final test report
			extReporter.flush();
		}
	}

