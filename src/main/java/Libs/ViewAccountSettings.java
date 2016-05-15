package Libs;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Locators.homePageLocators;
import Locators.sessionsPageLocators;
import Locators.settingsPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;

public class ViewAccountSettings extends BaseTest {

	@Test
	public void viewAccountSettings() {

		// Step 1
		System.out.println("Step 1: Login from Accounts tab");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.loginFromAccountsTab(baseURL, "admin", "admin");

		// validation point
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		System.out.println("Step 2: Navigate to Settings");
		driver.findElement(By.xpath(welcomePageLocators.Account_Tab)).click();
		driver.findElement(By.xpath(welcomePageLocators.settings_Option_In_Account_Tab)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Settings"));

		// validation points
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.firstName_Input_Field)).getAttribute("value").trim(),
				"Administrator");
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.lastName_Input_Field)).getAttribute("value").trim(),
				"Administrator");
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.email_Input_Field)).getAttribute("value").trim(),
				"admin@localhost");
		Assert.assertEquals(new Select(driver.findElement(By.xpath(settingsPageLocators.language_Select_Field)))
				.getFirstSelectedOption().getText().trim(), "English");

		// Step 3
		System.out.println("Step 3: Update values in Settings page and click on Save button");
		driver.findElement(By.xpath(settingsPageLocators.firstName_Input_Field)).clear();
		driver.findElement(By.xpath(settingsPageLocators.firstName_Input_Field)).sendKeys("Administrator1");
		driver.findElement(By.xpath(settingsPageLocators.lastName_Input_Field)).clear();
		driver.findElement(By.xpath(settingsPageLocators.lastName_Input_Field)).sendKeys("Administrator1");
		driver.findElement(By.xpath(settingsPageLocators.email_Input_Field)).clear();
		driver.findElement(By.xpath(settingsPageLocators.email_Input_Field)).sendKeys("admin1@localhost");
		driver.findElement(By.xpath(settingsPageLocators.save_button)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Settings"));

		// validation points
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.firstName_Input_Field)).getAttribute("value").trim(),
				"Administrator1");
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.lastName_Input_Field)).getAttribute("value").trim(),
				"Administrator1");
		Assert.assertEquals(
				driver.findElement(By.xpath(settingsPageLocators.email_Input_Field)).getAttribute("value").trim(),
				"admin1@localhost");

		// Step 4
		System.out.println("Step 4: Navigate to Sessions page");
		driver.findElement(By.xpath(welcomePageLocators.Account_Tab)).click();
		driver.findElement(By.xpath(welcomePageLocators.settings_Option_In_Account_Tab)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Sessions"));

		// validation points
		Assert.assertEquals(isElementPresent(By.xpath(sessionsPageLocators.getSessionRecord())), true);

		// Step 5
		System.out.println("Step 5: Logout");
		loginLogoutLib.logout();
	}
}
