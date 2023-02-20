package step_definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.junit.Assert;
import page_object.Signup_PageObject;

import java.util.List;

public class Signup_StepDefinition {
    Signup_PageObject signup = new Signup_PageObject();

    @When("I navigate to signup page in the application")
    public void validateApplicationNavigation() {
        signup.clickOnSignUpButton();
    }

    @When("I validate title of the page: {string}")
    public void validateTitleOfPage(String title) {
        Assert.assertEquals(signup.getTitleOfApplication(),title);
    }

    @When("I validate url of the page: {string}")
    public void validateCurrentUrlOfPage(String url) {
        Assert.assertEquals(signup.getUrlOfApplication(),url);
    }

    @When("Validate below mentioned labels are displayed in page")
    public void validateFiledsInPage(DataTable table) {
        List<String> expected = table.asList(String.class);
        Assert.assertEquals(expected,signup.validateFieldOnPage());
    }

    @When("I navigate to {string} signup page")
    public void navigateToSpecificSignupPage(String name) {
        signup.selectSignupRadiobutton(name);
    }

    @When("I validate default India country is selected in {string} dropdown")
    public void selectDropdown(String dropdown) {
        Assert.assertEquals(signup.validateDefaultDropdownValue(dropdown),"true");
    }

    @When("I select {string} in {string} dropdown")
    public void selectValueInDropdown(String value, String name) {
        signup.selectValueFromDropdown(name,value);
    }

    @When("I enter {string} mandatory field: {string}")
    public void enterValueInFields(String  fieldName, String  value){
        signup.enterValueInTextFields(fieldName,value);
    }

    @When("I select register terms and agree checkbox")
    public  void selectTermsCheckbox(){
        signup.selectTermsCheckbox();
    }

    @When("I select register newsletter and offer checkbox")
    public void selectNewsletterCheckbox() {
        signup.selectNewLetterCheckbox();
    }

    @When("I enter captcha value as per given sum value")
    public void enterCaptchaValue() {
        signup.enterCaptchaValue();
    }

    @When("I click on open account button")
    public void clickOnSignUpButton() {
        signup.clickOnOpenAccountButton();
    }

    @When("I should see {string} error message tooltip at {string}")
    public void validateErrorMessage(String  message, String fieldName) {
        Assert.assertEquals(signup.validateToolTipErrorMessage(fieldName), message);
    }
}
