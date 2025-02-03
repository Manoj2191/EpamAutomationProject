package org.epam.testing.uicore.driver.factory;

import org.epam.testing.uicore.driver.config.DriverConfig;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver(DriverConfig driverConfig) {
        return new FirefoxDriver(getFirefoxOptions(driverConfig));
    }

    private FirefoxOptions getFirefoxOptions(DriverConfig driverConfig) {

        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments(String.format("--window-size=%s,%s", driverConfig.getBrowserHeight(), driverConfig.getBrowserWidth()));

        return options;
    }
}
