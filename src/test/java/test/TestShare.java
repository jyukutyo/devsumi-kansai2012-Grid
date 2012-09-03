/*
 * (c)FURYU CORP. 2012. All rights reserved.
 *
 * $Id$
 */
package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestShare {

    protected static WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public static void createDriver(@Optional("firefox") String browser) throws MalformedURLException {
        DesiredCapabilities capability = null;

        if (browser.equalsIgnoreCase("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("Firefox");
            capability.setPlatform(Platform.MAC);
        } else if (browser.equalsIgnoreCase("iexplore")) {
            // capability.setCapability("jenkins.label", "windows");
            capability = DesiredCapabilities.internetExplorer();
            capability.setBrowserName("IE");
            capability.setPlatform(Platform.WINDOWS);
        }
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }

}
