package org.epam.testing.uicore.driver.constants;

public enum Browser {
    CHROME,
    FIREFOX;

    public static Browser getType(String browserType) {
        return valueOf(browserType);
    }
}
