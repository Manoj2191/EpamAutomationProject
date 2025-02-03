package org.epam.testing.uicore.driver.manager;

import org.openqa.selenium.WebDriver;

public interface WebDriverManger {
    WebDriver getDriver();

    void startDriver();

    void quiteDriver();
}
