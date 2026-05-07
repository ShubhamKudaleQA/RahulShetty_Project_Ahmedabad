package com.qa;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("https://www.flipkart.com/");

		driver.findElement(By.cssSelector("span.b3wTlE")).click();

		WebElement search = driver.findElement(By.cssSelector("div.Afujtw input"));

		Actions act = new Actions(driver);
		
		act.moveToElement(search).click().sendKeys("Phones").keyDown(Keys.ENTER).perform();

		List<WebElement> oldProduct = driver.findElements(By.cssSelector("div.RG5Slk"));
		
		driver.findElement(By.xpath("//div[contains(@class,'tx4xZf') and @title='Apple']")).click();
		
		wait.until(ExpectedConditions.stalenessOf(oldProduct.get(0)));
		
		List<WebElement> newProduct = driver.findElements(By.cssSelector("div.RG5Slk"));

		System.out.println("Count of Phones: "+newProduct.size());

		int count = 0;
		String input= "Apple";
		for (WebElement ele : newProduct) {
//			System.out.println(ele.getText().split(" ")[0].trim());		
			if (ele.getText().contains(input)) {
				count++;
			}
		}
		
		System.out.println("Count of "+input+" : "+count);


	}

}
