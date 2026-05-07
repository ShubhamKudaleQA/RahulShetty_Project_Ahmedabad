package com.qa;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BackStageDemo {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("https://demo.backstage.io/home");

		WebElement search = driver.findElement(By.xpath("//button[@aria-label='Search']"));

		List<WebElement> link = driver.findElements(RelativeLocator.with(By.tagName("a")).below(search));

		for (WebElement ele : link) {

			if (ele.getText().contains("Create")) {
				ele.click();
				System.out.println(ele.getText() + " is clicked...");
				break;
			}

		}

		driver.findElement(By.xpath("//button[@aria-label='Support']")).click();
		WebElement val = driver.findElement(By.xpath("//span[text()='Backstage News']"));
		driver.findElement(RelativeLocator.with(By.tagName("a")).below(val)).click();

		String parentWin = driver.getWindowHandle();
		System.out.println("Parent Win Title: " + driver.getTitle());

		Set<String> allWin = driver.getWindowHandles();

		for (String str : allWin) {
			if (!str.equals(parentWin)) {
				driver.switchTo().window(str);
				System.out.println("Child win title: " + driver.getTitle());
				break;
			}
		}

		driver.switchTo().window(parentWin);

		driver.findElement(By.xpath("//span[text()='Close']")).click();

	}

}
