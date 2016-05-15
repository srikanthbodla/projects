package Libs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.authenticationPageLocators;
import Locators.homePageLocators;
import core.testcase.SeleniumWDFactory;

public class LoginLogoutLib {

	/**
	 * This method is used to login the application with given username and
	 * password
	 * 
	 * @param url
	 * @param userName
	 * @param password
	 */
	public void login(String url, String userName, String password) {

		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.get(url);
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(homePageLocators.Login_Link)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Authentication"));

		driver.findElement(By.xpath(authenticationPageLocators.login_Input_Field)).sendKeys(userName);
		driver.findElement(By.xpath(authenticationPageLocators.password_Input_Field)).sendKeys(password);
		driver.findElement(By.xpath(authenticationPageLocators.authenticate_Button)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));
	}

	public void loginFromAccountsTab(String url, String userName, String password) {

		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.get(url);
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement accountTab = driver.findElement(By.xpath(homePageLocators.Account_Tab));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", accountTab);
		driver.findElement(By.xpath(homePageLocators.Account_Tab)).click();
		driver.findElement(By.xpath(homePageLocators.Authenticate_Option_In_Account_Tab)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Authentication"));

		driver.findElement(By.xpath(authenticationPageLocators.login_Input_Field)).sendKeys(userName);
		driver.findElement(By.xpath(authenticationPageLocators.password_Input_Field)).sendKeys(password);
		driver.findElement(By.xpath(authenticationPageLocators.authenticate_Button)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));
	}

	/**
	 * This method is used to logout of the application
	 */
	public void logout() {
		WebDriver driver = SeleniumWDFactory.getDriver();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(homePageLocators.Account_Tab)));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement accountTab = driver.findElement(By.xpath(homePageLocators.Account_Tab));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", accountTab);

		driver.findElement(By.xpath(homePageLocators.Account_Tab)).click();
		driver.findElement(By.xpath(homePageLocators.Logout_link_In_Account_options)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));
	}

}
