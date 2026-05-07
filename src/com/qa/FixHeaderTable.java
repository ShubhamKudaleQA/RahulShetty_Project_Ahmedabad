package com.qa;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FixHeaderTable {

	public static void main(String[] args) throws Exception {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		List<WebElement> tableRow = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr"));

		int row = tableRow.size();

		List<WebElement> tableCol = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/thead/tr/th"));

		int col = tableCol.size();

//		String[][] arr = new String[row][col];

		String exptStr = null;
		String val = null;

//		for (int i = 1; i <= row; i++) {
//			for (int j = 1; j <= col; j++) {
//				WebElement element = driver
//						.findElement(By.xpath("//div[@class='tableFixHead']/table/tbody/tr[" + i + "]/td[" + j + "]"));
//				arr[i - 1][j - 1] = element.getText();
//
//			}
//		}
//
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < col; j++) {
//				if ((arr[i][j]).equalsIgnoreCase("BEN")) {
//					val = arr[i][j];
//				}
//
//				if ((arr[i][j]).equalsIgnoreCase("PUNE")) {
//					exptStr = arr[i][j];
//				}
//			}
//		}

		for (int i = 2; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				WebElement element = driver
						.findElement(By.xpath("//div[@class='tableFixHead']/table/tbody/tr[" + i + "]/td[" + j + "]"));
				System.out.print(element.getText() + " ");

				if (element.getText().equalsIgnoreCase("PUNE")) {
					exptStr = element.getText();
				}

				if (element.getText().equalsIgnoreCase("BEN")) {
					val = element.getText();
				}

			}
			System.out.println();
		}

		WebElement suggestion = driver.findElement(By.cssSelector("input#autocomplete"));
		suggestion.sendKeys(exptStr);

		WebElement alertInput = driver.findElement(By.id("name"));
		alertInput.sendKeys(val);

		driver.findElement(By.id("confirmbtn")).click();
		String str = driver.switchTo().alert().getText();
		System.out.println(((str.split(",")[0]).split(" ")[1]).trim());
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		
	
		
		WebElement alertInput1 = driver.findElement(By.id("name"));
		alertInput1.sendKeys(val);
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
	}

}
