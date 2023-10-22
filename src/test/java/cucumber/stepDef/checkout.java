package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class checkout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("standard_user success login")
    public void userSuccessLogin() {
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

    }

    @Given("user add one item to cart")
    public void userAddOneItemToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click(); //click add to cart button
        String badge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        Assert.assertEquals(badge, "1");
    }

    @When("user open the shopping cart")
    public void userOpenTheShoppingCart() {
        driver.findElement(By.id("shopping_cart_container")).click(); //click cart button
        boolean checkoutButton;
        checkoutButton = driver.findElement(By.id("checkout")).isEnabled();
        Assert.assertEquals(checkoutButton, true);

    }

    @And("user click on the checkout button")
    public void userClickOnTheCheckoutButton() {
        driver.findElement(By.id("checkout")).click(); //click checkout button
        String cartPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(cartPage, "Checkout: Your Information");
    }

    @And("user fill in valid information")
    public void userFillInValidInformation() {
        driver.findElement(By.id("first-name")).sendKeys("iqbal"); //first name
        driver.findElement(By.id("last-name")).sendKeys("al farizy"); //last name
        driver.findElement(By.id("postal-code")).sendKeys("1234"); //zip/postal Code
    }

    @And("user click on the continue button")
    public void userClickOnTheContinueButton() {
        driver.findElement(By.id("continue")).click(); //click continue button
        String cartPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(cartPage, "Checkout: Overview");
    }

    @Then("user click finish button")
    public void userClickFinishButton() {
        driver.findElement(By.id("finish")).click(); //click continue button
        String cartPage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        Assert.assertEquals(cartPage, "Thank you for your order!");
        driver.close();
    }

    @Then("user unable to click checkout button")
    public void userUnableToClickCheckoutButton() {
        driver.findElement(By.id("shopping_cart_container")).click(); //click cart button
        boolean checkoutButton;
        checkoutButton = driver.findElement(By.id("checkout")).isEnabled();
        Assert.assertEquals(checkoutButton, false);
        driver.close();
    }
}
