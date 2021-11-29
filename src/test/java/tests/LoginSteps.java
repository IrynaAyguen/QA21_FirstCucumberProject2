package tests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class LoginSteps {
    WebDriver driver;

    @Given("Navigate to Page PhoneBook")
    public void navigateToLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
    }

    @When("Click on Login tab")
    public void clickOnLoginTab() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));

    }

    @Then("Appear LoginRegistration form")
    public void isLoginRegistrationFormPresent() {
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
    }

    @And("Enter a valid data")
    public void enterValidData() {
        type(By.cssSelector("[placeholder='Email']"), "ira@web.de");
        type(By.cssSelector("[placeholder='Password']"), "Ira123123_");
    }

    @And("Click on Login button")
    public void clickOnLoginButton() {
        click(By.xpath("//button[contains(., ' Login')]"));
    }

    @Then("SignOut tab displayed")
    public void isSignOutTabDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
        //driver.quit();
    }

    @And("Enter a valid email and an invalid password")
    public void enterInvalidPassword (DataTable table){
        List<Map<String,String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        type(By.cssSelector("[placeholder='Email']"), email);
        type(By.cssSelector("[placeholder='Password']"), password);

    }

    @Then("Alert appeared")
    public void isAlertAppeared() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(isAlertPresent());
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }
    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }
}
