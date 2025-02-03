package org.epam.testing.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.epam.testing.common.utils.OwnerConfigReader;
import org.epam.testing.config.TestRunConfig;
import org.epam.testing.pages.home.HomePage;
import org.epam.testing.pages.results.ResultPage;


public class AppModules extends AbstractModule {
    @Override
    public void configure() {
        bind(HomePage.class);
        bind(ResultPage.class);

        //bindScope(ScenarioScoped.class, Scopes.SINGLETON);

       // bind(ScenarioContext.class).in(ScenarioScoped.class);
    }

    @Provides
    @Singleton
    public TestRunConfig provideAppConfig() {
        return OwnerConfigReader.getConfig(TestRunConfig.class);
    }
}
