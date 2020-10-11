package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	public WebDriver driver;
	private JavascriptExecutor javascriptExecutor;

	public Base(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void visit(String url) {
		driver.get(url);
	}

	public void click(WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void type(WebElement element, int timeout, String inputText) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		element.sendKeys(inputText);
	}

	public void hover(WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		Actions builder = new Actions(driver);
		Actions hover = builder.moveToElement(element);
		hover.click();
		hover.build();
		hover.perform();
	}

	public void scrollIntoView(WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void waitForElementToBeVisible(WebElement element, int timeout, String text) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	public boolean elementVisible(WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	public void pressEnter(WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		Actions press = new Actions(driver);
		press.sendKeys(Keys.ENTER).build().perform();
	}

}
