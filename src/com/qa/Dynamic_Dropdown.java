package com.qa;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dynamic_Dropdown {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://www.google.com/");
		driver.findElement(By.className("gLFyf")).sendKeys("Selenium Automation");
		List<WebElement> allAutoSuggestion = driver.findElements(By.xpath("//ul[@class=\"G43f7e\"]/li"));
		System.out.println(allAutoSuggestion.size());

		for (WebElement autoSug : allAutoSuggestion) {
			String expt = "selenium automation framework";
			System.out.println(autoSug.getText());
			if (autoSug.getText().equalsIgnoreCase(expt)) {
				autoSug.click();
				break;
			}
		}

		driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");

		WebElement suggestion = driver.findElement(By.cssSelector("input#autocomplete"));
		suggestion.sendKeys("ind");

		List<WebElement> country = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));

		for (WebElement sugg : country) {
			System.out.println(sugg.getText());
			String expt = "india";

			if (sugg.getText().equalsIgnoreCase(expt)) {
				sugg.click();
				break;
			}

		}

	}

}
