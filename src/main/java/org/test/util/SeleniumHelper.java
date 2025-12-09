package org.test.util;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {

//	https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/WebDriver.html
//	https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/WebElement.html

	private WebDriver driver;

	public SeleniumHelper(WebDriver driver) {
		this.driver = driver;
	}

//======== Basic WebDriver Methods ========
	public void enterURL(String url) { // Navigate to a specified URL
		driver.get(url);
	}

	public String getCurrentUrl() { // Get the current URL of the page
		return driver.getCurrentUrl();
	}

	public String getPageTitle() { // Get the title of the current page
		return driver.getTitle();
	}

	public WebElement findElement(By locator) { // Find a single web element using a locator
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) { // Find multiple web elements using a locator
		return driver.findElements(locator);
	}

	public int elementsCount(By locator) { // Get the count of elements matching a locator
		return findElements(locator).size();
	}

	public void closeCurrentWindow() { // Close the current window
		driver.close();
	}

	public void quitDriver() { // Quit the driver and close all associated windows
		driver.quit();
	}

//	=====Tab and Window Management Methods=====

	public void newTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public void newWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}

	public Set<String> getAllWindowHandles() { // Get all window handles
		return driver.getWindowHandles();
	}

	public String getCurrentWindowHandle() { // Get the current window handle
		return driver.getWindowHandle();
	}

	public int getTabsCount() { // Get the count of all window handles
		return getAllWindowHandles().size();
	}

	public void switchToTabByIndex(int index) {
		var handles = getAllWindowHandles().toArray(new String[0]);
		if (index >= 0 && index < handles.length) {
			driver.switchTo().window(handles[index]);
		} else {
			throw new IllegalArgumentException("Invalid window index: " + index);
		}
	}

//	===== Frame Management Methods =====

	public void switchToFrameByElement(WebElement frameElement) { // Switch to a frame using a WebElement
		driver.switchTo().frame(frameElement);
	}

	public void switchToFrameByIndex(int index) { // Switch to a frame using its index
		driver.switchTo().frame(index);
	}

	public void switchToFrameByNameOrId(String nameOrId) { // Switch to a frame using its name or ID
		driver.switchTo().frame(nameOrId);
	}

	public void switchToParentFrame() { // Switch to the parent frame
		driver.switchTo().parentFrame();
	}

	public void switchToMainWindow() { // Switch to the main page from any frame
		driver.switchTo().defaultContent();
	}

	// ===== Window Management Methods =====
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void minimizeWindow() {
		driver.manage().window().minimize();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

//	===== Wait Management Methods =====

	public WebDriverWait wait(int timeoutInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	}

	public void pageLoadTimeout(Duration duration) {
		driver.manage().timeouts().pageLoadTimeout(duration);
	}

	public void implicitWait(Duration duration) {
		driver.manage().timeouts().implicitlyWait(duration);
	}

	// ===== Additional Utility Methods =====

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
	}

	public void clickElement(WebElement element) {
		wait(20).until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void enterValue(WebElement element, String text) {
		wait(20).until(ExpectedConditions.elementToBeClickable(element)).clear();
		element.sendKeys(text);
	}

	public String getTextFromElement(WebElement element) {
		return wait(20).until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public void switchToFrame(WebElement frameElement) {
		wait(20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		driver.switchTo().frame(frameElement);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

//	=== Alert Handling Methods ===
	public void acceptAlert() {
		wait(20).until(ExpectedConditions.alertIsPresent()).accept();
	}

	public void dismissAlert() {
		wait(20).until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public String getAlertText() {
		return wait(20).until(ExpectedConditions.alertIsPresent()).getText();
	}

	public void sendKeysToAlert(String text) {
		wait(20).until(ExpectedConditions.alertIsPresent()).sendKeys(text);
	}

	public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		wait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		wait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
	}

//	 create all necessary methods for selenium operations
//	new tab method

	public void switchToWindowByHandle(String handle) {
		driver.switchTo().window(handle);
	}

	// Add more utility methods as needed for your Selenium operations.

	public void clearText(WebElement element) {
		wait(20).until(ExpectedConditions.elementToBeClickable(element)).clear();
	}

	// Additional utility methods can be added here as needed.

	// This class is intended to provide utility methods for Selenium operations.
	// Currently, it is empty, but you can add methods as needed for your Selenium
	// tests.

	// Example method to initialize a WebDriver
	// public static WebDriver initializeWebDriver(String browserType) {
	// // Implementation for initializing WebDriver based on browser type
	// }

	// Example method to take a screenshot
	// public static void takeScreenshot(WebDriver driver, String filePath) {
	// // Implementation for taking a screenshot
	// }
	// Example method to wait for an element to be visible
	// public static void waitForElementToBeVisible(WebDriver driver, By locator,
	// int timeout) {
	// // Implementation for waiting for an element to be visible
	// }
	// Example method to click an element
	// public static void clickElement(WebDriver driver, By locator) {
	// // Implementation for clicking an element
	// }
	// Example method to enter text into an input field
	// public static void enterText(WebDriver driver, By locator, String text) {

}
