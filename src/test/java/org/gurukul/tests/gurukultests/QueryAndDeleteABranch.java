package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Libs.BranchLib;
import Libs.LoginLogoutLib;
import Locators.branchesPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;
import core.testcase.SeleniumWDFactory;

/**
 * This test will query/search for a branch and delete it
 * 
 * @author Srikanthbodla
 *
 */
public class QueryAndDeleteABranch extends BaseTest {

	static final Logger logger = Logger.getLogger(QueryAndDeleteABranch.class);

	@Test
	public void queryAndDeleteABranch() {

		WebDriver driver = SeleniumWDFactory.getDriver();

		// Step 1
		logger.info("Step 1: Login app");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.login(baseURL, "admin", "admin");

		// validation points
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Create a branch");

		String branchName = "BranchNameD";
		String branchCode = "BCODE" + getUniqueID();
		logger.info(branchName + branchCode);

		BranchLib branchLib = new BranchLib();
		branchLib.createBranchFromWelcomePage(branchName, branchCode);

		// validation points
		logger.info("Verify Delete button is displayed for Branch created above");
		String xpathOfDeleteButtonOfBranch = branchesPageLocators.getXpathOfDeleteButtonOfBranch(branchName,
				branchCode);
		Assert.assertTrue(driver.findElement(By.xpath(xpathOfDeleteButtonOfBranch)).isDisplayed());

		// Step 3
		logger.info("Step3: Query for the Above created branch");
		branchLib.queryForBranchInBranchesList(branchName);

		// validation points
		logger.info("Verify whether appropriate branch is fetched");
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfDeleteButtonOfBranch)));

		// Step 4
		logger.info("Step 4: Click on Delete button of the branch");
		driver.findElement(By.xpath(xpathOfDeleteButtonOfBranch)).click();

		// validation points
		logger.info("Verify whether Delete confirmation message is displayed with Cancel and Delete buttons");
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(branchesPageLocators.delete_Branch_Confirmation_Message)));

		Assert.assertEquals(isElementPresent(By.xpath(branchesPageLocators.delete_Branch_Confirmation_Message)), true);
		Assert.assertTrue(isElementPresent(By.xpath(branchesPageLocators.cancel_Button_In_Delete_Branch_Confirmation)));
		Assert.assertTrue(isElementPresent(By.xpath(branchesPageLocators.delete_Button_In_Delete_Branch_Confirmation)));

		// Step 5
		logger.info("Step 5: Click on cancel button");
		driver.findElement(By.xpath(branchesPageLocators.cancel_Button_In_Delete_Branch_Confirmation)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// validation points
		Assert.assertTrue(isElementPresent(By.xpath(xpathOfDeleteButtonOfBranch)));

		// Step 6
		System.out
				.println("Step 6: Click on Delete button of branch and click on delete button in delete confirmation");
		waitForSeconds(30);
		driver.findElement(By.xpath(xpathOfDeleteButtonOfBranch)).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(branchesPageLocators.delete_Branch_Confirmation_Message)));
		driver.findElement(By.xpath(branchesPageLocators.delete_Button_In_Delete_Branch_Confirmation)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// validation points
		waitForSeconds(30);
		Assert.assertTrue(isElementNotPresent(By.xpath(xpathOfDeleteButtonOfBranch)));

		// Step 7
		logger.info("Step 7: Query for the deleted branch and check for the branch");
		branchLib.queryForBranchInBranchesList(branchName);

		// validation points
		Assert.assertTrue(isElementNotPresent(By.xpath(xpathOfDeleteButtonOfBranch)));

		// Step 8
		logger.info("Step 8: Logout");
		loginLogoutLib.logout();

	}
}
