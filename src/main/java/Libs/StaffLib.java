package Libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.branchesPageLocators;
import Locators.staffsPageLocators;
import Locators.welcomePageLocators;
import core.testcase.SeleniumWDFactory;

public class StaffLib {

	/**
	 * This method creates a staff from welcome page
	 * 
	 * @param staffName
	 * @param branchName
	 */
	public void createStaffFromWelcomePage(String staffName, String branchName) {

		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).click();
		driver.findElement(By.xpath(welcomePageLocators.staff_Option_In_Entities_DropDown)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));
		driver.findElement(By.xpath(staffsPageLocators.createANewStaff_Button)).click();
		new WebDriverWait(driver, 60).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(staffsPageLocators.createOrEditANewStaff_Popup)));
		driver.findElement(By.xpath(staffsPageLocators.name_InputField_In_CreateOrEditANewStaff_Popup))
				.sendKeys(staffName);
		new Select(driver.findElement(By.xpath(staffsPageLocators.branch_dropdown_In_CreateOrEditANewStaff_Popup)))
				.selectByVisibleText(branchName);
		driver.findElement(By.xpath(staffsPageLocators.save_Button_In_CreateOrEditANewStaff_Popup)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));

	}

	public void clickOnViewButtonOfStaff(String staffName, String branch) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath(staffsPageLocators.getXpathOfViewButtonOfStaff(staffName, branch))));
		driver.findElement(By.xpath(staffsPageLocators.getXpathOfViewButtonOfStaff(staffName, branch))).click();
		;
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staff"));
	}

	public void clickOnDeleteButtonOfStaff(String staffName, String branch) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath(staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branch))));
		driver.findElement(By.xpath(staffsPageLocators.getXpathOfDeleteButtonOfStaff(staffName, branch))).click();
		;
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staff"));
	}

	public void queryForStaffInStaffList(String staffName) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.findElement(By.xpath(staffsPageLocators.query_InputField)).clear();
		driver.findElement(By.xpath(staffsPageLocators.query_InputField)).sendKeys(staffName);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(staffsPageLocators.searchAStaff_Button)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Staffs"));
	}

}
