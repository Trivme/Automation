package org.tophap;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortResultTest extends SingleTest {

    @Test
    void sortPriceTest() throws InterruptedException {
        getDriver().get("https://www.tophap.com/");
        TestHelper.login(getDriver());

        WebElement searchInput = getDriver().findElement(By.id("th-geo-input"));
        searchInput.clear();
        searchInput.sendKeys("94523");

        Thread.sleep(2000);
        getDriver().findElement(By.className("th-search-button")).click();

        // waiting for menu
        getDriver().findElement(By.xpath("//aside[@class='th-sider']"));

        WebElement menu = getDriver().findElement(By.className("th-trigger"));
        menu.click();

        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='A-Z']")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[text()='Price']")).click();
        menu.click();

        List<WebElement> resultList = getDriver().findElements(By.className("th-result-item-wrapper"));
        int prevPrice = Integer.MIN_VALUE;
        for (WebElement resultItem : resultList) {
            int currentPrice = Integer.parseInt(resultItem.findElement(By.className("th-price")).getText().replaceAll("[$,]", ""));
            assertTrue(prevPrice <= currentPrice, String.format("$%d more than $%d", prevPrice, currentPrice));
            prevPrice = currentPrice;
        }

    }
}
