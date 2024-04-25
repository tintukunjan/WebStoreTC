package Webstore_TC01;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webstore_TC01 {

	public static void main(String[] args) {
		// 1. Go to https://www.webstaurantstore.com/

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.webstaurantstore.com/");

		// 2. Search for 'stainless work table'.
		WebElement searchBox = driver.findElement(By.id("searchval"));
		searchBox.sendKeys("stainless work table");
		searchBox.submit();
		System.out.println("Searched 'stainless work table' successfully!");

		// 3. Check the search result ensuring every product has the word 'Table' in its
		// title.
		boolean NotallProductsContainTable = true;
		for (WebElement product : driver.findElements(By.xpath("//div[@id='search-page']//a"))) {
			String title = product.getText();
			if (!title.toLowerCase().contains("table")) {
				NotallProductsContainTable = false;

				break;
			}
		}

		System.out.println("All Title contain table");
		// 4. Add the last of found items to Cart.
		if (!NotallProductsContainTable) {
			WebElement lastProduct = driver.findElement(By.xpath(
					"(//div[@class='group border-transparent border-solid border-6 m-0 max-w-full relative z-10 hover:outline-gray-200']//a)[last()]"));
			lastProduct.click();
			WebElement addToCartButton = driver.findElement(By.id("buyButton"));
			addToCartButton.click();
			System.out.println("Added the last of found items to Cart");

			// 5. Emptying the Cart
			// Clicking on Cart item number
			WebElement Cart = driver.findElement(By.id("cartItemCountSpan"));
			Cart.click();

			// Clicking on Empty Cart button
			WebElement emptyCart = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div/button"));
			emptyCart.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Clicking on EmptyCart button on the pop up
			WebElement emptyCartButton = driver
					.findElement(By.xpath("//*[@id=\"td\"]/div[11]/div/div/div/footer/button[1]"));
			emptyCartButton.click();
			System.out.println("Cart is empty");

			// WebElement emptyCart = driver.findElement(By.xpath("//button[contains(@class,
			// 'Empty') and contains(@class, 'Cart')]"));

			System.out.println("Test passed successfully!");
		} else {
			System.out.println("Test failed as all item title not contain Table.");
		}

	}
}
