package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {

	DesiredCapabilities dc = new DesiredCapabilities();

	private static RemoteWebDriver driver = null;

	public boolean takeScreenshot(final String name) {
		String screenshotDirectory = System.getProperty("appium.screenshots.dir",
				System.getProperty("java.io.tmpdir", ""));
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
	}

	@BeforeTest
	public void setup() throws MalformedURLException {
		// DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		// dc.setCapability( "deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("appPackage", "com.example.appiumfun");
		dc.setCapability("appActivity", ".MainActivity");
		dc.setCapability("noReset", true);

	}

	@Test
	public void sampleTest() {

		AndroidDriver<AndroidElement> ad;
		try {
			ad = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
			MobileElement el1 = (MobileElement) ad.findElementById("com.example.appiumfun:id/button_first");
			el1.click();

			// wait to look at the screen
			ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			MobileElement el2 = (MobileElement) ad.findElementById("com.example.appiumfun:id/editText");
			el2.clear();
			MobileElement el3 = (MobileElement) ad.findElementById("com.example.appiumfun:id/editText");
			el3.sendKeys("Hello from Automation :)");

			Assert.assertEquals(ad.findElementById("com.example.appiumfun:id/editText").getText(),
					"Hello from Automation :)");

			System.out.println("Im a running test");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterTest
	public void teardown() {

	}

}
