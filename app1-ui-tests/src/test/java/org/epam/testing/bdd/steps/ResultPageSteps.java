package org.epam.testing.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.epam.testing.common.ScenarioContext;
import org.epam.testing.pages.results.ResultPage;
import org.epam.testing.uicore.driver.manager.WebDriverManger;

public class ResultPageSteps extends BaseStep {
    private final ResultPage resultPage;
    private final ScenarioContext scenarioContext;

    @Inject
    protected ResultPageSteps(ResultPage resultPage, WebDriverManger webDriverManger, ScenarioContext scenarioContext) {
        super(webDriverManger);
        this.resultPage = resultPage;
        this.scenarioContext = scenarioContext;
    }

    @Then("verify product listed in the result is appropriate")
    public void verify_product_listed_in_the_result_is_appropriate() throws InterruptedException {
        String searchedProduct = (String) scenarioContext.getData("product");

        Assertions.assertThat(resultPage.getResultCardTitle())
                .allMatch(title -> title.toLowerCase().contains(searchedProduct.toLowerCase()));
    }
}
