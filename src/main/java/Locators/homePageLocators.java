package Locators;

public class homePageLocators {

	// Locators for Home page

	public static String Gurukula_Img = "//img[@alt='Gurukula']";
	public static String Welcome_Text = "//h1[text()='Welcome to Gurukula!']";
	public static String Home_Tab = "//a[@ui-sref='home']/span[contains(@class,'home')]";
	public static String Account_Tab = "//span[contains(@translate,'.account.main') and text()='Account']/following-sibling::b";
	public static String Authenticate_Option_In_Account_Tab = "//span[contains(@translate,'account.login') and text()='Authenticate']/parent::a";
	public static String Logout_link_In_Account_options = "//span[contains(@translate,'menu.account.logout') and text()='Log out']/parent::a";
	public static String Login_Link = "//a[@href='#/login' and text()='login']";
	public static String Register_Link = "//a[@href='#/register' and text()='Register a new account']";
	public static String Click_Here_Msg_With_Login_Link = "//div[@class='alert alert-warning ng-scope' and contains(text(),'Click here to')]/a[@href='#/login' and text()='login']";
	public static String Registration_Req_Msg_With_Registration_Link = "//div[@class='alert alert-warning ng-scope' and contains(text(),'You don') and contains(text(),'t have an account yet?')]/a[@href='#/register' and text()='Register a new account']";
}
