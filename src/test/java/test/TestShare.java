/*
 * (c)FURYU CORP. 2012. All rights reserved.
 *
 * $Id$
 */
package test;

import java.net.MalformedURLException;
import java.net.URL;

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
        } else if (browser.equalsIgnoreCase("ie")) {
            capability = DesiredCapabilities.internetExplorer();
        }
        driver = new RemoteWebDriver(new URL("http://jyukutyomac.local:4444/wd/hub"), capability);
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }

}
