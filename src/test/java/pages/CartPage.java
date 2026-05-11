package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage
{
	WebDriver driver;
	
	@FindBy(xpath="//a[@href=\"/view_cart\"]")
	WebElement viewCartButton;
	@FindBy(xpath="//table[@id=\"cart_info_table\"]/tbody/tr")
	List<WebElement> cartTableRow;
	@FindBy(xpath="//a[@class=\"btn btn-default check_out\" and text()=\"Proceed To Checkout\"]")
	WebElement cartPageCheck;
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickViewCartButton()
	{
		viewCartButton.click();
	}
	
	
	public int verifyAddedProductsCount()
	{
		return cartTableRow.size();		
	}
	
	public List<String[]> getCartData()
	{
		List<String[]> tabledata = new ArrayList<>();
		for(WebElement row: cartTableRow)
		{
		List<WebElement> cols = row.findElements(By.tagName("td"));
		tabledata.add(new String[] {
				cols.get(1).getText().replace("\n", " ").trim(),
				cols.get(2).getText(),
				cols.get(3).getText(),
				cols.get(4).getText()
		});		
		}
		return tabledata;
	}
	
	public boolean verifyCartPageDisplayed()
	{
		try
		{
			return cartPageCheck.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void removeProductByName(String productname)throws InterruptedException
	{
		for (int i = 1; i <= cartTableRow.size(); i++) 
		 {
			 WebElement row = driver.findElement(By.xpath("//table[@id='cart_info_table']/tbody/tr[" + i + "]"));
		     String name = row.findElement(By.xpath("td[2]")).getText().replace("\n", " ");
		     if (name.contains(productname)) {
		            row.findElement(By.xpath(".//a[@class='cart_quantity_delete']")).click();
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            wait.until(ExpectedConditions.invisibilityOfElementLocated(
		            	    By.xpath("//td[contains(text(),'" + productname + "')]")
		            	));
		            break;
		        }
		 }
	 }
}
