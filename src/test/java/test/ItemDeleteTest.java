package test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static test.TestShare.*;

public class ItemDeleteTest extends AfterLoginTest {

    @BeforeMethod
    public void 削除画面に行き_商品削除リンクをクリックする() {
        driver.get("http://localhost:8080/item/index.html");
        WebElement deleteLink = driver.findElement(By.linkText("商品削除"));
        deleteLink.click();
    }

    @Test(dependsOnGroups = {"register"})
    public void 商品を削除しない_確認ダイアログでキャンセルする() {
        WebElement element = driver.findElement(By.xpath("/html/body/form/div/input"));
        element.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        Assert.assertEquals(driver.getTitle(), "マスタ管理 - 商品削除画面");
    }

    @Test(dependsOnMethods = {"商品を削除しない_確認ダイアログでキャンセルする"})
    public void 商品を削除する() {
        String nameValue = driver.findElement(By.id("itemName")).getText();

        WebElement element = driver.findElement(By.xpath("/html/body/form/div/input"));
        element.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assert.assertEquals(driver.getTitle(), "マスタ管理 - 商品一覧画面");

        try {
            driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr/td[.='" + nameValue + "']"));
            Assert.fail("削除できていません");
        } catch (NoSuchElementException expected) {

        }
    }

}
