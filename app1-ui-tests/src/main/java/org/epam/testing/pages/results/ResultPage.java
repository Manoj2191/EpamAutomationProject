package org.epam.testing.pages.results;

import com.google.inject.Inject;
import org.epam.testing.pages.abstracts.AbstractPage;
import org.epam.testing.uicore.seleniumutils.DriverUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage extends AbstractPage {
    private final String PAGE_NAME = "Search Page";

    private final By RESULT_PAGE_LOCATOR = By.xpath("//span[contains(text(), \"results for\")]");
    private final By RESULT_CARD_TITLE_LOCATOR = By.xpath(
            "//div[@data-id]//span[contains(@id,\"productRating\")]//parent::div//preceding-sibling::div[1]"
    );

    @Inject
    public ResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOpened() {
        return DriverUtilities.isDisplayed(RESULT_PAGE_LOCATOR, webDriver);
    }

    @Override
    public String getPageName() {
        return PAGE_NAME;
    }

    public List<String> getResultCardTitle() {
        DriverUtilities.waitForVisibilityOfElement(RESULT_CARD_TITLE_LOCATOR, webDriver);
        return DriverUtilities.findAll(RESULT_CARD_TITLE_LOCATOR, webDriver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
