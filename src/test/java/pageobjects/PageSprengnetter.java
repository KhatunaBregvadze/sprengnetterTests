package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PageSprengnetter extends Base {

	@FindBy(xpath = "//span[contains(text(),'Karriere')]")
	private WebElement karriereElement;

	@FindBy(xpath = "//a[contains(text(),'Stellenbörse')]")
	private WebElement stellenboerseElement;

	@FindBy(xpath = "//a[contains(text(),'Suche')]")
	private WebElement sucheElement;

	@FindBy(name = "q")
	private WebElement inputSucheElement;

	@FindBy(xpath = "string(//p[contains(text(), 'Die Suche lieferte keine Ergebnisse.')])")
	private WebElement responseElement;

	public PageSprengnetter(WebDriver driver) {
		super(driver);
		visit("https://www.sprengnetter.de/");
	}

	public void chooseSuche(String suche) {
		click(sucheElement, 4);
		type(inputSucheElement, 4, suche);
		pressEnter(inputSucheElement, 4);
	}

	public void clickKarriere() {
		karriereElement.isDisplayed();
		click(karriereElement, 4);
	}

	public void clickStellenboerse() {
		click(stellenboerseElement, 10);
	}

	public void assertDelphientwickler() {
		Assert.assertFalse("Die Suche lieferte keine Ergebnisse.".equals(responseElement));
	}

}
