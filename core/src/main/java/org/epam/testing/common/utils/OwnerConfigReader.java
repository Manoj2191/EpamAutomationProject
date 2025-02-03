package org.epam.testing.common.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class OwnerConfigReader {
    public static <T extends Config> T getConfig(Class<T> config) {
        return ConfigFactory.create(config, System.getProperties(), System.getenv());
    }
}
