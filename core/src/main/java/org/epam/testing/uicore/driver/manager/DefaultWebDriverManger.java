package org.epam.testing.uicore.driver.manager;

import jakarta.inject.Inject;
import org.epam.testing.uicore.driver.config.DriverConfig;
import org.epam.testing.uicore.driver.factory.ChromeDriverFactory;
import org.epam.testing.uicore.driver.factory.DriverFactory;
import org.epam.testing.uicore.driver.factory.FireFoxDriverFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DefaultWebDriverManger implements WebDriverManger {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Inject
    DriverConfig driverConfig;

    @Override
    public WebDriver getDriver() {
        return driver.get();
    }

    @Override
    public void startDriver() {
        if (driver.get() == null) {
            DriverFactory driverFactory = createDriverFactory(driverConfig);
            driver.set(driverFactory.createDriver(driverConfig));
        }

        configureDriverSession();
    }

    @Override
    public void quiteDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private void configureDriverSession() {
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get().manage().window().maximize();
    }

    private DriverFactory createDriverFactory(DriverConfig driverConfig) {
        switch (driverConfig.getBrower()) {
            case CHROME:
                return new ChromeDriverFactory();
            case FIREFOX:
                return new FireFoxDriverFactory();
            default:
                throw new RuntimeException("Driver implimentation is not available");
        }
    }
}
