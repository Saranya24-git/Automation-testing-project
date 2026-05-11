package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Product;

public class ProductPage
{
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Products')]")
	WebElement productLink;
	@FindBy(xpath="//h2[@class='title text-center' and text()='All Products']")
	WebElement allProductsTitle;
	@FindBy(id="search_product")
	WebElement productNameInput;
	@FindBy(id="submit_search")
	WebElement searchIcon;
	@FindBy(xpath="//h2[@class='title text-center' and text()='Searched Products']")
	WebElement searchedProductTitle;
	@FindBy(xpath="//div[@class='col-sm-4']//div[@class='product-image-wrapper']")
	List<WebElement> searchedProductsList;
	@FindBy(xpath="//img[@src=\"/get_product_picture/1\"]")
	WebElement firstProduct;
	@FindBy(xpath="//a[@data-product-id=\"1\" and @class=\"btn btn-default add-to-cart\"]")
	WebElement firstProductAddToCart;
	@FindBy(xpath="//button[text()=\"Continue Shopping\"]")
	WebElement continueShoppingButton;
	@FindBy(xpath="//img[@src=\"/get_product_picture/2\"]")
	WebElement secondProduct;
	@FindBy(xpath="//a[@data-product-id=\"2\" and @class=\"btn btn-default add-to-cart\"]")
	WebElement secondProductAddToCart;
	@FindBy(xpath="//a[@href=\"/product_details/1\"]")
	WebElement firstViewProductButton;
	
	@FindBy(css = ".product-information h2")
	WebElement name;

	@FindBy(xpath = "//p[contains(text(),'Category')]")
	WebElement category;

	@FindBy(xpath = "//span[contains(text(),'Rs')]")
	WebElement price;

	@FindBy(xpath = "//p[contains(.,'Availability')]")
	WebElement availability;

	@FindBy(xpath = "//p[contains(.,'Condition')]")
	WebElement condition;

	@FindBy(xpath = "//p[contains(.,'Brand')]")
	WebElement brand;
	@FindBy(id="quantity")
	WebElement productQuantity;
	@FindBy(xpath="//button[@class=\"btn btn-default cart\"]")
	WebElement addToCartButton;
	@FindBy(xpath="//a/u[contains(text(),'View Cart')]")
	WebElement viewCartLink;

	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	
	public void clickProductButton()
	{
		productLink.click();
	}
	
	public String verifyAllProductsTitle()
	{
		return allProductsTitle.getText();
	}
	
	public void enterProductName(String productname)
	{
		productNameInput.sendKeys(productname);
		searchIcon.click();
	}
	
	public String verifySearchedProducts()
	{
		return searchedProductTitle.getText();
	}
	
	public List<String> verifyAllTheProductsSearched()
	{
			List<String> names = new ArrayList<>();

		    for (WebElement product : searchedProductsList) {
		        names.add(product.findElement(By.xpath(".//p")).getText());
		    }

		    return names;		
	}
	
	public void firstProductAddToCart()
	{
		
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(firstProduct));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",firstProduct);
		action.moveToElement(firstProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(firstProductAddToCart));
		firstProductAddToCart.click();		
	}
	
	public void clickContinueShoppingButton()
	{
		continueShoppingButton.click();
	}
	
	public void secondProductAddToCart()
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(secondProduct));
		action.moveToElement(secondProduct).perform();
		secondProductAddToCart.click();	
	}
	
	public void clickFirstViewProductButton()
	{
		firstViewProductButton.click();
	}
	
	
	public Product getProductDetails() 
	{
		return new Product(
		        name.getText(),
		        category.getText().split(":")[1].trim(),
		        price.getText().trim(),
		        availability.getText().split(":")[1].trim(),
		        condition.getText().split(":")[1].trim(),
		        brand.getText().split(":")[1].trim()
		    );
	}
	
	public void increaseProductQuantity(String quantity)
	{
		productQuantity.clear();
		productQuantity.sendKeys(quantity);
	}
	
	public void clickAddToCart()
	{
		addToCartButton.click();
	}
	
	public void clickViewCart()
	{
		viewCartLink.click();
	}
	
	
	
}

	
