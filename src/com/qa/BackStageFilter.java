package com.qa;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BackStageFilter {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Scanner input = new Scanner(System.in);

		driver.get("https://demo.backstage.io/home");

		WebElement search = driver.findElement(By.xpath("//button[@aria-label='Search']"));

		List<WebElement> link = driver.findElements(RelativeLocator.with(By.tagName("a")).below(search));

		for (WebElement ele : link) {

			if (ele.getText().contains("Catalog")) {
				ele.click();
				System.out.println(ele.getText() + " is clicked...");
				break;
			}

		}

			while (true) {
				System.out.println("Enter string value: ");

				String str = input.next();

				List<WebElement> oldLinks = driver
						.findElements(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]/tr/td[1]/a"));
				System.out.println(oldLinks.size());
				driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(str);
				//Thread.sleep(3000);
				wait.until(ExpectedConditions.stalenessOf(oldLinks.get(0)));
				System.out.println(driver
						.findElements(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]/tr/td[1]/a")).size());
				driver.findElement(By.cssSelector("button[aria-label='Clear Search']")).click();
			}


	}

}
