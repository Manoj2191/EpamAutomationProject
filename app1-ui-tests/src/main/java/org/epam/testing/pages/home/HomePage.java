package org.epam.testing.pages.home;

import com.google.inject.Inject;
import org.epam.testing.config.TestRunConfig;
import org.epam.testing.pages.abstracts.AbstractPage;
import org.epam.testing.pages.common.MainNavBar;
import org.epam.testing.pages.factory.BlockFactory;
import org.epam.testing.uicore.seleniumutils.DriverUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private final TestRunConfig appConfig;
    private final String PAGE_NAME = "Home Page";

    private final By HOME_PAGE_LOCATOR = By.xpath(
            "//button[@aria-label=\"Previous Slide\"]//parent::div"
    );

    @Inject
    public HomePage(WebDriver webDriver, TestRunConfig appConfig) {
        super(webDriver);
        this.appConfig = appConfig;
    }

    @Override
    public boolean isOpened() {
        return DriverUtilities.isDisplayed(HOME_PAGE_LOCATOR, webDriver);
    }

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }

    public HomePage open() {
        DriverUtilities.openPage(appConfig.getUrl(), webDriver);
        waitForPageReadyState();
        return this;
    }

    public MainNavBar onMainNavigationBar() {
        return BlockFactory.createBlock(MainNavBar.class, webDriver);
    }
}
