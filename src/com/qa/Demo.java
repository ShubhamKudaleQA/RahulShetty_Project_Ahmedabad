package com.qa;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("https://www.google.com/");

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter String: ");
		String input = sc.next();

		WebElement inputText = driver.findElement(By.cssSelector("textarea.gLFyf"));
		inputText.sendKeys(input);

		String output = driver.findElement(By.xpath("//ul[@class='G43f7e']/li[3]")).getText();

		System.out.println(output);

	}

}
