package org.epam.testing.uicore.driver.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.epam.testing.common.utils.OwnerConfigReader;
import org.epam.testing.common.guice.CustomScopes;
import org.epam.testing.uicore.driver.config.DriverConfig;
import org.epam.testing.uicore.driver.config.UITestConfig;
import org.epam.testing.uicore.driver.constants.Browser;
import org.epam.testing.uicore.driver.manager.DefaultWebDriverManger;
import org.epam.testing.uicore.driver.manager.WebDriverManger;
import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {
    @Override
    public void configure() {
        bind(WebDriverManger.class).to(DefaultWebDriverManger.class).in(CustomScopes.THREAD_LOCAL);
    }

    @Provides
    @Singleton
    public UITestConfig provideUITestConfig() {
        return OwnerConfigReader.getConfig(UITestConfig.class);
    }

    @Provides
    @Singleton
    public DriverConfig provideDriverConfig(UITestConfig uiTestConfig) {
        DriverConfig driverConfig = new DriverConfig();
        driverConfig.setBrower(Browser.valueOf(uiTestConfig.getBrowserName().toUpperCase()));
        driverConfig.setBrowserWidth(uiTestConfig.getBrowserWidth());
        driverConfig.setBrowserHeight(uiTestConfig.getBrowserHeight());

        return driverConfig;
    }

    @Provides
    public WebDriver getWebDriver(WebDriverManger webDriverManger) {
        if (webDriverManger.getDriver() == null) {
            webDriverManger.startDriver();
            return webDriverManger.getDriver();
        }
        return webDriverManger.getDriver();
    }
}
