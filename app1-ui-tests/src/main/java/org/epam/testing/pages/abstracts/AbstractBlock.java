package org.epam.testing.pages.abstracts;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public abstract class AbstractBlock {
    protected WebDriver webDriver;

    protected AbstractBlock(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public abstract boolean isShown();
}
