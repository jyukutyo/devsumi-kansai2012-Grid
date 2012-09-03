package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static test.TestShare.*;

public class ItemAddTest extends AfterLoginTest {

    @BeforeMethod
    public void 商品作成画面に行く() {
        driver.get("http://localhost:8080/item/create.html");
    }

    @Test(groups = "register")
    public void 商品画像つきで商品を登録する() {
        WebElement name = driver.findElement(By.id("itemName"));
        WebElement price = driver.findElement(By.id("price"));
        WebElement description = driver.findElement(By.id("description"));

        String nameValue = "duke1";
        name.sendKeys(nameValue);
        price.sendKeys("100");
        description.sendKeys("Javaのマスコットキャラクター");

        WebElement file = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[3]/td[2]/input"));
        file.sendKeys("/Users/jyukutyo/Downloads/duke1.jpeg");

        WebElement registerButton = driver.findElement(By.xpath("/html/body/form/div/input"));
        registerButton.click();

        Assert.assertEquals(driver.getTitle(), "マスタ管理 - 商品一覧画面");

        WebElement nameInList = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr/td[.='" + nameValue + "']"));
        Assert.assertEquals(nameInList.getText(), nameValue);
    }

}
