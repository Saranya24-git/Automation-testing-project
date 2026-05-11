package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
	WebDriver driver;
	
	@FindBy(partialLinkText="Home")
	WebElement homeLink;
	@FindBy(partialLinkText="Signup")
	WebElement signUpLoginLink;
	@FindBy(xpath="//h2[text()='New User Signup!']")
	WebElement newUserSignupText;
	@FindBy(name="name")
	WebElement nameInput;
	@FindBy(xpath="//input[@placeholder='Email Address' and @data-qa='signup-email']")
	WebElement signUpEmailInput;
	@FindBy(xpath="//button[text()='Signup']")
	WebElement signupButton;
	@FindBy(xpath="//b[text()='Enter Account Information']")
	WebElement accountInformationText;
	@FindBy(xpath = "//label[@class='top']")
	List<WebElement> genderOptions;
	@FindBy(id="password")
	WebElement passwordInput;
	@FindBy(id="days")
	WebElement dayDropdown;
	@FindBy(id="months")
	WebElement monthDropdown;
	@FindBy(id="years")
	WebElement yearDropdown;
	@FindBy(id="newsletter")
	WebElement newsletterSingupChkBox;
	@FindBy(id="optin")
	WebElement specialOffersChkBox;
	@FindBy(id="first_name")
	WebElement firstnameInput;
	@FindBy(id="last_name")
	WebElement lastnameInput;
	@FindBy(id="company")
	WebElement companyInput;
	@FindBy(id="address1")
	WebElement address1Input;
	@FindBy(id="address2")
	WebElement address2Input;
	@FindBy(id="country")
	WebElement countryDropDown;
	@FindBy(id="state")
	WebElement stateInput;
	@FindBy(id="city")
	WebElement cityInput;
	@FindBy(id="zipcode")
	WebElement zipcodeInput;
	@FindBy(id="mobile_number")
	WebElement mobilenumberInput;
	@FindBy(xpath="//button[text()='Create Account']")
	WebElement createAccountButton;
	@FindBy(xpath="//b[text()='Account Created!']")
    WebElement accountCreatedText;
	@FindBy(linkText="Continue")
	WebElement continueButton;
	@FindBy(xpath="//a/i[@class='fa fa-user']/following-sibling::b")
	WebElement usernameCheck;
	@FindBy(xpath="//a/i[@class='fa fa-trash-o']/following-sibling::b")
	WebElement deleteAccountLink;
	@FindBy(xpath="//b[text()='Account Deleted!']")
	WebElement accountDeletedText;
	@FindBy(xpath="//h2[text()='Login to your account']")
	WebElement loginToYourAccountText;
	@FindBy(xpath="//input[@placeholder='Email Address' and @data-qa='login-email']")
	WebElement loginEmailInput;
	@FindBy(xpath="//input[@placeholder='Password' and @data-qa='login-password']")
	WebElement loginPassword;
	@FindBy(xpath="//button[text()='Login' and @data-qa='login-button']")
	WebElement loginButton;
	@FindBy(xpath="//p[text()='Your email or password is incorrect!']")
	WebElement emailPasswordIncorrectText;
	@FindBy(xpath="//a[text()=' Logout']")
	WebElement logoutButton;
	@FindBy(xpath="//p[text()='Email Address already exist!']")
	WebElement emailAddressExistsErrorMessage;
	@FindBy(xpath="//button[@class='btn btn-success' and text()='Test Cases']")
	WebElement testCaseButton;
	@FindBy(xpath="//h2[@class=\"title text-center\" and .=\"Test Cases\"]")
	WebElement testCasePageTitle;
	@FindBy(xpath="//div[@class=\"col-sm-3 col-sm-offset-1\"]")
	WebElement footer;
	@FindBy(xpath="//div[@class=\"single-widget\"]/h2")
	WebElement footerHeader;
	@FindBy(id="susbscribe_email")
	WebElement emailInput;
	@FindBy(id="subscribe")
	WebElement subscribeButton;
	@FindBy(id="success-subscribe")
	WebElement subscribeMessage;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String isHomePageVisible()
	{
		return homeLink.getText().trim();
	}
	
	public void clickSignUpLoginButton()
	{
		signUpLoginLink.click();
	}
	
	public String isNewUserSignUpVisible()
	{
		return newUserSignupText.getText();
	}
	
	public void enterNameAndEmailAddress(String name,String email)
	{
		nameInput.sendKeys(name);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(signUpEmailInput));
		signUpEmailInput.sendKeys(email);
		signupButton.click();
	}
	
	public String isEnterAccountInformationVisible()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return accountInformationText.getText();
	}
	
	public void enterCredentialDetails(String gender, String password,String dayselectvalue, String monthselectvalue, String yearselectvalue)
	{
		String xpath = "//label[contains(normalize-space(),'" + gender + "')]";
		driver.findElement(By.xpath(xpath)).click();
		passwordInput.sendKeys(password);
		Select dayselect = new Select(dayDropdown);
		dayselect.selectByVisibleText(dayselectvalue);
		Select monthselect = new Select(monthDropdown);
		monthselect.selectByVisibleText(monthselectvalue);
		Select yearselect = new Select(yearDropdown);
		yearselect.selectByVisibleText(yearselectvalue);
		((JavascriptExecutor) driver).executeScript(
			    "arguments[0].scrollIntoView({block: 'center'});", newsletterSingupChkBox);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(newsletterSingupChkBox));
		newsletterSingupChkBox.click();
		specialOffersChkBox.click();		
	}
	
	public void enterPersonalDetails(String firstname, String lastname,String company,String address1,String address2, String country, String state, String city,String zipcode,String mobilenumber)
	{
		firstnameInput.sendKeys(firstname);
		lastnameInput.sendKeys(lastname);
		companyInput.sendKeys(company);
		address1Input.sendKeys(address1);
		address2Input.sendKeys(address2);
		Select countrydd = new Select(countryDropDown);
		countrydd.selectByVisibleText(country);
		stateInput.sendKeys(state);
		cityInput.sendKeys(city);
		zipcodeInput.sendKeys(zipcode);
		mobilenumberInput.sendKeys(mobilenumber);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
		 ((JavascriptExecutor) driver).executeScript(
			        "arguments[0].scrollIntoView({block: 'center'});", createAccountButton);
		createAccountButton.click();
	}
	
	public String isAccountCreatedVisible()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(accountCreatedText));
		return accountCreatedText.getText();
	}
	
	public void clickContinueButton()
	{
		continueButton.click();
	}
	
	public String verifyUsernameLoggedInVisible()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(usernameCheck));
		return usernameCheck.getText();
	}
	
	public void clickDeleteAccountButton()
	{
		deleteAccountLink.click();
	}
	
	public String verifyAccountDeletedVisible()
	{
		return accountDeletedText.getText();
	}
	
	public String verifyLoginToYourAccountText()
	{
		return loginToYourAccountText.getText();
	}
	
	public void enterEmailAndPassword(String email, String password)
	{
		loginEmailInput.sendKeys(email);
		loginPassword.sendKeys(password);
	}
	
	public void clickLoginButton()
	{
		

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));

		((JavascriptExecutor) driver).executeScript(
		    "arguments[0].scrollIntoView({block:'center'});", loginButton);

		loginButton.click();
		
	}
	
	public String incorrectEmailPasswordCheck()
	{
		return emailPasswordIncorrectText.getText();
	}
	
	public void clickLogoutButton()
	{
		logoutButton.click();
	}
	
	public String verifyEmailAddressAlreadyExists()
	{
		return emailAddressExistsErrorMessage.getText();
	}	
	
	public void clickTestCaseButton()
	{
		testCaseButton.click();
	}
	
	public String verifyTestCasePage()
	{
		return testCasePageTitle.getText();
	}
	
	public void scrollToFooter()
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",footer);		
	}
	
	public String verifyFooterHeader()
	{
		return footerHeader.getText();
	}
	
	public void enterEmailAndSubscribe(String email)
	{
		emailInput.sendKeys(email);
		subscribeButton.click();
	}
	
	public String verifySubscribeMessage()
	{
		return subscribeMessage.getText();
	}
	
	
}