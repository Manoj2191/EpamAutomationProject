package org.epam.testing.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import org.epam.testing.uicore.driver.module.DriverModule;


public class GuiceInjectorSource implements InjectorSource {
    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION,
                CucumberModules.createScenarioModule(),
                new DriverModule(),
                new AppModules());
    }
}
