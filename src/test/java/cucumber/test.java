package cucumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class test {
    @Test //tag untuk running script di bawah ini
    public void succes_login(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com";

        WebDriverManager.firefoxdriver().setup(); //setup firefox driver automatically using web driver manager
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //assertion di login page
        String loginPage = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPage, "Swag Labs");
        driver.findElement(By.id("user-name")).sendKeys("standard_user"); //get email
        driver.findElement(By.id("password")).sendKeys("secret_sauce"); //get password
        driver.findElement(By.id("login-button")).click(); //click login button
        String dashboardPage = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(dashboardPage, "Swag Labs");

        driver.findElement(By.id("shopping_cart_container")).click(); //click cart button
        boolean checkoutButton = driver.findElement(By.id("checkout")).isEnabled();
        Assert.assertEquals(checkoutButton, "false");

        driver.findElement(By.id("checkout")).click(); //click checkout button
        String cartPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(cartPage, "Checkout: Your Information");

        driver.findElement(By.id("first-name")).sendKeys("iqbal"); //first name
        driver.findElement(By.id("last-name")).sendKeys("al farizy"); //last name
        driver.findElement(By.id("postal-code")).sendKeys("1234"); //zip/postal Code

        driver.findElement(By.id("continue")).click(); //click continue button
        String overviewPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(overviewPage, "Checkout: Overview");

        driver.findElement(By.id("finish")).click(); //click continue button
        String finalPage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        Assert.assertEquals(finalPage, "Thank you for your order!");

        driver.close();
    }

    @Test //tag untuk running script di bawah ini
    public void fill_Information(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com";
        WebDriverManager.firefoxdriver().setup(); //setup firefox driver automatically using web driver manager
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //assertion di login page
        String loginPage = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPage, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("standard_user"); //get email

        driver.findElement(By.id("password")).sendKeys("secret_sauce"); //get password

        driver.findElement(By.id("login-button")).click(); //click login button
        String dashboardPage = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        Assert.assertEquals(dashboardPage, "Swag Labs");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click(); //click add to cart button
        String badge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        Assert.assertEquals(badge, "1");

        driver.findElement(By.id("shopping_cart_container")).click(); //click cart button
        boolean checkoutButton;
        checkoutButton = driver.findElement(By.id("checkout")).isEnabled();
        Assert.assertEquals(checkoutButton, true);

        driver.findElement(By.id("checkout")).click(); //click checkout button
        String cartPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(cartPage, "Checkout: Your Information");

        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("firstname"); //first name
        String fillFirstName = driver.findElement(By.xpath("//*[@id='first-name']")).getAttribute("value");
        Assert.assertEquals(fillFirstName, "firstname");

        driver.close();
    }
}
