package utils;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserActions {
    public static String registerNewUser(WebDriver driver) {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        String email = "testuser_" + UUID.randomUUID() + "@mailinator.com";
        driver.findElement(By.id("input-firstname")).sendKeys("Test");
        driver.findElement(By.id("input-lastname")).sendKeys("User");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("Test@1234");
        driver.findElement(By.id("input-confirm")).sendKeys("Test@1234");
    // Click the label for the agree checkbox to avoid ElementClickInterceptedException
    driver.findElement(By.cssSelector("label[for='input-agree']")).click();
        driver.findElement(By.cssSelector("input[type='submit'][value='Continue']")).click();
        return email;
    }

    public static void logout(WebDriver driver) {
        driver.findElement(By.linkText("Logout")).click();
    }

    public static void login(WebDriver driver, String email, String password) {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
    }
}
