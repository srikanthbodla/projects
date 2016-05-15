package Locators;

public class authenticationPageLocators {

	public static String login_Input_Field = "//input[@id='username']";
	public static String password_Input_Field = "//input[@id='password']";
	public static String rememberMe_Checkbox = "//span[contains(@translate,'rememberme') and text()='Automatic Login']";
	public static String authenticate_Button = "//button[@type='submit' and text()='Authenticate']";
	public static String forgotPassoword_Link = "//a[contains(@translate,'password.forgot') and text()='Did you forget your password?']";
	public static String registerNewAccount_Link = "//a[@href='#/register' and text()='Register a new account']";
}
