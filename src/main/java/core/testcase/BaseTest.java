package core.testcase;

import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	public static String baseURL;
	protected WebDriver driver;

	public BaseTest() {
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * This method gets 5 digit uniqueID
	 * 
	 * @return
	 */
	public static String getUniqueID() {
		String uid = Calendar.getInstance().getTimeInMillis() + "";
		return uid.substring(uid.length() - 1 - 5, uid.length() - 1);
	}

	@BeforeTest
	/**
	 * This method will configure webdriver with baseURL and maximize the
	 * browser
	 */
	public void configureWebDriver() {
		baseURL = new SeleniumWDFactory().getApplicationURL();
		System.out.println(baseURL);
		driver = SeleniumWDFactory.getDriver();
		driver.manage().window().maximize();
	}

	/**
	 * This method will close the driver object
	 */
	@AfterTest
	public final void tearDown() {
		driver = SeleniumWDFactory.getDriver();
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
		driver = null;
	}

	/**
	 * This method will check the presence of Element
	 * 
	 * @param by
	 * @return
	 */
	public static boolean isElementPresent(By by) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		long sTime = Calendar.getInstance().getTimeInMillis();

		while (((Calendar.getInstance().getTimeInMillis() - sTime) / (double) 1000) < 60) {
			try {
				driver.findElement(by);
				flag = true;
				break;
			} catch (NoSuchElementException nosuchelement) {

			}
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return flag;
	}

	/**
	 * This method checks for element not present
	 * 
	 * @param by
	 * @return
	 */
	public static boolean isElementNotPresent(By by) {
		WebDriver driver = SeleniumWDFactory.getDriver();
		boolean flag = false;
		if (driver.findElements(by).size() == 0)
			flag = true;

		return flag;
	}

	public static void waitForSeconds(int secs) {
		try {
			Thread.sleep(1000 * secs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
