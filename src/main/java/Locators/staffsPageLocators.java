package Locators;

public class staffsPageLocators {

	public static String createANewStaff_Button = "//span[contains(@translate,'staff.home') and text()='Create a new Staff']/parent::button";
	public static String query_InputField = "//input[@id='searchQuery']";
	public static String searchAStaff_Button = "//span[text()='Search a Staff']/parent::button[@ng-click='search()']";
	public static String createOrEditANewStaff_Popup = "//h4[@id='myStaffLabel' and text()='Create or edit a Staff']/ancestor::form[@name='editForm']";
	public static String name_InputField_In_CreateOrEditANewStaff_Popup = "//input[@name='name']";
	public static String branch_dropdown_In_CreateOrEditANewStaff_Popup = "//select[@name='related_branch']";
	public static String cancel_Button_In_CreateOrEditANewStaff_Popup = "//span[contains(@translate,'cancel') and text()='Cancel']/parent::button";
	public static String save_Button_In_CreateOrEditANewStaff_Popup = "//span[contains(@translate,'save') and text()='Save']/parent::button[@type='submit']";
	public static String delete_Staff_Confirmation_Message = "//h4[contains(@translate,'delete.title') and text()='Confirm delete operation']/parent::div/following-sibling::div/p[contains(text(),'Are you sure you want to delete Staff')]";
	public static String cancel_Button_In_Delete_Staff_Confirmation = "//form[@name='deleteForm']//span[contains(@translate,'action.cancel') and text()='Cancel']/parent::button";
	public static String delete_Button_In_Delete_Staff_Confirmation = "//form[@name='deleteForm']//span[contains(@translate,'action.delete') and text()='Delete']/parent::button";
	
	/**
	 * This method returns xpath of Delete button on Staff record
	 * 
	 * @param staffName
	 * @param staffCode
	 * @return
	 */
	public static String getXpathOfDeleteButtonOfStaff(String staffName, String branchName) {
		return "//tr[@ng-repeat='staff in staffs']//td[text()='" + staffName + "']/following-sibling::td[text()='"
				+ branchName + "']/following-sibling::td/button/span[text()='Delete']/parent::button";
	}

	/**
	 * This method returns xpath of View button of Staff record
	 * 
	 * @param staffName
	 * @param staffCode
	 * @return
	 */
	public static String getXpathOfViewButtonOfStaff(String staffName, String branchName) {
		return "//tr[@ng-repeat='staff in staffs']//td[text()='" + staffName + "']/following-sibling::td[text()='"
				+ branchName + "']/following-sibling::td/button/span[text()='View']/parent::button";
	}

	/**
	 * This method returns xpath of Edit button of Staff Record
	 * 
	 * @param staffName
	 * @param staffCode
	 * @return
	 */
	public static String getXpathOfEditButtonOfStaff(String staffName, String branchName) {
		return "//tr[@ng-repeat='staff in staffs']//td[text()='" + staffName + "']/following-sibling::td[text()='"
				+ branchName + "']/following-sibling::td/button/span[text()='Edit']/parent::button";
	}

}
