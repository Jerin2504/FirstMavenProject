package TestNG.Maven_Project;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class MyTestNGExample {
	
	@BeforeClass
	public void inaugration() {
		System.out.println("Runing all test");
	}
	
	@BeforeMethod
	public void openBrowser(){
		System.out.println("Opening the browser");
	}
	
	@Test(priority = 2)
	public void testSomething() {
		System.out.println("Bhidoo...testing something");
	}
	
	@Ignore
	@Test
	public void testnew() {
		System.out.println("Test new");
	}
	
	
	@Test(priority = 1)
	public void testAnything() {
		System.out.println("Bhidoo....testing anything");
	}
	
	@AfterMethod
	public void closeBrowser() {
		System.out.println("Closing the browser");
		
	}

}
