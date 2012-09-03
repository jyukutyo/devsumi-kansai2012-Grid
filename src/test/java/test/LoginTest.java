package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static test.TestShare.*;

public class LoginTest {

    @BeforeMethod
    public void toLoginPage() {
        driver.get("http://localhost:8080/login.html");
    }

    @Test
    public void ログインする() {
        WebElement userId = driver.findElement(By.name("j_username"));
        WebElement password = driver.findElement(By.name("j_password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        userId.sendKeys("admin");
        password.sendKeys("spring");
        loginButton.click();

        final int timeoutInSeconds = 3;
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.titleIs("マスタ管理 - 商品一覧画面"));

        // このように書くと、画面遷移より先にassertが実行され、失敗することがある
        // Assert.assertEquals(driver.getTitle(), "マスタ管理 - 商品一覧画面");
    }

    @Test
    public void エラーになる_ユーザIDを入力していない(Method method) throws IOException {
        WebElement password = driver.findElement(By.name("j_password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        password.sendKeys("spring");
        loginButton.click();

        try {
            driver.findElement(By.xpath("/html/body/div/font"));
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.fail("エラーメッセージの要素がありません");
        }
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/tmp/" + method.getName() + ".png"));
    }

}
