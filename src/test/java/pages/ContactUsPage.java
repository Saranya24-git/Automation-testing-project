package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage
{
	WebDriver driver;
	
	@FindBy(xpath="//a[text()=' Contact us']")
	WebElement contactUsLink;
	@FindBy(xpath="//h2[@class=\"title text-center\" and text()=\"Get In Touch\"]")
	WebElement getInTouchText;
	@FindBy(xpath="//input[@name='name']")
	WebElement nameInput;
	@FindBy(xpath="//input[@name='email']")
	WebElement emailInput;
	@FindBy(xpath="//input[@name='subject']")
	WebElement subjectInput;
	@FindBy(id="message")
	WebElement messageInput;
	@FindBy(xpath="//input[@name='upload_file']")
	WebElement uploadFile;
	@FindBy(xpath="//input[@name='submit']")
	WebElement submitButton;
	@FindBy(xpath="//div[@class='status alert alert-success']")
	WebElement successMessageText;
	@FindBy(xpath="//a[@class='btn btn-success']")
	WebElement homeButton;
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickContactUs()
	{
		contactUsLink.click();
	}
	
	public String verifyGetInTouchVisible()
	{
		return getInTouchText.getText();
	}
		
	public void enterNameEmailSubjectMessageDetails(String name, String email, String subject,String message)
	{
		nameInput.sendKeys(name);
		emailInput.sendKeys(email);
		subjectInput.sendKeys(subject);
		messageInput.sendKeys(message);
	}
	
	public void uploadFile()
	{
		String filePath = System.getProperty("user.dir") + "/src/test/resources/test.txt";
		uploadFile.sendKeys(filePath);
	}
	
	public void clickSubmitButton()
	{
		submitButton.click();
	}
	
	public void clickOkButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		alert.accept();
	}
	
	public String verifySuccessMessage()
	{
		return successMessageText.getText();
	}
	
	public void clickHomeButton()
	{
		homeButton.click();
	}
	
	
}

	