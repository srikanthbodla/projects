package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
 * This test will edit staff details
 * @author Srikanthbodla
 *
 */
public class EditStaffDetails extends BaseTest {

	static final Logger logger = Logger.getLogger(EditStaffDetails.class);
	
	@Test
	public void editStaffDetails() {

		// Step 1
		logger.info("Step 1: Login app");
		LoginLogoutLib loginLogoutLib = new LoginLogoutLib();
		loginLogoutLib.login(baseURL, "admin", "admin");

		// validation point
		waitForSeconds(3);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.admin_Logged_In_Message)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Create a Staff and two branches");
		String staffName = "StaffE";

		// Creating two branches
		BranchLib branchLib = new BranchLib();
		String branchName1 = "BranchNameX";
		String branchCode1 = "BCODE" + getUniqueID();

		branchLib.createBranchFromWelcomePage(branchName1, branchCode1);
		waitForSeconds(5);
		branchLib.clickOnHomeTab();

		String branchName2 = "BranchNameY";
		String branchCode2 = "BCODE" + getUniqueID();

		branchLib.createBranchFromWelcomePage(branchName2, branchCode2);
		waitForSeconds(5);
		branchLib.clickOnHomeTab();

		StaffLib staffLib = new StaffLib();
		staffLib.createStaffFromWelcomePage(staffName, branchName1);

		// validation points
		logger.info("Verify whether above created staff is displayed with Edit button");
		String editButtonXpath = staffsPageLocators.getXpathOfEditButtonOfStaff(staffName, branchName1);

		Assert.assertEquals(driver.findElement(By.xpath(editButtonXpath)).isDisplayed(), true);

		// Step 3
		logger.info("Step 3: Click on Edit button of Staff");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(editButtonXpath)));
		waitForSeconds(3);
		driver.findElement(By.xpath(editButtonXpath)).click();

		// validation points
		logger.info("Verify create or edit staff pop up is displayed with existing values in fields");
		Assert.assertTrue(isElementPresent(By.xpath(staffsPageLocators.createOrEditANewStaff_Popup)));
		Assert.assertEquals(
				driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup))
						.getAttribute("value").trim(),
				staffName);
		Assert.assertEquals(new Select(
				driver.findElement(By.xpath(staffsPageLocators.branch_dropdown_In_CreateOrEditANewStaff_Popup)))
						.getFirstSelectedOption().getText().trim(),
				branchName1);

		// Step 4
		logger.info("Step 4: update the values and click on cancel button");
		String staffName_Edit = "StaffEdit";
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup)).clear();
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup))
				.sendKeys(staffName_Edit);

		new Select(driver.findElement(By.xpath(staffsPageLocators.branch_dropdown_In_CreateOrEditANewStaff_Popup)))
				.selectByVisibleText(branchName2);

		driver.findElement(By.xpath(staffsPageLocators.cancel_Button_In_CreateOrEditANewStaff_Popup)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// validation points
		Assert.assertEquals(
				driver.findElement(By.xpath(staffsPageLocators.getXpathOfEditButtonOfStaff(staffName, branchName1)))
						.isDisplayed(),
				true);

		// Step 5
		logger.info("Step 5: update the values and click on Save button");
		waitForSeconds(3);
		driver.findElement(By.xpath(editButtonXpath)).click();
		waitForSeconds(3);
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup)).clear();
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup))
				.sendKeys(staffName_Edit);

		new Select(driver.findElement(By.xpath(staffsPageLocators.branch_dropdown_In_CreateOrEditANewStaff_Popup)))
				.selectByVisibleText(branchName2);

		driver.findElement(By.xpath(staffsPageLocators.save_Button_In_CreateOrEditANewStaff_Popup)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// validation points
		logger.info("Verify whether values are saved appropriately");
		Assert.assertEquals(driver
				.findElement(By.xpath(staffsPageLocators.getXpathOfEditButtonOfStaff(staffName_Edit, branchName2)))
				.isDisplayed(), true);

		// Step 6
		logger.info("Step 6: Click on View button of Staff");
		staffLib.clickOnViewButtonOfStaff(staffName_Edit, branchName2);

		// validation points
		logger.info("Verify whether values are saved appropriately");
		Assert.assertEquals(
				driver.findElement(By.xpath(staffPageLocators.nameValue_input_Field)).getAttribute("value").trim(),
				staffName_Edit);
		Assert.assertEquals(
				driver.findElement(By.xpath(staffPageLocators.branchValue_input_Field)).getAttribute("value").trim(),
				branchName2);

		// Step 7
		logger.info("Step 7: Click on back button");
		driver.findElement(By.xpath(staffPageLocators.back_Button)).click();

		// validation points
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

		// Step 8
		logger.info("Step 8: Logout");
		loginLogoutLib.logout();

	}
}
