package org.epam.testing.uicore.driver.factory;

import org.epam.testing.uicore.driver.config.DriverConfig;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver createDriver(DriverConfig driverConfig);
}
