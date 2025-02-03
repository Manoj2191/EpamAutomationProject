package org.epam.testing.uicore.seleniumutils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class DriverUtilities {
    private static final int DEFAULT_WAIT_TIMEOUT_IN_SEC = 30;
    private static final int MAX_ACTION_RETRY = 3;

    public static WebElement find(By by, WebDriver driver) {
        log.debug("Find element with locator {}", by);
        waitForPresenceOfElement(by, driver);
        return driver.findElement(by);
    }

    public static List<WebElement> findAll(By by, WebDriver driver) {
        log.debug("Finding all element with locator {}", by);
        waitForPresenceOfAllElement(by, driver);
        return driver.findElements(by);
    }

    public static void waitForPresenceOfElement(By by, WebDriver driver) {
        log.debug("Waiting for presence of element {}", by);
        try {
            createWaitInstance(driver).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element not found {}", by);
        }
    }

    public static void waitForPresenceOfAllElement(By by, WebDriver driver) {
        log.debug("Waiting for presence of all elements {}", by);
        try {
            createWaitInstance(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            log.error("Elements not found {}", by);
        }
    }

    public static void waitForVisibilityOfElement(By by, WebDriver driver) {
        log.debug("Waiting for visibility of element {}", by);
        try {
            createWaitInstance(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element not visible {}", by);
        }
    }

    public static void waitForVisibilityOfAllElement(By by, WebDriver driver) {
        log.debug("Waiting for visibility of all element {}", by);
        try {
            createWaitInstance(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            log.error("Elements not visible {}", by);
        }
    }

    public static void typeText(String text, By by, WebDriver driver) {
        boolean isSuccess = false;
        int retryCount = 0;

        while (!isSuccess && retryCount < MAX_ACTION_RETRY) {
            WebElement element = find(by, driver);

            if (element.isDisplayed()) {
                element.sendKeys(text);
                isSuccess = true;
            }

            retryCount++;
        }
    }

    public static void click(By by, WebDriver driver) {
        boolean isSuccess = false;
        int retryCount = 0;

        while (!isSuccess && retryCount < MAX_ACTION_RETRY) {
            log.debug("Trying to click element {} (attempt {})", by, retryCount);
            WebElement element = find(by, driver);

            try {
                element.click();
                isSuccess = true;
            } catch (StaleElementReferenceException e) {
                log.debug("Element is stale while clicking (attempt {})", retryCount);
            } catch (ElementClickInterceptedException e) {
                log.debug("Click is interrupted (attempt {})", retryCount);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click()", element);
                isSuccess = true;
            } catch (Exception e) {
                log.error("Unexpected exception while clicking element {} (attempt {})", by, retryCount, e);
            }

            retryCount++;

            if (retryCount >= MAX_ACTION_RETRY) {
                log.error("Max retry count reached for clicking element {}. Click failed.", by);
            }
        }
    }

    public static boolean isDisplayed(By by, WebDriver driver) {
        waitForPresenceOfElement(by, driver);
        return find(by, driver).isDisplayed();
    }

    public static String getPageUrl(WebDriver driver) {
        log.debug("Retrieving current page url...");
        String url = driver.getCurrentUrl();
        log.debug("Retrieved page url: {}", url);
        return url;
    }

    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void openPage(String url, WebDriver driver) {
        log.debug("Opening page with url: {}", url);
        driver.get(url);
    }

    public static void waitForPageLoad(WebDriver driver) {
        log.debug("Waiting for page load out...");
        try {
            createWaitInstance(driver).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String state = (String) js.executeScript("return document.readyState");
                    return state.equals("complete");
                }
            });
        } catch (Exception e) {
            log.error("Error in loading page");
        }
    }

    private static WebDriverWait createWaitInstance(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT_IN_SEC));
    }
}
