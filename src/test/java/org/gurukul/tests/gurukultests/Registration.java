package org.gurukul.tests.gurukultests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Locators.homePageLocators;
import Locators.registrationPageLocators;
import core.testcase.BaseTest;
import core.testcase.SeleniumWDFactory;

/**
 * 
 * This test case will perform registration of user
 * 
 * @author Srikanthbodla
 *
 */
public class Registration extends BaseTest {

	static final Logger logger = Logger.getLogger(QueryAndDeleteABranch.class);

	@Test
	public void registration() {

		// Step 1
		logger.info("Step1: Access Gurukul application");
		WebDriver driver = SeleniumWDFactory.getDriver();
		driver.get(baseURL);
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("gurukula"));

		// validation point 1
		logger.info(
				"Verify whether Home Page is displayed with Gurukula Logo, Welcome Text,Home and Account tab, Register a new account link");
		Assert.assertEquals(driver.getTitle(), "gurukula");
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Gurukula_Img)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Welcome_Text)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Home_Tab)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Account_Tab)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Click_Here_Msg_With_Login_Link)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(homePageLocators.Registration_Req_Msg_With_Registration_Link))
				.isDisplayed(), true);

		// Step 2
		logger.info("Step 2: Click on 'Register a new account Link'");
		driver.findElement(By.xpath(homePageLocators.Register_Link)).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs("Registration"));

		// validation point 2

		logger.info(
				"Verify Registration page is displayed with Login,Email,New Password,New Password Confirmation fields are present, password strength field, Register button, login link");

		// verifying the fields
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.login_Input_Field)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.email_Input_Field)).isDisplayed(),
				true);
		Assert.assertEquals(
				driver.findElement(By.xpath(registrationPageLocators.newPassword_Input_field)).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.newPasswordConfirmation_Input_field))
				.isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.registration_header)).isDisplayed(),
				true);
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.register_Button)).isDisplayed(), true);

		// verifying Register button is in disabled state when fields are empty
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.register_Button)).isEnabled(), false);

		// Step 3
		logger.info("Step 3: Enter valid Login, Email and New Password and New Password confirmation");

		String uniqueId = getUniqueID();
		String login = "srikanth";
		String email = "S" + uniqueId + "@xyz.com";
		String password = "Password123";

		driver.findElement(By.xpath(registrationPageLocators.login_Input_Field)).sendKeys(login);
		driver.findElement(By.xpath(registrationPageLocators.email_Input_Field)).sendKeys(email);
		driver.findElement(By.xpath(registrationPageLocators.newPassword_Input_field)).sendKeys(password);
		driver.findElement(By.xpath(registrationPageLocators.newPasswordConfirmation_Input_field)).sendKeys(password);

		// validation point
		logger.info("Verify if Registration button is enabled");
		Assert.assertEquals(driver.findElement(By.xpath(registrationPageLocators.register_Button)).isEnabled(), true);

		// Step 4
		logger.info("Step 4: Click on Register");
		driver.findElement(By.xpath(registrationPageLocators.register_Button)).click();

	}
}
