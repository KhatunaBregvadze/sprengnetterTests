package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class KarriereStellenboerseKontakt extends Base {

	@FindBy(name = "tx_rmform_form[mail][name]")
	private WebElement nameElement;

	@FindBy(name = "tx_rmform_form[mail][email]")
	private WebElement emailElement;

	@FindBy(name = "tx_rmform_form[mail][phone]")
	private WebElement phoneElement;

	@FindBy(name = "tx_rmform_form[mail][company]")
	private WebElement companyElement;

	@FindBy(name = "tx_rmform_form[mail][message]")
	private WebElement messageElement;

	@FindBy(id = "mailddownload-form-submit-1042")
	private WebElement buttonAbsendenElement;

	@FindBy(id = "name-1042-error")
	private WebElement nameError;

	@FindBy(id = "email-1042-error")
	private WebElement emailError;

	@FindBy(id = "message-1042-error")
	private WebElement messageError;

	public KarriereStellenboerseKontakt(WebDriver driver) {
		super(driver);
	}

	public void typeName(String name) {
		type(nameElement, 4, name);
	}

	public void typeEmail(String email) {
		type(emailElement, 4, email);
	}

	public void typePhone(String phone) {
		type(phoneElement, 4, phone);
	}

	public void typeCompany(String company) {
		type(companyElement, 4, company);
	}

	public void typeMessage(String message) {
		type(messageElement, 4, message);
	}

	public void clickButtonAbsenden() {
		click(buttonAbsendenElement, 4);
	}

	public void getNameError() {
		Assert.assertTrue("Dieses Feld ist ein Pflichtfeld.".equals(nameError.getText()));
	}

	public void getEmailError() {
		Assert.assertTrue("Dieses Feld ist ein Pflichtfeld.".equals(emailError.getText()));
	}

	public void getMessageError() {
		Assert.assertTrue("Dieses Feld ist ein Pflichtfeld.".equals(messageError.getText()));
	}
}
