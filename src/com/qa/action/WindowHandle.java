package com.qa.action;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandle {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/");

		WebElement move = driver.findElement(By.id("nav-link-accountList"));
		Actions act = new Actions(driver);
		act.moveToElement(move).keyDown(Keys.CONTROL).click().perform();

		// By using Iterator Method
		Set<String> win = driver.getWindowHandles();
		Iterator<String> itr = win.iterator();
		String parentWin = itr.next();
		String childWin = itr.next();

		driver.switchTo().window(childWin);

//		By using For loop
//		String parentWin = driver.getWindowHandle();
//		Set<String> window = driver.getWindowHandles();
//		for (String win : window) {
//
//			if (!win.equals(parentWin)) {
//				driver.switchTo().window(win);
//
//			}
//		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
		WebElement signIn = driver.findElement(By.cssSelector("input[name='email']"));

		signIn.sendKeys("9975023011");

//		System.out.println(driver.findElement(By.xpath("//h1[contains(text(),'create account')]")).getText());

		driver.findElement(By.id("continue")).click();

	}

}
