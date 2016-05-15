package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Libs.BranchLib;
import Libs.LoginLogoutLib;
import Libs.StaffLib;
import Locators.staffsPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;

/**
 * This test will query/search for staff and will delete it
 * 
 * @author Srikanthbodla
 *
 */
public class QueryAndDeleteAStaff extends BaseTest {

	static final Logger logger = Logger.getLogger(QueryAndDeleteABranch.class);

	@Test
	public void queryAndDeleteStaff() {

		// Step 1
		logger.info("Step 1: Login app");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.login(baseURL, "admin", "admin");

		// validation points
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Create a branche and a staff");
		String staffName = "StaffD";

		// Creating two branches
		BranchLib branchLib = new BranchLib();
		String branchName = "BranchNameD";
		String branchCode = "BCODE" + getUniqueID();

		branchLib.createBranchFromWelcomePage(branchName, branchCode);
		waitForSeconds(5);
		branchLib.clickOnHomeTab();

		StaffLib staffLib = new StaffLib();
		staffLib.createStaffFromWelcomePage(staffName, branchName);

		// validation points
		logger.info("Verify whether above created staff is displayed with Delete button");
		Assert.assertTrue(
				driver.findElement(By.xpath(staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branchName)))
						.isDisplayed());

		// Step 3
		logger.info("Step 3: Query for above created staff");
		staffLib.queryForStaffInStaffList(staffName);

		// validation points
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branchName))));

		// Step 4
		logger.info("Step 4: Click on Delete button of the staff");
		String xpathOfDeleteButtonOfStaff = staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branchName);
		waitForSeconds(3);
		driver.findElement(By.xpath(xpathOfDeleteButtonOfStaff)).click();

		// validation points
		logger.info("Verify whether Delete confirmation message is displayed with Cancel and Delete buttons");
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(staffsPageLocators.delete_Staff_Confirmation_Message)));

		Assert.assertEquals(isElementPresent(By.xpath(staffsPageLocators.delete_Staff_Confirmation_Message)), true);
		Assert.assertTrue(isElementPresent(By.xpath(staffsPageLocators.cancel_Button_In_Delete_Staff_Confirmation)));
		Assert.assertTrue(isElementPresent(By.xpath(staffsPageLocators.delete_Button_In_Delete_Staff_Confirmation)));

		// Step 5
		logger.info("Step 5: Click on cancel button");
		driver.findElement(By.xpath(staffsPageLocators.cancel_Button_In_Delete_Staff_Confirmation)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));
		waitForSeconds(3);

		// validation points
		Assert.assertTrue(isElementPresent(By.xpath(xpathOfDeleteButtonOfStaff)));

		// Step 6
		logger.info("Step 6: Click on Delete button of staff and click on delete button in delete confirmation");
		waitForSeconds(30);
		driver.findElement(By.xpath(xpathOfDeleteButtonOfStaff)).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(staffsPageLocators.delete_Staff_Confirmation_Message)));
		driver.findElement(By.xpath(staffsPageLocators.delete_Button_In_Delete_Staff_Confirmation)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// validation points
		waitForSeconds(3);
		Assert.assertTrue(isElementNotPresent(By.xpath(xpathOfDeleteButtonOfStaff)));

		// Step 7
		logger.info("Step 7: Query for the deleted staff and check for the branch");
		staffLib.queryForStaffInStaffList(staffName);

		// validation points
		Assert.assertTrue(isElementNotPresent(By.xpath(xpathOfDeleteButtonOfStaff)));

		// Step 8
		logger.info("Step 8: Logout");
		loginLogoutLib.logout();
	}
}
