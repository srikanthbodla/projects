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
import Locators.branchPageLocators;
import Locators.branchesPageLocators;
import Locators.welcomePageLocators;
import core.testcase.BaseTest;
import core.testcase.SeleniumWDFactory;

/**
 * This test will edit the branch details
 * @author Srikanthbodla
 *
 */
public class EditBranchDetails extends BaseTest {

	static final Logger logger = Logger.getLogger(EditBranchDetails.class);
	
	@Test
	public void editBranchDetails() {
		
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

		String branchName = "BranchNameE";
		String branchCode = "BCODE" + getUniqueID();
		logger.info(branchName + branchCode);

		BranchLib branchLib = new BranchLib();
		branchLib.createBranchFromWelcomePage(branchName, branchCode);

		// validation points
		logger.info("Verify Edit button is displayed for Branch created above");
		String xpathOfEditButtonOfBranch = branchesPageLocators.getXpathOfEditButtonOfBranch(branchName, branchCode);
		Assert.assertTrue(driver.findElement(By.xpath(xpathOfEditButtonOfBranch)).isDisplayed());

		// Step 3
		logger.info("Step 3: Click on Edit button of branch");
		waitForSeconds(3);
		driver.findElement(By.xpath(xpathOfEditButtonOfBranch)).click();

		// validation points
		logger.info("Verify Edit branch pop up is displayed with branchName and branchCode");
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup)));
		waitForSeconds(3);
		Assert.assertEquals(
				driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup))
						.getAttribute("value").trim(),
				branchName);

		Assert.assertEquals(
				driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup))
						.getAttribute("value").trim(),
				branchCode);

		// Step 4
		logger.info("Step 4: Edit values in name and code field and click on Cancel button");
		String branchName_Edit = "BranchEdit";
		String branchCode_Edit = "BCODE" + getUniqueID();
		logger.info(branchName_Edit + branchCode_Edit);

		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup)).clear();
		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchName_Edit);

		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup)).clear();
		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchCode_Edit);

		driver.findElement(By.xpath(branchesPageLocators.cancel_Button_In_CreateOrEditABranch_Popup)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// validation points
		logger.info("Verify that branch is not Edited");
		Assert.assertTrue(driver.findElement(By.xpath(xpathOfEditButtonOfBranch)).isDisplayed());

		// Step 5
		logger.info("Step 5: Edit values in name and code fields and click on Save button");
		waitForSeconds(3);
		driver.findElement(By.xpath(xpathOfEditButtonOfBranch)).click();
		waitForSeconds(3);
		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup)).clear();
		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchName_Edit);

		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup)).clear();
		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchCode_Edit);

		driver.findElement(By.xpath(branchesPageLocators.save_Button_In_CreateOrEditABranch_Popup)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// validation points
		logger.info("Verify that branch is saved with new details");

		Assert.assertTrue(driver
				.findElement(
						By.xpath(branchesPageLocators.getXpathOfViewButtonOfBranch(branchName_Edit, branchCode_Edit)))
				.isDisplayed());
		

		// Step 6
		logger.info("Step 6: Click on view button of the branch edited above");
		String branchViewButtonXpath = branchesPageLocators.getXpathOfViewButtonOfBranch(branchName_Edit, branchCode_Edit);
		waitForSeconds(3);
		driver.findElement(By.xpath(branchViewButtonXpath)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branch"));

		// validation points
		logger.info("Verify Branch Details, Header, name, code");
		Assert.assertEquals(driver.findElement(By.xpath(branchPageLocators.Branch_Header)).getText().trim(), "Branch");

		Assert.assertEquals(
				driver.findElement(By.xpath(branchPageLocators.Value_Of_Name_Field)).getAttribute("value").trim(),
				branchName_Edit);

		Assert.assertEquals(
				driver.findElement(By.xpath(branchPageLocators.Value_Of_Code_Field)).getAttribute("value").trim(),
				branchCode_Edit);

		// Step 7
		logger.info("Step 7: Click on Back button below details");
		driver.findElement(By.xpath(branchPageLocators.Back_Button)).click();

		// validation points
		logger.info("Verify whether Branches page is displayed");
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// Step 8
		logger.info("Step 8: Logout");
		loginLogoutLib.logout();

	}
}
