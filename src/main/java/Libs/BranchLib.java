package Libs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.branchesPageLocators;
import Locators.homePageLocators;
import Locators.welcomePageLocators;
import core.testcase.SeleniumWDFactory;

public class BranchLib {

	/**
	 * This method is used to create branch with given branchName and branchCode
	 * where the navigation starts from welcome page
	 * 
	 * @param branchName
	 * @param branchCode
	 */
	public void createBranchFromWelcomePage(String branchName, String branchCode) {

		WebDriver driver = SeleniumWDFactory.getDriver();

		driver.findElement(By.xpath(welcomePageLocators.entities_DropDown)).click();
		driver.findElement(By.xpath(welcomePageLocators.branch_Option_In_Entities_DropDown)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));
		driver.findElement(By.xpath(branchesPageLocators.createANewBranch_Button)).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath(branchesPageLocators.name_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchName);

		driver.findElement(By.xpath(branchesPageLocators.code_InputField_In_CreateOrEditABranch_Popup))
				.sendKeys(branchCode);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath(branchesPageLocators.save_Button_In_CreateOrEditABranch_Popup)));
		driver.findElement(By.xpath(branchesPageLocators.save_Button_In_CreateOrEditABranch_Popup)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));
	}

	/**
	 * This method queries or searches for a branch and deletes it
	 * 
	 * @param branchName
	 */
	public void queryForBranchInBranchesList(String branchName) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.findElement(By.xpath(branchesPageLocators.query_InputField)).clear();
		driver.findElement(By.xpath(branchesPageLocators.query_InputField)).sendKeys(branchName);
		driver.findElement(By.xpath(branchesPageLocators.searchABranch_Button)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Branches"));
	}

	public void clickOnHomeTab() {
		WebDriver driver = SeleniumWDFactory.getDriver();
		
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(homePageLocators.Home_Tab)));
		
		WebElement homeTab = driver.findElement(By.xpath(homePageLocators.Home_Tab));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", homeTab);

		driver.findElement(By.xpath(homePageLocators.Home_Tab)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));

	}
	

}
