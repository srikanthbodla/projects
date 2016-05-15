package Locators;

public class branchesPageLocators {

	public static String createANewBranch_Button = "//span[contains(@translate,'branch.home') and text()='Create a new Branch']/parent::button";
	public static String query_InputField = "//input[@id='searchQuery']";
	public static String searchABranch_Button = "//span[text()='Search a Branch']/parent::button[@ng-click='search()']";
	public static String createOrEditANewBranch_Popup = "//h4[@id='myBranchLabel' and text()='Create or edit a Branch']/ancestor::form[@name='editForm']";
	public static String name_InputField_In_CreateOrEditABranch_Popup = "//input[@name='name']";
	public static String code_InputField_In_CreateOrEditABranch_Popup = "//input[@name='code']";
	public static String cancel_Button_In_CreateOrEditABranch_Popup = "//span[contains(@translate,'cancel') and text()='Cancel']/parent::button";
	public static String save_Button_In_CreateOrEditABranch_Popup = "//span[contains(@translate,'save') and text()='Save']/parent::button[@type='submit']";
	public static String delete_Branch_Confirmation_Message = "//h4[contains(@translate,'delete.title') and text()='Confirm delete operation']/parent::div/following-sibling::div/p[contains(text(),'Are you sure you want to delete Branch')]";
	public static String cancel_Button_In_Delete_Branch_Confirmation = "//form[@name='deleteForm']//span[contains(@translate,'action.cancel') and text()='Cancel']/parent::button";
	public static String delete_Button_In_Delete_Branch_Confirmation = "//form[@name='deleteForm']//span[contains(@translate,'action.delete') and text()='Delete']/parent::button";
	/**
	 * This method returns xpath of Delete button on Branch record
	 * 
	 * @param branchName
	 * @param branchCode
	 * @return
	 */
	public static String getXpathOfDeleteButtonOfBranch(String branchName, String branchCode) {
		return "//tr[@ng-repeat='branch in branches']//td[text()='" + branchName + "']/following-sibling::td[text()='"
				+ branchCode + "']/following-sibling::td/button/span[text()='Delete']/parent::button";
	}

	/**
	 * This method returns xpath of View button of Branch record
	 * 
	 * @param branchName
	 * @param branchCode
	 * @return
	 */
	public static String getXpathOfViewButtonOfBranch(String branchName, String branchCode) {
		return "//tr[@ng-repeat='branch in branches']//td[text()='" + branchName + "']/following-sibling::td[text()='"
				+ branchCode + "']/following-sibling::td/button/span[text()='View']/parent::button";
	}

	/**
	 * This method returns xpath of Edit button of Branch Record
	 * 
	 * @param branchName
	 * @param branchCode
	 * @return
	 */
	public static String getXpathOfEditButtonOfBranch(String branchName, String branchCode) {
		return "//tr[@ng-repeat='branch in branches']//td[text()='" + branchName + "']/following-sibling::td[text()='"
				+ branchCode + "']/following-sibling::td/button/span[text()='Edit']/parent::button";
	}

}
