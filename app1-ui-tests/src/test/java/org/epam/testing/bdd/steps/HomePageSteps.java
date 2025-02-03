package org.epam.testing.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.epam.testing.common.ScenarioContext;
import org.epam.testing.pages.home.HomePage;
import org.epam.testing.uicore.driver.manager.WebDriverManger;

import java.util.List;

public class HomePageSteps extends BaseStep {
    private final HomePage homePage;
    private final ScenarioContext scenarioContext;

    @Inject
    protected HomePageSteps(HomePage homePage, WebDriverManger webDriverManger, ScenarioContext scenarioContext) {
        super(webDriverManger);
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
    }

    @Given("user is in home page")
    public void user_is_in_home_page() {
        homePage.open();
    }

    @When("user search for {string} in search box")
    public void userSearchForInSearchBox(String product) {
        homePage.onMainNavigationBar()
                .searchForProduct(product);

        scenarioContext.setData("product", product);
    }

    @When("user type {string} name in search box")
    public void userTypeNameInSearchBox(String product) {
        homePage.onMainNavigationBar()
                .typeTextInSearchInput(product);

        scenarioContext.setData("product", product);
    }

    @Then("verify appropriate suggestions should be shown in drop down")
    public void verifyAppropriateSuggestionsShouldBeShownInDropDown() {
        String searchedProduct = (String) scenarioContext.getData("product");

        List<String> results = homePage.onMainNavigationBar().getResultsFromDropdown();

        Assertions.assertThat(results)
                .allMatch(result -> result.toLowerCase().contains(searchedProduct.toLowerCase()));
    }
}
