package org.epam.testing.pages.factory;

import org.epam.testing.pages.abstracts.AbstractBlock;
import org.epam.testing.pages.abstracts.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockFactory {
    public static <T extends AbstractBlock> T createBlock(Class<T> clazz, WebDriver driver) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Could not create block instance for " + clazz.getSimpleName(), e);
        }
    }
}

