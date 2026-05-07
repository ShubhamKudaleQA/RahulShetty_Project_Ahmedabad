package com.qa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDemo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		Actions act = new Actions(driver);
		
		WebElement title=driver.findElement(By.xpath("//h1[text()='Practice Page']"));
		
		WebElement radioBtn=driver.findElement(By.cssSelector("div#radio-btn-example fieldset legend"));
		
		//act.moveToElement(radioBtn).contextClick().perform();
		
		
		act.doubleClick(radioBtn).perform();
		
		act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		
		WebElement input=	driver.findElement(By.cssSelector("input#name"));
		
		act.keyDown(Keys.CONTROL).sendKeys(input,"v").keyUp(Keys.CONTROL).perform();
		act.sendKeys(Keys.BACK_SPACE).perform();
		act.sendKeys(Keys.BACK_SPACE).perform();
		
		

		
		
		//act.contextClick().perform();


	}

}
