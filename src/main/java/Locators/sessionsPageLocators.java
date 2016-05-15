package Locators;

public class sessionsPageLocators {

	public static String getSessionRecord() {
		return "//tr[@ng-repeat='session in sessions']/td[text()='0:0:0:0:0:0:0:1']/following-sibling::td[contains(.,'Mozilla') or contains(.,'Chrome')]/following-sibling::td/button[@type='submit' and text()='Invalidate']";
	}

}
