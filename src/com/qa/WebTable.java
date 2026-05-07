package com.qa;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args)  {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		List<WebElement> tableRow = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr"));

		int row = tableRow.size();

		List<WebElement> tableCol = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr/th"));

		int col = tableCol.size();
		String exptStr = null;
		String val = null;

		for (int i = 2; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				WebElement element = driver.findElement(By.xpath("//table[@name='courses']/tbody/tr["+i+"]/td["+j+"]"));
				
				if (element.getText().contains("Selenium Webdriver")) {
					exptStr =element.getText();
					System.out.println(exptStr);
				}
				
				if (element.getText().contains("Appium (Selenium)")) {
					val =(element.getText().split(" "))[0];
					System.out.println(val);
				}

			}
		}
		
		
		WebElement suggestion =driver.findElement(By.cssSelector("input#autocomplete"));
		suggestion.sendKeys(exptStr);
		
		WebElement alertInput =driver.findElement(By.id("name"));
		alertInput.sendKeys(val);

		driver.close();

		
		
		
	}
	

}
