package org.epam.testing.bdd.steps;

import org.epam.testing.uicore.driver.manager.WebDriverManger;

public abstract class BaseStep {
    protected final WebDriverManger webDriverManger;

    protected BaseStep(WebDriverManger webDriverManger) {
        this.webDriverManger = webDriverManger;
    }
}
