package org.epam.testing.testngtest;

import com.google.inject.Inject;
import org.epam.testing.pages.home.HomePage;
import org.epam.testing.uicore.driver.manager.WebDriverManger;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends BaseTest {
    private final HomePage homePage;

    @Inject
    protected HomePageTest(HomePage homePage, WebDriverManger webDriverManger) {
        super(webDriverManger);
        this.homePage = homePage;
    }

    //@Test
    public void test1() {
        List<String> ls = homePage.open()
                .onMainNavigationBar()
                .searchForProduct("Laptop")
                .getResultCardTitle();

        ls.forEach(System.out::println);
    }
}
