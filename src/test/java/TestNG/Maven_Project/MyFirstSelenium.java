package TestNG.Maven_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstSelenium {

	@Test
	public void validateLoginFunctionality() {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

		WebElement emailInput = driver.findElement(By.id("input-email"));
		WebElement pwdInput = driver.findElement(By.cssSelector("input[name='password']"));
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		
		emailInput.sendKeys("joyal.jhs@gmail.com");
		pwdInput.sendKeys("rough");
		loginBtn.click();
	}

}
