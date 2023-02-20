package page_object;

import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.Helper;
import utils.ObjectRepository;

import java.util.List;

public class Signup_PageObject extends DriverFactory {
    Helper helper = new Helper();
    WebDriver driver = DriverFactory.getDriver();

    public void clickOnSignUpButton() {
        helper.clickOnElement("ID", ObjectRepository.signUp_button);
    }
    public String getTitleOfApplication() {

        return driver.getTitle();
    }

    public String getUrlOfApplication() {

        return driver.getCurrentUrl();
    }

    public void selectSignupRadiobutton(String name) {
        helper.clickOnElement("xpath", ObjectRepository.radioButton.replace("{param}", name));
    }

    public void enterValueInTextFields(String value, String text) {
        helper.enterText("xpath",ObjectRepository.textBox.replace("{param}",value),text);
    }

    public void selectTermsCheckbox(){
        helper.waitFor(3000);
        helper.scrollDown();
        helper.selectCheckbox("id",ObjectRepository.agree_checkbox);
    }

    public  void selectNewLetterCheckbox() {
        helper.waitFor(3000);
        helper.scrollDown();
        helper.selectCheckbox("id",ObjectRepository.newsLetter_checkbox);
    }

    public void clickOnOpenAccountButton() {
        helper.waitFor(3000);
        helper.scrollDown();
        helper.clickOnElement("xpath",ObjectRepository.openAccount_button);
    }

    public void enterCaptchaValue() {
        helper.waitFor(3000);
        helper.scrollDown();
        String value = helper.getTextOfAnElement("ID",ObjectRepository.captcha);
        String[] calculateValue;
        String finalValue = null;
        if(value.contains("+")) {
            calculateValue = value.trim().split("[+]");
            finalValue = String.valueOf(Integer.parseInt(calculateValue[0].trim()) + Integer.parseInt(calculateValue[1].trim().split("=")[0].trim()));
        }
        else {
            calculateValue = value.trim().split("[-]");
            finalValue = String.valueOf(Integer.parseInt(calculateValue[0].trim()) - Integer.parseInt(calculateValue[1].trim().split("=")[0].trim()));
        }
            helper.enterText("ID",ObjectRepository.captcha_textbox,finalValue);
    }

    public String validateToolTipErrorMessage(String field) {
        helper.scrollUp();
        if(field.equals("terms checkbox")){
            helper.mouseActions("id", ObjectRepository.agree_checkbox,"mouse hover");
        }else {
            helper.mouseActions("xpath", ObjectRepository.textBox.replace("{param}",field),"mouse hover");
        }
        return helper.getTextOfAnElement("css",ObjectRepository.errormessage_tooltip);
    }

    public void selectValueFromDropdown(String dropdownname, String value) {
        helper.selectValueInDropDownByVisibleText("xpath", ObjectRepository.drop_down.replace("{param}",dropdownname), value);
    }

    public String validateDefaultDropdownValue(String dropdownname) {
       return helper.getAttributeOfAnElement("xpath",ObjectRepository.defaultDropdown_value.replace("{param}",dropdownname),
                "selected");
    }

    public List<String> validateFieldOnPage() {
        List<String> actual = helper.getTextFromMultipleElements("css",ObjectRepository.signUpPage_labels);
        actual.removeIf(String::isEmpty);
        return actual;
    }
}
