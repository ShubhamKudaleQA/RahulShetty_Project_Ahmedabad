package com.qa;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RSAutomationPracWeb {

	public void radioButton(WebDriver driver) {

		System.out.println(driver.findElement(By.cssSelector("div#radio-btn-example fieldset legend")).getText());
		System.out.println("-------------------");

		List<WebElement> allRadioBtn = driver.findElements(By.cssSelector("input[class='radioButton']"));

		String radioValue = "radio2";
		for (WebElement radio : allRadioBtn) {
			if (radio.getDomAttribute("value").equalsIgnoreCase(radioValue)) {
				radio.click();
				System.out.println(radio.getDomAttribute("value") + " is clicked");
				break;
			}
		}
		System.out.println();

	}

	public void checkBox(WebDriver driver) {

		System.out.println(driver.findElement(By.cssSelector("div#checkbox-example fieldset legend")).getText());
		System.out.println("-------------------");

		List<WebElement> allCheckBox = driver.findElements(By.cssSelector("input[type='checkbox']"));

		String checkBoxValue = "option2";
		for (WebElement checkBox : allCheckBox) {
			if (!checkBox.getDomAttribute("value").equalsIgnoreCase(checkBoxValue)) {
				checkBox.click();
				System.out.println(checkBox.getDomAttribute("value") + " is clicked");
			}
		}
		System.out.println();
	}

	public void dropDown(WebDriver driver) {

		System.out.println(driver.findElement(By.cssSelector("div.cen-right-align fieldset legend")).getText());
		System.out.println("-------------------");

		WebElement dropDownVal = driver.findElement(By.id("dropdown-class-example"));

		Select dropDown = new Select(dropDownVal);

		List<WebElement> allDropDownValue = dropDown.getOptions();

		for (WebElement drop : allDropDownValue) {
			System.out.print(drop.getText() + " ");
		}
		dropDown.selectByValue("option1");
		System.out.println(dropDown.getFirstSelectedOption().getText() + " is selected");
		System.out.println();
	}

	public void autoSuggestion(WebDriver driver) {

		System.out.println(driver.findElement(By.cssSelector("div#select-class-example fieldset legend")).getText());
		System.out.println("-------------------");

		driver.findElement(By.id("autocomplete")).sendKeys("jap");

		List<WebElement> allCountrySuggestion = driver.findElements(By.cssSelector("li.ui-menu-item"));

		String countryName = "Japan";
		for (WebElement suggestion : allCountrySuggestion) {
			if (suggestion.getText().equalsIgnoreCase(countryName)) {
				System.out.println(suggestion.getText() + " is selected");
				suggestion.click();
				break;
			}
		}
		System.out.println();
	}

	public void switchToAlert(WebDriver driver, String str) {

		WebElement inputField = driver.findElement(By.id("name"));
		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(inputField)).getText());
		System.out.println("-------------------");

		inputField.sendKeys(str);
		driver.findElement(By.id("alertbtn")).click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		inputField.sendKeys(str);

		driver.findElement(By.id("confirmbtn")).click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().dismiss();

		System.out.println();
	}

	public void switchWindow(WebDriver driver, WebDriverWait wait) {

		System.out.println(driver
				.findElement(RelativeLocator.with(By.tagName("legend")).above(By.cssSelector("button#openwindow")))
				.getText());
		System.out.println("-------------------");

		String parentWin = driver.getWindowHandle();
		System.out.println("Title is: " + driver.getTitle());
		driver.findElement(By.id("openwindow")).click();

		Set<String> allWinId = driver.getWindowHandles();

		for (String win : allWinId) {

			if (!win.equals(parentWin)) {
				driver.switchTo().window(win);
				System.out.println("Child Win Title is: " + driver.getTitle());
				System.out.println("Child Win URL is: " + driver.getCurrentUrl());
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
				System.out.println(driver.findElement(By.tagName("h1")).getText());
			}
			driver.switchTo().window(parentWin);
		}
		System.out.println();
	}

	public void switchTab(WebDriver driver, WebDriverWait wait) {

		System.out.println(driver.findElement(By.cssSelector("div.cen-align fieldset legend")).getText());
		System.out.println("-------------------");

		String parentTab = driver.getWindowHandle();
		System.out.println("Parent Tab Title is: " + driver.getTitle());
		driver.findElement(By.id("opentab")).click();

		Set<String> allTabId = driver.getWindowHandles();

		for (String tab : allTabId) {

			if (!tab.equals(parentTab)) {

				driver.switchTo().window(tab);
				System.out.println("Child Tab Title is: " + driver.getTitle());
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
				System.out.println(driver.findElement(By.tagName("h1")).getText());
			}

			driver.switchTo().window(parentTab);
		}
		System.out.println();
	}

	public String webTable(WebDriver driver) {

		WebElement inputField = driver.findElement(By.id("product"));

		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(inputField)).getText());
		System.out.println("-------------------");

		int row = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr")).size();
		int column = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[1]/th")).size();
		String expStr = null;
		for (int i = 2; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				String value = driver
						.findElement(By.xpath("//table[@name='courses']/tbody/tr[" + i + "]/td[" + j + "]")).getText();

				if (value.contains("WebSecurity Testing")) {
					expStr = value;
					break;
				}
			}
		}
		System.out.println(expStr);
		System.out.println();
		return expStr;
	}

	public String fixedHeaderWebTable(WebDriver driver) {

		WebElement fixedTbl = driver.findElement(By.className("tableFixHead"));

		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(fixedTbl)).getText());
		System.out.println("-------------------");

		int row = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr")).size();
		int column = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr[1]/td")).size();
		String expStr = null;
		int sum = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				String value = driver
						.findElement(By.xpath("//div[@class='tableFixHead']/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getText();

				if (value.contains("Ronaldo")) {
					expStr = value;
					break;
				}

			}

			String value = driver.findElement(By.xpath("//div[@class='tableFixHead']/table/tbody/tr[" + i + "]/td[4]"))
					.getText();
			int amount = Integer.parseInt(value);
			sum += amount;
		}
		String total = driver.findElement(By.cssSelector("div.totalAmount")).getText();
		int totalAmount = Integer.parseInt((total.split(":"))[1].trim());
		if (totalAmount == sum) {
			System.out.println("Total amount: " + totalAmount);
		}

		System.out.println();
		return expStr;
	}

	public void elementDisplay(WebDriver driver, String str, Actions act) {
		WebElement hide = driver.findElement(By.id("hide-textbox"));
		WebElement show = driver.findElement(By.id("show-textbox"));
		WebElement input = driver.findElement(By.cssSelector("input#displayed-text"));

		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(hide)).getText());
		System.out.println("-------------------");

		hide.click();
		boolean isHide = input.isDisplayed();
		System.out.println("Element is Display: " + isHide);

		act.scrollToElement(show).perform();
		show.click();
		boolean isShow = input.isDisplayed();
		System.out.println("Element is Display: " + isShow);

		input.sendKeys(str);
		System.out.println(input.getText());

		System.out.println();
	}

	public void mouseHover(WebDriver driver, Actions act) {

		WebElement mouse = driver.findElement(By.id("mousehover"));

		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(mouse)).getText());
		System.out.println("-------------------");

		act.scrollToElement(driver.findElement(By.xpath("//legend[contains(text(),'iFrame Example')]"))).perform();
		act.moveToElement(mouse).perform();

		List<WebElement> mouseContent = driver.findElements(By.cssSelector("div.mouse-hover-content a"));
		for (WebElement content : mouseContent) {
			if (content.getText().equalsIgnoreCase("Top")) {
				System.out.println(content.getText() + " is clicked");
				content.click();
				break;
			}

		}

		System.out.println();
	}

	public void iframe(WebDriver driver, Actions act, WebDriverWait wait) throws Exception {
		WebElement iframe = driver.findElement(By.id("courses-iframe"));
		System.out.println(driver.findElement(RelativeLocator.with(By.tagName("legend")).above(iframe)).getText());
		System.out.println("-------------------");

		act.moveToElement(iframe).perform();
		driver.switchTo().frame("courses-iframe");

		System.out.println(driver.findElement(By.cssSelector("ul[class='clearfix'] li")).getText());

		List<WebElement> iframeLink = driver.findElements(By.cssSelector("ul.navigation.clearfix li a"));

		for (WebElement link : iframeLink) {
			if (!link.getText().equals("")) {
				if (link.getText().equalsIgnoreCase("Courses")) {
					link.click();
					break;
				}
			}
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']")));
		driver.findElement(By.cssSelector("input[type='search']")).sendKeys("Selenium");
		driver.navigate().back();
		driver.switchTo().parentFrame();

//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.login-btn a.theme-btn.register-btn")))	;	
//		driver.findElement(By.cssSelector("div.login-btn a.theme-btn.register-btn")).click();
		System.out.println();
	}

	public void footerLink(WebDriver driver, Actions act) {

		System.out.println("Footer Section Link");
		System.out.println("-------------------");

		WebElement footer = driver.findElement(By.cssSelector("table.gf-t"));
		act.scrollToElement(footer).perform();

		WebElement coloumnDriver = footer.findElement(By.xpath("//table[@class='gf-t']/tbody/tr/td[1]/ul"));
		List<WebElement> allFooterLink = coloumnDriver.findElements(By.tagName("a"));

		for (WebElement link : allFooterLink) {
			System.out.println(link.getText());
			act.keyDown(Keys.CONTROL).click(link).perform();
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Actions act = new Actions(driver);
		driver.manage().deleteAllCookies();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		RSAutomationPracWeb rs = new RSAutomationPracWeb();

		String str = rs.webTable(driver);
		rs.radioButton(driver);
		rs.checkBox(driver);
		rs.dropDown(driver);
		rs.autoSuggestion(driver);
		rs.switchWindow(driver, wait);	
		rs.switchTab(driver, wait);
		String name = rs.fixedHeaderWebTable(driver);
		rs.switchToAlert(driver, name);
		rs.elementDisplay(driver, str, act);
		rs.mouseHover(driver, act);
		rs.iframe(driver, act, wait);
		rs.footerLink(driver, act);
	}

}
