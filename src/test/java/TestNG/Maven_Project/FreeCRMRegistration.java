package TestNG.Maven_Project;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FreeCRMRegistration {

	WebDriver driver;
	private final String browser = "chrome";

	@BeforeMethod
	public void launchApplication() {
		openBrowser();
		driver.get("https://classic.freecrm.com/register/");
	}

	@Test
	public void registerNewAccount() {

		Select editionSelect = new Select(driver.findElement(By.cssSelector("select[name='payment_plan_id']")));
		WebElement firstNameInput = driver
				.findElement(By.cssSelector("form[name='multipleForm']>div:nth-of-type(3) input"));
		WebElement lastNameInput = driver
				.findElement(By.cssSelector("form[name='multipleForm']>div:nth-of-type(4) input"));
		WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
		WebElement confirmEmailInput = driver.findElement(By.cssSelector("input[name='email_confirm']"));
		WebElement usernameInput = driver.findElement(By.cssSelector("input[placeholder ='Username']"));
		WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
		WebElement confirmPasswordInput = driver
				.findElement(By.cssSelector("input[name*='pass'][placeholder*='Confirm']"));
		WebElement termsCheckBox = driver.findElement(By.cssSelector("input[name^='agree']"));
		WebElement submitBtn = driver.findElement(By.cssSelector("button[name$='Button']"));

		/*
		 * Select editionSelect = new Select(driver.findElement(By.
		 * xpath("//div[@class='form-group']//following-sibling::div[@class='form-group has-feedback has-error']//input[@name='first_name']"
		 * ))); WebElement firstNameInput =
		 * driver.findElement(By.xpath("//input[@name='first_name']"));
		 */

		Random random = new Random();
		int randomValue = random.nextInt(100);

		editionSelect.selectByValue("1");
		firstNameInput.sendKeys("Jerin");
		lastNameInput.sendKeys("Raj");
		emailInput.sendKeys("jerin" + randomValue + "@mail.com");
		confirmEmailInput.sendKeys("jerin" + randomValue + "@mail.com");
		usernameInput.sendKeys(RandomStringUtils.randomAlphanumeric(7));
		passwordInput.sendKeys("jerin123");
		confirmPasswordInput.sendKeys("jerin123");
		termsCheckBox.click();
		submitBtn.click();
	}

	@AfterMethod
	public void closeApplication() {
		driver.close();
	}

	private void openBrowser() {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			throw new InvalidArgumentException("Invalid browser :" + browser);
		}
	}
}
