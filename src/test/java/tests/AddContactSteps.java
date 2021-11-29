package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddContactSteps {
    WebDriver driver;


    @And("Click ADD tab")
    public void clickAddTab() throws InterruptedException {
        Thread.sleep(5000);
        click(By.xpath("//a[contains(.,'ADD')]"));

    }

    @And("Enter a valid data of contact")
    public void enterValidDataOfContact() {
        type(By.cssSelector("[placeholder='Name']"), "Svetlana");  ///
        type(By.cssSelector("input:nth-child(2)"), "Svetlanova");
        type(By.cssSelector("input:nth-child(3)"), "1111111");
        type(By.cssSelector("input:nth-child(4)"), "sveta@web.de");
        type(By.cssSelector("input:nth-child(5)"), "Frankfurt");
        type(By.cssSelector("input:nth-child(6)"), "sister");
    }

    @And("Click on Save button")
    public void clickOnSaveButtonByAddContact(){
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Then("Is Contact created")
    public void isContactCreated(){
        Assert.assertTrue(isContactCreated("1111111"));
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
            click(By.xpath("//div[@class='contact-item_card__2SOIM']")); // if 1 contact is present
            // click(By.xpath("//div[@class='contact-item_card__2SOIM'] /h3[contains(.,'1111111')]"));// if was many contacts
            click(By.xpath("//button[contains(., 'Remove')]"));
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }


}
