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
import Locators.staffPageLocators;
import Locators.staffsPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;

/**
 * 
 * This test will view staff details
 * 
 * @author Srikanthbodls
 *
 */
public class ViewStaffDetails extends BaseTest {

	static final Logger logger = Logger.getLogger(ViewStaffDetails.class);

	@Test
	public void viewStaffDetails() {

		// Step 1
		logger.info("Step 1: Login app");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.login(baseURL, "admin", "admin");

		// validation point
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Create a branche and a staff");
		String staffName = "StaffE";

		// Creating two branches
		BranchLib branchLib = new BranchLib();
		String branchName = "BranchNameX";
		String branchCode = "BCODE" + getUniqueID();

		branchLib.createBranchFromWelcomePage(branchName, branchCode);
		waitForSeconds(5);
		branchLib.clickOnHomeTab();

		StaffLib staffLib = new StaffLib();
		staffLib.createStaffFromWelcomePage(staffName, branchName);

		// validation points
		logger.info("Verify whether above created staff is displayed with View button");
		Assert.assertTrue(
				driver.findElement(By.xpath(staffsPageLocators.getXpathOfViewButtonOfStaff(staffName, branchName)))
						.isDisplayed());

		// Step 3
		logger.info("Step 3: Click on View button of Staff");
		waitForSeconds(3);
		staffLib.clickOnViewButtonOfStaff(staffName, branchName);

		// validation points
		logger.info("Verify whether values are saved appropriately");
		Assert.assertEquals(
				driver.findElement(By.xpath(staffPageLocators.nameValue_input_Field)).getAttribute("value").trim(),
				staffName);
		Assert.assertEquals(
				driver.findElement(By.xpath(staffPageLocators.branchValue_input_Field)).getAttribute("value").trim(),
				branchName);

		// Step 4
		logger.info("Step 4: Click on back button");
		driver.findElement(By.xpath(staffPageLocators.back_Button)).click();

		// validation points
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// Step 5
		logger.info("Step 5 : Logout");
		loginLogoutLib.logout();
	}
}
