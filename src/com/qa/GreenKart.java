package com.qa;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class GreenKart {

	public static void addItems(WebDriver driver, String[] itemNeeded, WebDriverWait wait) {

//		Scanner input = new Scanner(System.in);
//		System.out.println("Enter no of item required: ");
//		int numOfItem = input.nextInt();
//		input.nextLine();
//
//		String[] itemNeeded = new String[numOfItem];
//
//		for (int i=0;i<numOfItem;i++) {
//			System.out.println("Enter item: "+(i+1)+": ");
//			itemNeeded[i] = input.nextLine();
//		}

		List<String> itemNeedList = Arrays.asList(itemNeeded);

		List<WebElement> nameVeg = driver.findElements(By.cssSelector("h4.product-name"));
		List<WebElement> addToCart = driver.findElements(By.cssSelector("div.product-action button"));

		wait.until(ExpectedConditions.visibilityOfAllElements(addToCart));

		int j = 0;
		for (int i = 0; i < nameVeg.size(); i++) {
			String selVeg = nameVeg.get(i).getText().split("-")[0].trim();
			if (itemNeedList.contains(selVeg)) {
				j++;
				// wait.until(ExpectedConditions.visibilityOf(addToCart.get(i)));
				addToCart.get(i).click();
				if (j == itemNeeded.length) {
					break;
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

		String[] itemNeeded = { "Beans", "Mushroom", "Potato", "Carrot" };

		addItems(driver, itemNeeded, wait);

		driver.findElement(By.cssSelector("a.cart-icon img")).click();

		WebElement allItemAdded = driver.findElement(By.cssSelector("ul.cart-items"));

		File srcFile = allItemAdded.getScreenshotAs(OutputType.FILE);
		File destFile = new File(
				"C:\\Users\\shubh\\OneDrive\\Pictures\\Screenshots\\Selenium Screenshot\\ScreenShot.png");

		Files.copy(srcFile, destFile);

		driver.findElement(By.cssSelector("div.action-block button")).click();

		WebElement applyBtn = driver.findElement(By.cssSelector("div.promoWrapper button"));
		wait.until(ExpectedConditions.visibilityOf(applyBtn));

		driver.findElement(By.cssSelector("div.promoWrapper input")).sendKeys("rahulshettyacademy..");
		wait.until(ExpectedConditions.elementToBeClickable(applyBtn));

		applyBtn.click();

		WebElement promoCodeMsg = driver.findElement(By.className("promoInfo"));
		wait.until(ExpectedConditions.visibilityOf(promoCodeMsg));
		System.out.println(promoCodeMsg.getText());

		// System.out.println("No. of Items:"+driver.findElement(By.xpath("//b[text()='No. of Items : ']")).getText());
		System.out.println("Total Amount: " + driver.findElement(By.xpath("//span[@class='totAmt']")).getText());
		System.out.println("Discounted Percentage: " + driver.findElement(By.xpath("//span[@class='discountPerc']")).getText());
		System.out.println("Discount Amount: " + driver.findElement(By.xpath("//span[@class='discountAmt']")).getText());
		driver.findElement(By.xpath("//span[@class='totAmt']/following-sibling::button")).click();

		WebElement countryDropdown = driver.findElement(By.xpath("//div[@class='wrapperTwo']/div/select"));
		Select country = new Select(countryDropdown);
		country.selectByValue("India");

		WebElement checkBox = driver.findElement(By.className("chkAgree"));
		//boolean isCheckSelected = checkBox.isSelected();
		checkBox.click();
		driver.findElement(By.xpath("//span[@class='errorAlert']/following-sibling::button")).click();

//		if (isCheckSelected == false) {
//			System.out.println(driver.findElement(By.xpath("//span[@class='errorAlert']/b")).getText());
//		}

		// driver.close();

	}

}
