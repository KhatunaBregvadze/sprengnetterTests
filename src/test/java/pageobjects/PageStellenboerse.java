package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PageStellenboerse extends Base {

	@FindBy(xpath = "//h3[contains(text(),'Berufserfahrene und Berufseinsteigende für den Standort Bad Neuenahr')]")
	private WebElement imgBerufserfahrene_einsteigerElement;

	@FindBy(xpath = "//ul/li/a[text()='QA Software Engineer (m/w/d) ']")
	private WebElement qaSoftwareEngineerElement;

	@FindBy(xpath = "//h2[contains(text(),'Ihre Ansprechpartnerin')]")
	private WebElement kontaktElement;

	@FindBy(xpath = "//h2[text()='QA Software Engineer (m/w/d)']")
	private WebElement qaHeaderElement;

	public PageStellenboerse(WebDriver driver) {
		super(driver);
	}

	public void mouseHover() {
		Actions hover = new Actions(driver);
		hover.moveToElement(imgBerufserfahrene_einsteigerElement).perform();
		hover(imgBerufserfahrene_einsteigerElement, 4);
	}

	public void clickQASoftwareEngineer() {
		click(qaSoftwareEngineerElement, 4);
	}

	public void scrollPageStellenboerse() {
		scrollIntoView(kontaktElement, 4);
	}

	public void assertQAStellenanzeige() {
		Assert.assertTrue(qaHeaderElement.isDisplayed());
	}

}
