package org.epam.testing.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        printResult(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printResult(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printResult(result, "SKIPPED");
    }

    private void printResult(ITestResult result, String status) {
        System.out.println(
                String.format("Test: %s \nStatus: %s", result.getMethod().getMethodName(), status)
        );
    }
}

