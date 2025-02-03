package org.epam.testing.pages.abstracts;

import lombok.extern.log4j.Log4j2;
import org.epam.testing.uicore.seleniumutils.DriverUtilities;
import org.openqa.selenium.WebDriver;

@Log4j2
public abstract class AbstractPage {
    protected WebDriver webDriver;

    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public abstract boolean isOpened();

    public abstract String getPageName();

    public String getCurrentPageUrl() {
        return DriverUtilities.getPageUrl(webDriver);
    }

    public String getPageTitle() {
        return DriverUtilities.getPageTitle(webDriver);
    }

    protected void waitForPageReadyState() {
        DriverUtilities.waitForPageLoad(webDriver);
    }
}
