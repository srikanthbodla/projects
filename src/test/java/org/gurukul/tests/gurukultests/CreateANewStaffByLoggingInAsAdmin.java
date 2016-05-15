package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Libs.LoginLogoutLib;
import Locators.staffsPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;
import core.testcase.SeleniumWDFactory;

/**
 * This test case will create a staff by logging in as admin
 * @author Srikanthbodla
 *
 */
public class CreateANewStaffByLoggingInAsAdmin extends BaseTest {

	static final Logger logger = Logger.getLogger(CreateANewBranchByLoggingInAsAdmin.class);
	
	@Test
	public void createANewStaffByLoggingInAsAdmin() {

		WebDriver driver = SeleniumWDFactory.getDriver();

		// Step 1
		logger.info("Step 1: Login app");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.login(baseURL, "admin", "admin");

		// validation point
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Select Staff Option from Entities dropdown");
		driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).click();
		driver.findElement(By.xpath(welcomePageLocators.staff_Option_In_Entities_DropDown)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// validation point
		logger.info(
				"Verify Branches page is displayed, With Create new Staff button, Search field with Search staff button");
		Assert.assertEquals(driver.findElement(By.xpath(staffsPageLocators.query_InputField)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(staffsPageLocators.searchAStaff_Button)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(staffsPageLocators.createANewStaff_Button)).isDisplayed(), true);

		// Step 3
		logger.info("Step 3: Click on Create new Staff button");
		driver.findElement(By.xpath(staffsPageLocators.createANewStaff_Button)).click();

		// validation point
		logger.info("Verify whterh Create new Staff pop up is displayed");
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(staffsPageLocators.createOrEditANewStaff_Popup)));

		// Step 4
		logger.info("Step 4: Enter all valid details and click on Save button");
		String branch = "BranchNameA";
		String staffName = "staffA";
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup)).sendKeys(staffName);
		new Select(driver.findElement(By.xpath(staffsPageLocators.branch_dropdown_In_CreateOrEditANewStaff_Popup)))
				.selectByVisibleText(branch);

		driver.findElement(By.xpath(staffsPageLocators.save_Button_In_CreateOrEditANewStaff_Popup)).click();

		// validation point
		logger.info("Verify whether Staff is created and view , edit and delete buttons are shown for record");

		Assert.assertEquals(driver
				.findElement(By.xpath(staffsPageLocators.getXpathOfViewButtonOfStaff(staffName, branch))).isDisplayed(),
				true);
		Assert.assertEquals(driver
				.findElement(By.xpath(staffsPageLocators.getXpathOfEditButtonOfStaff(staffName, branch))).isDisplayed(),
				true);
		Assert.assertEquals(
				isElementPresent(By.xpath(staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branch))), true);

		// Step 5
		logger.info("Step 5: Logout");
		loginLogoutLib.logout();
	}
}
