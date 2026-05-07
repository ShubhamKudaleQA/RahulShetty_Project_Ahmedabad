package com.qa;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Backstage {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://demo.backstage.io/home");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

//		Scanner input = new Scanner(System.in);
//		System.out.println("Enter Submodule Name: ");

		String value = "catalog";

		List<WebElement> list = driver.findElements(By.xpath("//button[@aria-label='Search']/following-sibling::a"));

		for (WebElement e : list) {
			if (e.getAttribute("aria-label").equalsIgnoreCase(value)) {
				e.click();
				System.out.println("Clicked on " + e.getAttribute("aria-label"));
				break;
			}
		}

		driver.navigate().refresh();
		
		Thread.sleep(5000);
		WebElement backstageLink = driver.findElement(By.xpath("//*[text()='backstage']"));
		backstageLink.click();

//		WebElement create = driver.findElement(By.xpath("//a[@role='button']"));
//		create.click();

		driver.findElement(By.xpath("//div[@aria-haspopup='listbox'] //p[text()='Component']")).click();

//		System.out.println("Enter dropdown name you want to click: ");
//		String dropdownValue = input.next();

		List<WebElement> dropdownList = driver.findElements(By.cssSelector("ul[role='listbox'] li"));

		for (WebElement dropdown : dropdownList) {
			if (dropdown.getText().equalsIgnoreCase("System")) {
				dropdown.click();
				System.out.println(dropdown.getText() + " dropdown Clicked");
				break;
			}
		}

		driver.findElement(By.cssSelector("button[title='Open']")).click();

//		List<WebElement> li =driver.findElements(By.cssSelector("button[title='Open']"));
//		System.out.println(li.size());
//		for (WebElement ele : li) {
//			System.out.println(ele.getDomAttribute("role"));
//		}

	}

}
