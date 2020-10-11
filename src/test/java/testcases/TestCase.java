package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageobjects.KarriereStellenboerseKontakt;
import pageobjects.PageSprengnetter;
import pageobjects.PageStellenboerse;

public class TestCase {

	private PageSprengnetter pageSprengnetter;
	private PageStellenboerse pageStellenboerse;
	private KarriereStellenboerseKontakt karriereStellenboerseKontakt;

	protected WebDriver driver;

	@BeforeSuite
	public void setUp() throws IOException {

		Properties Config = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/selenium.properties");
		Config.load(file);

		if ("firefox".equals(Config.getProperty("browser"))) {
			System.setProperty("webdriver.geckodriver.driver", "src/test/recources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if ("ie".equals(Config.getProperty("browser"))) {
			// Die Pfad Variable muss auf dem Host entsprechend angepasst werden.
			// (webdriver.ie.driver=$PATH/IEDriverServer.exe)
			System.setProperty("webdriver.ie.driver", "src/test/recources/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();

		pageSprengnetter = new PageSprengnetter(driver);
		pageStellenboerse = new PageStellenboerse(driver);
		karriereStellenboerseKontakt = new KarriereStellenboerseKontakt(driver);
	}

	// Aufgabe 1
	@Test
	public void chooseQaSoftwareEngineer() {
		navigateToStellenboerse();
		pageStellenboerse.mouseHover();
		pageStellenboerse.clickQASoftwareEngineer();
		pageStellenboerse.assertQAStellenanzeige();
	}

	// Aufgabe 2
	// Nichtgültige Äquivalenzklassen sollten nicht zusammen getestet werden. Da der
	// Screenshot der Anforderungen aber darauf hindeutet, dass das so gewollt ist,
	// habe ich das dennoch als Testfall angelegt. Damit die Tests aber eine Aussage
	// besitzen, sind die NGÄs folgend jeweils einzeln als Test verfügbar.
	@Test
	public void sendContactFormWithoutUsernameEmailAndText() {
		navigateToStellenboerseBottom();
		karriereStellenboerseKontakt.typePhone("0152-29939891");
		karriereStellenboerseKontakt.typeCompany("TestUnternehmen GmbH");

		karriereStellenboerseKontakt.clickButtonAbsenden();
		karriereStellenboerseKontakt.getMessageError();
	}

	@Test
	public void sendContactFormWithoutUsername() {
		navigateToStellenboerseBottom();
		karriereStellenboerseKontakt.typeEmail("test.test@sprengnetter.de");
		karriereStellenboerseKontakt.typePhone("0152-23939891");
		karriereStellenboerseKontakt.typeCompany("TestUnternehmen GmbH");
		karriereStellenboerseKontakt.typeMessage("text");

		karriereStellenboerseKontakt.clickButtonAbsenden();
		karriereStellenboerseKontakt.getNameError();
	}

	@Test
	public void sendContactFormWithoutEmailaddress() {
		navigateToStellenboerseBottom();
		karriereStellenboerseKontakt.typeName("test test");
		karriereStellenboerseKontakt.typePhone("0152-23999891");
		karriereStellenboerseKontakt.typeCompany("TestUnternehmen GmbH");
		karriereStellenboerseKontakt.typeMessage("text");

		karriereStellenboerseKontakt.clickButtonAbsenden();
		karriereStellenboerseKontakt.getEmailError();
	}

	@Test
	public void sendContactFormWithoutText() {
		navigateToStellenboerseBottom();
		karriereStellenboerseKontakt.typeName("test test");
		karriereStellenboerseKontakt.typeEmail("test.test.@sprengnetter.de");
		karriereStellenboerseKontakt.typePhone("0152-29939891");
		karriereStellenboerseKontakt.typeCompany("TestUnternehmen GmbH");

		karriereStellenboerseKontakt.clickButtonAbsenden();
		karriereStellenboerseKontakt.getMessageError();
	}

	// Aufgabe 3
	@Test
	public void searchDelphientwickler() {
		pageSprengnetter.chooseSuche("Delphientwickler");
		pageSprengnetter.assertDelphientwickler();
	}

	private void navigateToStellenboerseBottom() {
		navigateToStellenboerse();
		pageStellenboerse.scrollPageStellenboerse();
	}

	private void navigateToStellenboerse() {
		pageSprengnetter.clickKarriere();
		pageSprengnetter.clickStellenboerse();
	}

	@AfterSuite
	public void after() {
		driver.quit();
	}
}
