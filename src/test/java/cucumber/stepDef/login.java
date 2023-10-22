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

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("login page displayed")
    public void loginPageDisplayed() {

        WebDriverManager.firefoxdriver().setup(); //setup firefox driver automatically using web driver manager
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //assertion di login page
        String loginPage = driver.findElement(By.xpath("//div[@class='login_logo']")).getText();
        Assert.assertEquals(loginPage, "Swag Labs");

    }


    @When("user input (.*) as username$")
    public void userInputEmailAsUsername(String username) {

        driver.findElement(By.id("user-name")).sendKeys(username); //get email

    }

    @And("user input (.*) as password$")
    public void userInputPasswordAsPassword(String password) {

        driver.findElement(By.id("password")).sendKeys(password); //get password

    }

    @And("user click login button")
    public void userClickLoginButton() {
        driver.findElement(By.id("login-button")).click(); //click login button

    }

    @Then("user see (.*) displayed$")
    public void userSeeStatusDisplayed(String status) {

        if (status.equals("success")){
            String dashboardPage = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
            Assert.assertEquals(dashboardPage, "Swag Labs");
        }else if ("failed".equals(status)){
            String errorMassage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            Assert.assertEquals(errorMassage, "Epic sadface: Username and password do not match any user in this service");
        }else {
            String errorMassage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            Assert.assertEquals(errorMassage, "Epic sadface: Sorry, this user has been locked out.");
        }

        driver.close();

    }
    @And("user click item")
    public void userClickItem() {
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div")).click();
    }

    @Then("item is displayed")
    public void itemIsDisplayed() {
        String information = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
        Assert.assertEquals(information, "Sauce Labs Backpack");
        driver.close();
    }
}
