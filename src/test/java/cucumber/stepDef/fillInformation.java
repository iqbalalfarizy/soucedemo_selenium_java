package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class fillInformation {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("problem_user navigate to the checkout: your information page")
    public void userNavigateToTheCheckoutYourInformationPage() {
        WebDriverManager.firefoxdriver().setup(); //setup firefox driver automatically using web driver manager
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //assertion di login page
        String loginPage = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPage, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("problem_user"); //get email

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
        Assert.assertTrue(checkoutButton);

        driver.findElement(By.id("checkout")).click(); //click checkout button
        String cartPage = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(cartPage, "Checkout: Your Information");
    }

    @Given("user input (.*) as firstname$")
    public void userInputFirstnameAsFirstname(String firstname) {
        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys(firstname); //first name
        String fillFirstName = driver.findElement(By.xpath("//*[@id='first-name']")).getAttribute("value");
        Assert.assertEquals(fillFirstName, firstname);
    }

    @When("user input (.*) as lastname$")
    public void userInputLastnameAsLastname(String lastname) {
        driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys(lastname); //last name
        String fillLastName = driver.findElement(By.xpath("//*[@id='last-name']")).getAttribute("value");
        Assert.assertEquals(fillLastName, lastname);
    }

    @And("user input (.*) as zippostalcode$")
    public void userInputZipPostalcodeAsZipPostalcode(String zippostalcode) {
        driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys(zippostalcode); //zip/postal Code
        String fillzippostalCode = driver.findElement(By.xpath("//*[@id='postal-code']")).getAttribute("value");
        Assert.assertEquals(fillzippostalCode, zippostalcode);
    }


    @Then("user can see pop up (.*) displayed$")
    public void userCanSeePopUpStatusDisplayed(String status) {

        if (status.equals("success")) {
            String cartPage = driver.findElement(By.cssSelector(".title")).getText();
            Assert.assertEquals(cartPage, "Checkout: Overview");
            driver.close();

        } else {
            boolean errorMassage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed();
            Assert.assertTrue(errorMassage);
            driver.close();
        }
    }

    @And("user click on continue button")
    public void userClickOnContinueButton() {
        driver.findElement(By.id("continue")).click(); //click continue button
    }
}
