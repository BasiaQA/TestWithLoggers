package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SeleniumFormTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("SeleniumFormTest.class");

    String url;
    String firstName;
    String lastName;
    String email;
   String age;
    String continent;
    String seleniumCommand;
    String filePath;
    String inf;

    @Test
    public void shouldFillFormWithSuccess() {
        url = "https://seleniumui.moderntester.pl/form.php";
        firstName = "Jan";
        lastName = "Kowalski";
        email = "test1@gmail.com";
        age = "70";
        continent = "europe";
        seleniumCommand = "webelement-commands";
        filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file.txt";
        inf = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla ipsum eget arcu fermentum viverra.";

        getDriver().get(url);
        logger.info("Webpage url: " + url);

        getDriver().findElement(By.id("inputFirstName3")).sendKeys(firstName);
        getDriver().findElement(By.id("inputLastName3")).sendKeys(lastName);
        logger.debug("First name is: {} and last name is: {}", firstName, lastName);

        getDriver().findElement(By.id("inputEmail3")).sendKeys(email);
        logger.debug("Email is: {} ", email);

        List<WebElement> gridRadiosSex = getDriver().findElements(By.cssSelector("[name='gridRadiosSex']"));
        gridRadiosSex.get(0).click();
        logger.debug("Radio button properly clicked");

        getDriver().findElement(By.id("inputAge3")).sendKeys(age);
        logger.debug("Age is: {} ", age);

        List<WebElement> experience = getDriver().findElements(By.cssSelector("[name='gridRadiosExperience']"));
        logger.debug("Number of radios " + experience.size());
        int index = new Random().nextInt(experience.size());
        experience.get(index).click();
        logger.debug("Random index of radio: " + index);

        List<WebElement> profession = getDriver().findElements(By.name("gridCheckboxProfession"));
        for (WebElement prof : profession) {
            if (!prof.isSelected()) {
                prof.click();
                break;
            }
        }
        logger.debug("Checkbox properly clicked");

        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByValue(continent);
        logger.debug("Selected continent: {}", continent);

        Select seleniumCommands = new Select(getDriver().findElement(By.id("selectSeleniumCommands")));
        seleniumCommands.selectByValue(seleniumCommand);
        logger.debug("Select by value " + seleniumCommand+ "options: " + seleniumCommands.getOptions());

        getDriver().findElement(By.id("chooseFile")).sendKeys(filePath);
        logger.debug("File path is: " + filePath);

        getDriver().findElement(By.id("additionalInformations")).sendKeys(inf);
        logger.debug("Additional information is: " + inf);

        getDriver().findElement(By.xpath("//button[@type=('submit')]")).click();
        logger.debug("Button properly clicked");

        WebElement msg = getDriver().findElement(By.id("validator-message"));
        logger.debug("Message is: " + msg.getText());

        assertThat(msg.getText(), equalTo("Form send with success"));
    }
}

