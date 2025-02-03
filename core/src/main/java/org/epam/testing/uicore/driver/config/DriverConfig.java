package org.epam.testing.uicore.driver.config;

import lombok.Data;
import org.epam.testing.uicore.driver.constants.Browser;

@Data
public class DriverConfig {
    private Browser brower;
    private boolean isLocal;
    private int browserWidth;
    private int browserHeight;
}
