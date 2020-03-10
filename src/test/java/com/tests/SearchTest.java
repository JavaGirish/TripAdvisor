package com.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {

	static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.tripadvisor.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void test1() {
		driver.findElement(By.xpath("//input[@title='Search']")).click();
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Club Mahindra");
		driver.findElement(By.xpath("//button[@type='submit']//span")).click();
		driver.findElement(By.xpath("//div[@class='result inner-columns-wrapper']")).click();
		switchToWindow();
		driver.findElement(By.xpath("//a[contains(text(),'Write')]")).click();
		switchToWindow();
		Actions ax = new Actions(driver);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i <=3; i++) {
			ax.moveToElement(driver.findElement(By.id("bubble_rating"))).build().perform();
		}
		driver.findElement(By.id("bubble_rating")).click();
		Assert.assertTrue(driver.findElement(By.id("DQ_RATINGS")).isDisplayed());

	}

//	@AfterMethod
//	public void tearDown() {
//
//		driver.quit();
//
//	}
	
	
	
	
	public static void switchToWindow() {
		
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(tabs2.size()-1));
	   
	
	}

}
