package org.epam.testing.pages.common;

import com.google.inject.Inject;
import org.epam.testing.pages.abstracts.AbstractBlock;
import org.epam.testing.pages.factory.PageFactory;
import org.epam.testing.pages.results.ResultPage;
import org.epam.testing.uicore.seleniumutils.DriverUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.epam.testing.uicore.seleniumutils.DriverUtilities.waitForVisibilityOfAllElement;

public class MainNavBar extends AbstractBlock {
    private final By MAIN_NAV_BAR_LOCATOR = By.xpath("//form[contains(@class, \"header-form-search\")]");
    private final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@title=\"Search for Products, Brands and More\"]");
    private final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@title=\"Search for Products, Brands and More\"]");
    private final By RESULT_DROP_DOWN_RESULT_NAME_LOCATOR = By.xpath("//form[@action='/search']//a/div[text()]");

    @Inject
    public MainNavBar(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isShown() {
        return DriverUtilities.isDisplayed(MAIN_NAV_BAR_LOCATOR, webDriver);
    }

    public ResultPage searchForProduct(String productName) {
        typeTextInSearchInput(productName);
        clickSearchButton();

        return PageFactory.createPage(ResultPage.class, webDriver);
    }

    public MainNavBar typeTextInSearchInput(String text) {
        DriverUtilities.typeText(text, SEARCH_INPUT_LOCATOR, webDriver);
        return this;
    }

    public ResultPage clickSearchButton() {
        DriverUtilities.click(SEARCH_BUTTON_LOCATOR, webDriver);
        return PageFactory.createPage(ResultPage.class, webDriver);
    }

    public List<String> getResultsFromDropdown() {
        waitForVisibilityOfAllElement(RESULT_DROP_DOWN_RESULT_NAME_LOCATOR, webDriver);
        return DriverUtilities.findAll(RESULT_DROP_DOWN_RESULT_NAME_LOCATOR, webDriver)
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
