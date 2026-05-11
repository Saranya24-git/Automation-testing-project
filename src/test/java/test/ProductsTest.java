package test;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.DriverFactory;
import models.Product;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class ProductsTest
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
	public void TC08verifyAllProductsAndProductDetailPage()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");	
		ProductPage product = new ProductPage(driver);
		product.clickProductButton();
		String allProductsTitle = product.verifyAllProductsTitle();
		Assert.assertEquals(allProductsTitle, "ALL PRODUCTS");
		product.clickFirstViewProductButton();
		Product productpojo = product.getProductDetails();
		Assert.assertEquals(productpojo.getName(), "Blue Top");
		Assert.assertEquals(productpojo.getCategory(), "Women > Tops");
		Assert.assertEquals(productpojo.getPrice(), "Rs. 500");
		Assert.assertEquals(productpojo.getAvailability(), "In Stock");
		Assert.assertEquals(productpojo.getCondition(), "New");
		Assert.assertEquals(productpojo.getBrand(), "Polo");		
	}
	
	@Test(enabled=false)
	public void TC9SearchProduct()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");	
		ProductPage product = new ProductPage(driver);
		product.clickProductButton();
		String allProductsTitle = product.verifyAllProductsTitle();
		Assert.assertEquals(allProductsTitle, "ALL PRODUCTS");
		product.enterProductName("Winter Top");
		String searchedProductsTitle = product.verifySearchedProducts();
		Assert.assertEquals(searchedProductsTitle, "SEARCHED PRODUCTS");
		List<String> productNames = product.verifyAllTheProductsSearched();
		Assert.assertFalse(productNames.isEmpty(), "No products found!");
		for (String name : productNames) 
		{
			System.out.println(name);
			 Assert.assertTrue(name.contains("Winter Top"));
		}
		
	}
	
	@Test(enabled=false)
	public void TC11VerifySubscriptionInCartPage()
	{
		    driver.manage().deleteAllCookies();
		    LoginPage login = new LoginPage(driver);
		    String homeCheck = login.isHomePageVisible();
		    Assert.assertEquals(homeCheck,"Home");
			CartPage cart = new CartPage(driver);
			cart.clickViewCartButton();
			login.scrollToFooter();
			Assert.assertEquals("SUBSCRIPTION",login.verifyFooterHeader());
			login.enterEmailAndSubscribe("test@gmail.com");
			Assert.assertEquals("You have been successfully subscribed!", login.verifySubscribeMessage());			
	}
		
	@Test(enabled=false)
	public void TC12AddToCart()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");	
		ProductPage product = new ProductPage(driver);
		product.clickProductButton();
		product.firstProductAddToCart();
		product.clickContinueShoppingButton();
		product.secondProductAddToCart();
		product.clickContinueShoppingButton();
		CartPage cart = new CartPage(driver);
		cart.clickViewCartButton();
		int rowCount = cart.verifyAddedProductsCount();
		Assert.assertEquals(2, rowCount);
		List<String[]> tableData = cart.getCartData();
		 String[][] expected = {
			        {"Blue Top Women > Tops", "Rs. 500", "1", "Rs. 500"},
			        {"Men Tshirt Men > Tshirts", "Rs. 400", "1", "Rs. 400"}
			    };
		for (int i = 0; i < expected.length; i++) {
		        Assert.assertEquals(tableData.get(i), expected[i], "Mismatch in row " + (i + 1));
		    }
	}
	
	@Test
	public void TC13VerifyProductQuantityInCart()
	{
		driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");
		ProductPage product = new ProductPage(driver);
		product.clickProductButton();
		String allProductsTitle = product.verifyAllProductsTitle();
		Assert.assertEquals(allProductsTitle, "ALL PRODUCTS");
		product.clickFirstViewProductButton();
		Product productpojo = product.getProductDetails();
		Assert.assertEquals(productpojo.getName(), "Blue Top");
		Assert.assertEquals(productpojo.getCategory(), "Women > Tops");
		Assert.assertEquals(productpojo.getPrice(), "Rs. 500");
		Assert.assertEquals(productpojo.getAvailability(), "In Stock");
		Assert.assertEquals(productpojo.getCondition(), "New");
		Assert.assertEquals(productpojo.getBrand(), "Polo");	
		product.increaseProductQuantity("4");
		product.clickAddToCart();
		product.clickViewCart();
		CartPage cart = new CartPage(driver);
		List<String[]> tableData = cart.getCartData();
		 String[][] expected = {
			        {"Blue Top Women > Tops", "Rs. 500", "4", "Rs. 2000"},
			    };
		 for (int i = 0; i < expected.length; i++) {
		        Assert.assertEquals(tableData.get(i), expected[i], "Mismatch in row " + (i + 1));
		    }
	}
	
	@Test(enabled=false)
	public void TC17RemoveFromCart() throws InterruptedException 
	{
	    driver.manage().deleteAllCookies();
		LoginPage login = new LoginPage(driver);
		String homeCheck = login.isHomePageVisible();
		Assert.assertEquals(homeCheck,"Home");	
		ProductPage product = new ProductPage(driver);
		product.clickProductButton();
		product.firstProductAddToCart();
		product.clickContinueShoppingButton();
		product.secondProductAddToCart();
		product.clickContinueShoppingButton();
		CartPage cart = new CartPage(driver);
		cart.clickViewCartButton();
		Assert.assertTrue(cart.verifyCartPageDisplayed());
		List<String[]> tableData = cart.getCartData();
		String[][] expected = {
		        {"Blue Top Women > Tops", "Rs. 500", "1", "Rs. 500"},
		        {"Men Tshirt Men > Tshirts", "Rs. 400", "1", "Rs. 400"}
		    };
		for (int i = 0; i < expected.length; i++) {
	        Assert.assertEquals(tableData.get(i), expected[i]);
	    }
		cart.removeProductByName(expected[0][0]);
		List<String[]> updatedTable = cart.getCartData();

		Assert.assertFalse(
		    updatedTable.stream()
		        .anyMatch(row -> row[0].replace("\n"," ").contains("Blue Top")),
		    "Product still present"
		);
	}
	
	
	@AfterClass
	public void TearDown()
	{
	driver.quit();
	}
}
	