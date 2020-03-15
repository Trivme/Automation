package org.tophap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestHelper {

    public static void login(WebDriver driver) {
        driver.findElement(By.xpath("//button[text()='Try for Free']")).click();
        driver.findElement(By.cssSelector("svg.th-close-button")).click();
    }
}
