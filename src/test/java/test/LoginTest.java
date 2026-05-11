package test;


import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.DriverFactory;
import pages.LoginPage;

public class LoginTest
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
	
	@Test(enabled=false)
	public void TC1RegisterUser()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickSignUpLoginButton();
		String newUserSignupText = login.isNewUserSignUpVisible();
		Assert.assertEquals(newUserSignupText, "New User Signup!");
		login.enterNameAndEmailAddress("jlkasf","oghofgjkhhh@gmail.com");		
		String accountInformationTitle = login.isEnterAccountInformationVisible();
		Assert.assertEquals(accountInformationTitle, "ENTER ACCOUNT INFORMATION");
		login.enterCredentialDetails("Mrs.","abcd", "7", "January", "2021");
		login.enterPersonalDetails("Hardward", "university", "jumanjee", "111,aallover,jumanjee", "chacha", "India","TN", "chennai","600001", "1000010000");
		String accountCreatedText = login.isAccountCreatedVisible();
		Assert.assertEquals(accountCreatedText, "ACCOUNT CREATED!");
		login.clickContinueButton();
		String usernameCheck = login.verifyUsernameLoggedInVisible();
		Assert.assertEquals(usernameCheck, "jlkasf");
		login.clickDeleteAccountButton();
		String accountDeletedText = login.verifyAccountDeletedVisible();
		Assert.assertEquals(accountDeletedText, "ACCOUNT DELETED!");
		login.clickContinueButton();		
	}
	
	@Test(enabled=false)
	public void TC2LoginWithValidCredentials()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickSignUpLoginButton();
		String loginToYourAccountText = login.verifyLoginToYourAccountText();
		Assert.assertEquals(loginToYourAccountText, "Login to your account");
		login.enterEmailAndPassword("jlkasf@gmail.com","abcd");
		login.clickLoginButton();
		String usernameCheck = login.verifyUsernameLoggedInVisible();
		Assert.assertEquals(usernameCheck, "jlkasf");			
	}
	@Test(enabled=false)
	public void TC3LoginWithInvalidCredentails()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickSignUpLoginButton();
		String loginToYourAccountText = login.verifyLoginToYourAccountText();
		Assert.assertEquals(loginToYourAccountText, "Login to your account");
		login.enterEmailAndPassword("jlkas1f@gmail.com","abcd");
		login.clickLoginButton();
		String incorrectEmailPasswordText = login.incorrectEmailPasswordCheck();
		Assert.assertEquals(incorrectEmailPasswordText, "Your email or password is incorrect!");
	}
	
	@Test(enabled=false)
	public void TC4LogoutUser()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickSignUpLoginButton();
		String loginToYourAccountText = login.verifyLoginToYourAccountText();
		Assert.assertEquals(loginToYourAccountText, "Login to your account");
		login.enterEmailAndPassword("jlkasf@gmail.com","abcd");
		login.clickLoginButton();
		String usernameCheck = login.verifyUsernameLoggedInVisible();
		Assert.assertEquals(usernameCheck, "jlkasf");	
		login.clickLogoutButton();
		login.verifyLoginToYourAccountText();
		login.isNewUserSignUpVisible();
	}
	
	@Test(enabled=false)
	public void TC5RegisterUserWithExistingEmail()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickSignUpLoginButton();
		String newUserSignupText = login.isNewUserSignUpVisible();
		Assert.assertEquals(newUserSignupText, "New User Signup!");
		login.enterNameAndEmailAddress("jlkasf","jlkasf@gmail.com");
		String emailErrorMessage = login.verifyEmailAddressAlreadyExists();
		Assert.assertEquals(emailErrorMessage,"Email Address already exist!");
	}
	
	@Test(enabled=false)
	public void TC07VerifyTestCasesPage()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.clickTestCaseButton();
		String testCasePageTitle = login.verifyTestCasePage();
		Assert.assertEquals(testCasePageTitle, "TEST CASES");		
	}
	
	@Test(enabled=false)
	public void TC10VerifySubscriptionInHomePage() 
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		login.scrollToFooter();
		Assert.assertEquals("SUBSCRIPTION",login.verifyFooterHeader());
		login.enterEmailAndSubscribe("test@gmail.com");
		Assert.assertEquals("You have been successfully subscribed!", login.verifySubscribeMessage());
	}
		
	@AfterClass
	public void TearDown()
	{
		driver.quit();
	}
	
	
}





//Test Case 14: Place Order: Register while Checkout
//Test Case 15: Place Order: Register before Checkout
//Test Case 16: Place Order: Login before Checkout
//Test Case 18: View Category Products
//Test Case 19: View & Cart Brand Products
//Test Case 20: Search Products and Verify Cart After Login
//Test Case 21: Add review on product
//Test Case 22: Add to cart from Recommended items
//Test Case 23: Verify address details in checkout page
//Test Case 24: Download Invoice after purchase order
//Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
//Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality

