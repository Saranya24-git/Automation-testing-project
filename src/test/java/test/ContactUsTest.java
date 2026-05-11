package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.DriverFactory;
import pages.ContactUsPage;
import pages.LoginPage;

public class ContactUsTest
{
	WebDriver driver;
	@BeforeClass
	public void Setup()
	{
		driver = DriverFactory.initDriver();
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void TC6ContactUsForm()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");		
		ContactUsPage contactus = new ContactUsPage(driver);
		contactus.clickContactUs();
		String getInTouchText = contactus.verifyGetInTouchVisible();
		Assert.assertEquals(getInTouchText, "GET IN TOUCH");
		contactus.enterNameEmailSubjectMessageDetails("jumanjee","jumanjee@gmail.com","check the website","hello! would like to know about the website");
		contactus.uploadFile();
		contactus.clickSubmitButton();
		contactus.clickOkButton();
		String successMessageText = contactus.verifySuccessMessage();
		Assert.assertEquals(successMessageText, "Success! Your details have been submitted successfully.");
		contactus.clickHomeButton();
		login.isHomePageVisible();		
	}
	
	
	@AfterClass
	public void TearDown()
	{
		driver.quit();
	}
}