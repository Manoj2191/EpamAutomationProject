package org.epam.testing.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.epam.testing.uicore.driver.manager.WebDriverManger;

public class Hooks extends BaseStep {
    @Inject
    protected Hooks(WebDriverManger webDriverManger) {
        super(webDriverManger);
    }

    @Before
    public void setUp() {
        webDriverManger.startDriver();
    }

    @After
    public void tearDown() {
        webDriverManger.quiteDriver();
    }
}
