package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.DriverFactory;
import utils.UserActions;

public class UserRegistrationAndLoginTest {
    private WebDriver driver;
    private String registeredEmail;
    private final String password = "Test@1234";

    @Parameters({"headless"})
    @BeforeClass
    public void setUp(@org.testng.annotations.Optional("false") String headless) {
        driver = DriverFactory.getDriver(Boolean.parseBoolean(headless));
    }

    @Test(priority = 1)
    public void testRegisterNewUser() {
        registeredEmail = UserActions.registerNewUser(driver);
        Assert.assertTrue(driver.getPageSource().contains("Your Account Has Been Created!"), "Registration failed!");
    }

    @Test(priority = 2, dependsOnMethods = "testRegisterNewUser")
    public void testLoginWithRegisteredUser() {
        UserActions.logout(driver);
        UserActions.login(driver, registeredEmail, password);
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Login failed!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
