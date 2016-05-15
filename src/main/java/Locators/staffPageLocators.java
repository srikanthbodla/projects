package Locators;

public class staffPageLocators {

	public static String nameValue_input_Field = "//span[contains(@translate,'staff.name') and text()='Name']/parent::td/following-sibling::td/input";
	public static String branchValue_input_Field = "//span[contains(@translate,'staff.related_branch') and text()='Branch']/parent::td/following-sibling::td/input";
	public static String back_Button = "//span[contains(@translate,'action.back') and text()='Back']/parent::button";
}
