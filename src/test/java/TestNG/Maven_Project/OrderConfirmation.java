package TestNG.Maven_Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderConfirmation {
	private WebDriver driver;
	private Actions action;
	
	private String browserName = "chrome";
	private final String email = "jerin@mail.com";
	private final String password = "jerin123";

	@BeforeMethod
	public void setUpBrowser() {
		
		openBrowser();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		action = new Actions(driver);
	}

	@Test(priority = 1)
	public void validateAccountLogin() throws InterruptedException {
		loginAccount();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "My Account", "Invalid credentials");
	}

	@Test(priority = 2)
	public void selectOptionFromNavigationBar() {
		loginAccount();
		selectMacCategory();

		Assert.assertEquals(driver.getTitle(), "Mac", "Could not reach the Mac page");
	}

	@Test(priority = 3)
	public void validateAddToCartFunctionality() {
		loginAccount();
		selectMacCategory();
		driver.findElement(By.xpath("//div[@class='button-group'] //span")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.alert>a:last-of-type")).getText(), "shopping cart",
				"The product could not be added to the cart");
	}

	@Test(priority = 4)
	public void validateCheckOutFunctionality() {
		loginAccount();
		selectMacCategory();
		driver.findElement(By.xpath("//div[@class='button-group'] //span")).click();

		driver.findElement(By.xpath("//span[text()='Checkout']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart", "Could not naviagate to the Checkout page");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	private void loginAccount() {
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type='submit']")).submit();
	}

	private void selectMacCategory() {
		action.moveToElement(driver.findElement(By.cssSelector("ul.nav>li:first-of-type"))).perform();
		driver.findElement(By.cssSelector("ul.nav>li:first-of-type ul>li:last-of-type a")).click();

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
