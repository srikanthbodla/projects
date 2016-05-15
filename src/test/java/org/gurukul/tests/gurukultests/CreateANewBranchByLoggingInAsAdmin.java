package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Libs.LoginLogoutLib;
import Locators.branchesPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;
import core.testcase.SeleniumWDFactory;

/**
 * 
 * This Test is used to create a new branch by logging in as Admin.
 * 
 * @author SrikanthBodla
 *
 */
public class CreateANewBranchByLoggingInAsAdmin extends BaseTest {

	static final Logger logger = Logger.getLogger(CreateANewBranchByLoggingInAsAdmin.class);

	@Test
	public void createANewBranchByLoggingInAsAdmin() {

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
		logger.info("Step 2: Select Branch Option from Entities dropdown");
		driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).click();
		driver.findElement(By.xpath(welcomePageLocators.branch_Option_In_Entities_DropDown)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// validation point
		logger.info(
				"Verify Branches page is displayed, With Create new Branch button, Search field with Search branch button");
		Assert.assertEquals(driver.findElement(By.xpath(branchesPageLocators.query_InputField)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(branchesPageLocators.searchABranch_Button)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(branchesPageLocators.createANewBranch_Button)).isDisplayed(),
				true);

		// Step 3
		logger.info("Step 3: Click on Create new Branch button");
		driver.findElement(By.xpath(branchesPageLocators.createANewBranch_Button)).click();

		// validation point
		logger.info("Verify whterh Create new Branch pop up is displayed");
		new WebDriverWait(driver, 60).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(branchesPageLocators.createOrEditANewBranch_Popup)));

		// Step 4
		logger.info("Step 4: Enter all valid details and click on Save button");
		String branchName = "BranchNameA";
		String branchCode = "BCODE" + getUniqueID();
		waitForSeconds(3);
		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchName);
		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchCode);
		driver.findElement(By.xpath(branchesPageLocators.save_Button_In_CreateOrEditABranch_Popup)).click();

		// validation point
		logger.info("Verify whether Branch is created and view , edit and delete buttons are shown for record");

		Assert.assertEquals(
				driver.findElement(By.xpath(branchesPageLocators.getXpathOfViewButtonOfBranch(branchName, branchCode)))
						.isDisplayed(),
				true);
		Assert.assertEquals(
				driver.findElement(By.xpath(branchesPageLocators.getXpathOfEditButtonOfBranch(branchName, branchCode)))
						.isDisplayed(),
				true);
		Assert.assertEquals(
				isElementPresent(By.xpath(branchesPageLocators.getXpathOfDeleteButtonOfBranch(branchName, branchCode))),
				true);

		// Step 5
		logger.info("Step 5: Logout");
		loginLogoutLib.logout();

	}
}
