package environment;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DecepticonListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        restAssuredSetupInGlobal();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void restAssuredSetupInGlobal() {
        RestAssured.reset();
        RestAssured.urlEncodingEnabled = false;
    }
}
