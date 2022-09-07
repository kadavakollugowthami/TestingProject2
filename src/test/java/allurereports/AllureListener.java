package allurereports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener
{
     private static String getTestMethodName(ITestResult iTestResult) {
    	 return iTestResult.getMethod().getConstructorOrMethod().getName();
     }
     
     @Attachment
     public byte[] saveFailureScreenShot(WebDriver driver)
     {
     
    	 return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
     }
     @Attachment(value="{0}",type="text/plain")
     public static String saveTextLog(String message) {
    	 return message;
     }
     @Override
     public void onStart(ITestContext iTestContext) {
    	 System.out.println("I am in onStart method "+ iTestContext.getName());
    	 iTestContext.setAttribute("WebDriver",BaseClass.getDriver());
     }
     @Override
     public void onFinish(ITestContext iTestContext) {
    	 System.out.println("I am in onFinish method "+ iTestContext.getName());
    	 
     }
     @Override
     public void onTestStart(ITestResult iTestResult) {
    	 System.out.println("I am in onTestStart method "+ getTestMethodName(iTestResult) + "start");
    	 
     }
     @Override
     public void onTestSuccess(ITestResult iTestResult) {
    	 System.out.println("I am in onTestSuccess method "+ getTestMethodName(iTestResult) + "succeed");
    	
     }
     @Override
     public void onTestFailure(ITestResult iTestResult) {
    	 System.out.println("I am in onTestFailure method "+ getTestMethodName(iTestResult) + "Failed");
    	 Object testClass=iTestResult.getInstance();
    	 WebDriver driver=BaseClass.getDriver();
    	 if(driver instanceof WebDriver) {
    		 System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
    		 saveFailureScreenShot(driver);
    	 }
    	 saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");    	 
     }


     
     
     
}
