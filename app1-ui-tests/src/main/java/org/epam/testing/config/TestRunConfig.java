package org.epam.testing.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "classpath:TestRunConfig.properties"
})
public interface TestRunConfig extends Config {
    @Config.Key("app.name")
    String getName();

    @Config.Key("app.url")
    String getUrl();
}
