package utils;


import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String fileName = System.getProperty("user.dir") + File.separator +
                "screenshot" + File.separator + iTestResult.getMethod().getMethodName();
        File file = ((TakesScreenshot) DriverFactory.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aclass, Constructor constructor, Method method) {
        iTestAnnotation.setRetryAnalyzer(RetryListener.class);
    }


}
