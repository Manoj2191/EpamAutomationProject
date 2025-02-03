package org.epam.testing.testngtest;

import com.google.inject.Inject;
import org.epam.testing.module.AppModules;
import org.epam.testing.uicore.driver.manager.WebDriverManger;
import org.epam.testing.uicore.driver.module.DriverModule;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

@Guice(modules = {DriverModule.class, AppModules.class})
public class BaseTest {
    private final WebDriverManger webDriverManger;

    @Inject
    protected BaseTest(WebDriverManger webDriverManger) {
        this.webDriverManger = webDriverManger;
    }

    @BeforeSuite
    public void setUp() {
        webDriverManger.startDriver();
    }

    @AfterSuite
    public void tearDown() {
        webDriverManger.quiteDriver();
    }
}
