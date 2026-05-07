package com.qa;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		WebElement dropDown = driver.findElement(By.id("dropdown-class-example"));

		String expt = "option3";
		Select sc = new Select(dropDown);
		List<WebElement> options = sc.getOptions();

		for (WebElement dropEle : options) {
			System.out.println(dropEle.getText());

			if (dropEle.getText().equalsIgnoreCase(expt)) {
				dropEle.click();
				break;
			}

		}



	}

}
