package Locators;

public class branchPageLocators {

	public static String Branch_Header = "//h2/span[contains(@translate,'branch.detail.title')]";
	public static String Value_Of_Name_Field = "//td/span[contains(@translate,'branch.name') and text()='Name']/../following-sibling::td/input[@type='text']";
	public static String Value_Of_Code_Field = "//td/span[contains(@translate,'branch.code') and text()='Code']/../following-sibling::td/input[@type='text']";
	public static String Back_Button = "//span[contains(@translate,'action.back') and text()='Back']/parent::button";
}
