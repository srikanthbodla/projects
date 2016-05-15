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
 * This test will view branch details
 * 
 * @author Srikanthbodls
 *
 */
public class ViewBranchDetails extends BaseTest {
	
	static final Logger logger = Logger.getLogger(ViewBranchDetails.class);

	@Test
	public void viewBranchDetails() {

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
		logger.info("Step 2: Create a branch");

		String branchName = "BranchNameV";
		String branchCode = "BCODE" + getUniqueID();

		BranchLib branchLib = new BranchLib();
		branchLib.createBranchFromWelcomePage(branchName, branchCode);

		// validation points
		logger.info("Verify branch record is shown with View button");
		String branchViewButtonXpath = branchesPageLocators.getXpathOfViewButtonOfBranch(branchName, branchCode);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(branchViewButtonXpath)));
		Assert.assertEquals(driver.findElement(By.xpath(branchViewButtonXpath)).isDisplayed(), true);

		// Step 3
		logger.info("Step 3: Click on View button of Branch created above");
		waitForSeconds(3);
		driver.findElement(By.xpath(branchViewButtonXpath)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branch"));

		// validation points
		logger.info("Verify Branch Details, Header, name, code");
		Assert.assertEquals(driver.findElement(By.xpath(branchPageLocators.Branch_Header)).getText().trim(), "Branch");

		Assert.assertEquals(
				driver.findElement(By.xpath(branchPageLocators.Value_Of_Name_Field)).getAttribute("value").trim(),
				branchName);

		Assert.assertEquals(
				driver.findElement(By.xpath(branchPageLocators.Value_Of_Code_Field)).getAttribute("value").trim(),
				branchCode);

		// Step 4
		logger.info("Step 4: Click on Back button below details");
		driver.findElement(By.xpath(branchPageLocators.Back_Button)).click();

		// validation points
		logger.info("Verify whether Branches page is displayed");
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));

		// Step 5
		logger.info("Step 5: Logout");
		loginLogoutLib.logout();

	}
}
