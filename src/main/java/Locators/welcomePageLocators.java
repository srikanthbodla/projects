package Locators;

public class welcomePageLocators {

	public static String welcome_Text = "//h1[text()='Welcome to Gurukula!']";
	public static String admin_Logged_In_Message = "//div[contains(@translate,'logged.message') and text()='You are logged in as user \"admin\".']";
	public static String entities_DropDown = "//a[@class='dropdown-toggle']/span/span[contains(@translate,'menu.entities') and text()='Entities']";
	public static String branch_Option_In_Entities_DropDown = "//li/a[@href='#/branch']";
	public static String staff_Option_In_Entities_DropDown = "//li/a[@href='#/staff']";
	public static String home_Link = "//span[contains(@translate,'menu.home') and text()='Home']/parent::a";
	public static String Account_Tab = "//span[contains(@translate,'.account.main') and text()='Account']/following-sibling::b";
	public static String settings_Option_In_Account_Tab ="//span[contains(@translate,'account.settings') and text()='Settings']/parent::a";
	public static String sessions_Option_In_Account_Tab = "//span[contains(@translate,'account.sessions') and text()='Sessions']/parent::a";
}
