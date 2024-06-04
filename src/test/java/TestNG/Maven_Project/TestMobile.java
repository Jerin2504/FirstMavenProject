package TestNG.Maven_Project;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestMobile {
	
	@BeforeClass
	public void checkBattery() {
		System.out.println("Battery is 100% charged ");
	}
	
	@BeforeMethod
	public void switchOnMobile() {
		System.out.println("Mobile phone switched on ");
	}
	
	@Test(priority = 2)
	public void validateProcessor() {
		System.out.println("Processor is working fine..");
	}

	@Test(priority = 1)
	public void validateScreenDisplay() {
		System.out.println("Screen display working fine..");
	}
	
	@AfterMethod
	public void switchOffMobile() {
		System.out.println("Mobile phone switched off ");
	}
	
	@AfterClass
	public void removeBattery() {
		System.out.println("Battery Removed");
	}
	
}
