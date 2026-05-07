package com.qa;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail {

	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.com/");

		driver.findElement(By.xpath("//div[@id='gbwa']/div/a")).click();

		WebElement frame = driver.findElement(By.xpath("//iframe[@role='presentation']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(frame));
		
		driver.switchTo().frame(frame);

		driver.findElement(By.xpath("//span[text()='Gmail']")).click();
		driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//span[text()='Sign in']")).click();

		String parentWindow = driver.getWindowHandle();

		Set<String> allWindow = driver.getWindowHandles();

		for (String winId : allWindow) {
			if (!winId.equals(parentWindow)) {
				driver.switchTo().window(winId);
				driver.findElement(By.id("identifierId")).sendKeys("kshoo7214@gmail.com");
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				break;
			}
		}

	}

}
