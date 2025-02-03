package org.epam.testing.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.epam.testing.bdd.steps", "org.epam.testing.module"},
        plugin = {"pretty"},
        tags = "@sanity or @smoke",
        dryRun = false,
        monochrome = true
)
public class RegressionTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Test(dataProvider = "scenarios", threadPoolSize = 3, retryAnalyzer = Retry.class)
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    public static class Retry implements IRetryAnalyzer {
        private int count = 0;
        private static final int MAX_RETRY_COUNT = 2;

        @Override
        public boolean retry(ITestResult result) {
            if (!result.isSuccess() && count < MAX_RETRY_COUNT) {
                count++;
                return true;
            }
            return false;
        }
    }
}
