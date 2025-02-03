package org.epam.testing.uicore.driver.factory;

import org.epam.testing.uicore.driver.config.DriverConfig;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver(DriverConfig driverConfig) {
        return new ChromeDriver(getChromeOptions(driverConfig));
    }

    private ChromeOptions getChromeOptions(DriverConfig driverConfig) {

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //options.addArguments(String.format("--window-size=%s,%s", driverConfig.getBrowserHeight(), driverConfig.getBrowserWidth()));
        options.addArguments("--start--maximized");
        return options;
    }
}
