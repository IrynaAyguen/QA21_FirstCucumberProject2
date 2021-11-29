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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class LoginSteps extends HelperBase{

    //WebDriver driver;

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


    @And("Click ADD tab")
    public void clickAddTab() throws InterruptedException {
        Thread.sleep(2000);
        click(By.xpath("//a[contains(.,'ADD')]"));

    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
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

    @And("Enter a valid data of contact")
    public void enterValidDataOfContact() throws InterruptedException {
        type(By.cssSelector("[placeholder='Name']"), "Svetlana");
        type(By.cssSelector("input:nth-child(2)"), "Svetlanova");
        type(By.cssSelector("input:nth-child(3)"), "111");
        type(By.cssSelector("input:nth-child(4)"), "sveta@web.de");
        type(By.cssSelector("input:nth-child(5)"), "Frankfurt");
        type(By.cssSelector("input:nth-child(6)"), "doctor");
        Thread.sleep(3000);
    }

    @And("Click on Save button")
    public void clickOnSaveButtonByAddContact()  {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }


    @Then("Is Contact created")
    public void isNewContactCreated() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(isContactCreated("111"));
        Thread.sleep(3000);
        removeContact();
    }


    public Boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.xpath("//h3"));
        for (WebElement  el: contacts){
            System.out.println(el.getText());
            if (el.getText().contains(text))
                return true;
        }return false;
    }

    public void removeContact() {

        if (!isContactListEmpty()) {
            System.out.println(" ne pustoy");

            click(By.xpath("//div[@class='contact-item_card__2SOIM']")); // if 1 contact is present
            // click(By.xpath("//div[@class='contact-item_card__2SOIM'] /h3[contains(.,'111')]"));// if was many contacts
            click(By.xpath("//button[contains(., 'Remove')]"));
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

}
