/*
 * (c)FURYU CORP. 2012. All rights reserved.
 *
 * $Id$
 */
package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import static test.TestShare.driver;

public class AfterLoginTest {

    @BeforeClass
    public void ログインする() {
        driver.get("http://localhost:8080/login.html");
        WebElement userId = driver.findElement(By.name("j_username"));
        WebElement password = driver.findElement(By.name("j_password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        userId.sendKeys("admin");
        password.sendKeys("spring");
        loginButton.click();
    }

}
