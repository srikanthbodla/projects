package core.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumWDFactory {

	private static WebDriver driver;

	/**
	 * This method starts webdriver with given browser type in configuration
	 * file
	 * 
	 * @return
	 */
	public static WebDriver startDriver() {

		switch (getBrowserName()) {
		case "internetexplorer":
			driver = new InternetExplorerDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		default:
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		return driver;
	}

	/**
	 * This method starts the driver if not started and returns driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return (driver == null) ? startDriver() : driver;
	}

	/**
	 * This method kills the driver
	 */
	public static WebDriver killDriver() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
			driver = null;
		}
		return driver;
	}

	/**
	 * This method restarts the driver
	 * 
	 * @return
	 */
	public static WebDriver restartDriver() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
		driver = null;
		startDriver();
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * This method gets browser name on which tests to be executed
	 * 
	 * @return
	 */
	private static String getBrowserName() {
		return getConfigurationPropertyValue("browser");
	}

	/**
	 * This method gets application url on which tests to be executed
	 * 
	 * @return
	 */
	String getApplicationURL() {
		return getConfigurationPropertyValue("applicationurl");
	}

	/**
	 * This method is used to get value of given property from configuration
	 * file
	 * 
	 * @param propertyName
	 * @return
	 */
	private static String getConfigurationPropertyValue(String propertyName) {
		String configFilePath = "config/configuration.properties";
		FileInputStream inputstream = null;
		Properties prop = new Properties();

		try {
			inputstream = new FileInputStream(new File(configFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(inputstream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (String) prop.getProperty(propertyName);
	}

}
