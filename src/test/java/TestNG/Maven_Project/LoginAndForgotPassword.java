package TestNG.Maven_Project;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginAndForgotPassword {

	WebDriver driver;
	private String browserName = "chrome";
	private final String email = "jerin@mail.com";
	private final String password = "jerin123";

	@BeforeMethod
	public void setUpBrowser() {
		openBrowser();

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}

	@Test(priority = 1)
	public void validateLogIn() {
	
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Login']")).submit();

		assertEquals(driver.findElement(By.cssSelector("#content h2:nth-of-type(1)")).getText(), "My Account");
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	public void validateForgotPassword() {
		driver.findElement(By.xpath("//input[@type='password']//following-sibling::a")).click();

		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='submit']")).submit();

		assertEquals(driver.findElement(By.cssSelector("div.alert")).getText(),
				"An email with a confirmation link has been sent your email address.",
				"The email does not exist in the system");
		driver.manage().window().fullscreen();
	}

	@AfterMethod
	public void closeBrowser() {
//		driver.close();
	}

	private void openBrowser() {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid Browser : " + browserName);
		}
	}
}
