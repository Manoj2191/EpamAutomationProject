package org.epam.testing.uicore.driver.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "classpath:DriverConfig.properties"
})
public interface UITestConfig extends Config {
    @Config.Key("browser.isLocal")
    Boolean isLocal();

    @Config.Key("browser.name")
    String getBrowserName();

    @Config.Key("browser.width")
    Integer getBrowserWidth();

    @Config.Key("browser.height")
    Integer getBrowserHeight();
}
