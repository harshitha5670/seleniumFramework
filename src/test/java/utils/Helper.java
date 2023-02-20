package utils;

import io.cucumber.java.an.E;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class Helper {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(DriverFactory.driver, Duration.ofSeconds(20));

    public WebElement getWebelement(String identyfierType, String identifierValue) {
        switch (identyfierType.toUpperCase()) {
            case "ID":
                return driver.findElement(By.id(identifierValue));
            case "CSS":
                return driver.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return driver.findElement(By.tagName(identifierValue));
            case  "XPATH":
                return driver.findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebelement(String identyfierType, String identifierValue) {
        wait = new WebDriverWait(DriverFactory.driver, Duration.ofSeconds(50));
        switch (identyfierType.toUpperCase()) {
            case "ID":
                return driver.findElements(By.id(identifierValue));
            case "CSS":
                return driver.findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return driver.findElements(By.tagName(identifierValue));
            case  "XPATH":
                return driver.findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public boolean elementIsPresent(String type, String value) {
        boolean displayed = true;
        try {
           getWebelement(type, value).isDisplayed();
           if (displayed) {
               System.out.println("Element present");;
           } else {
               Assert.fail("Element is not present");
           }
       }catch (Exception e) {
           e.printStackTrace();
       } return displayed;
    }

    public void clickOnElement(String type, String value) {
        WebElement element = getWebelement(type, value);
        if (elementIsPresent(type,value)) {
            try {
                waitFor(3000);
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Element is not present to click");
            }
        }
    }

    public void enterText(String type, String value, String text) {
        WebElement element = getWebelement(type,value);
        try{
            element.sendKeys(text);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Element not present");
        }
    }

    public void elementIsEnabled(String type, String value) {
        try {
            boolean displayed = true;
            getWebelement(type, value).isEnabled();
            if (displayed) {
                System.out.println("Element is enabled");
            } else {
                System.out.println("Element is not enabled");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTextOfAnElement(String type, String value) {
        WebElement element = null;
        try {
            element = getWebelement(type,value);
            String text=element.getText();
            System.out.println("Value -->"+text);
            return text;
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to fetch text from an elemet: "+ element );
        }
        return null;
    }

    public List<String> getTextFromMultipleElements(String type, String value) {
        List<String> text = new ArrayList<>();
        try {
            List<WebElement> elements = getListWebelement(type,value);
            for (WebElement w : elements) {
                   text.add(w.getText());
            }
            System.out.println("Values ->" + text);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to fetch text from elements");
        }
        return text;
    }

    public void selectValueInDropDownByVisibleText(String type, String value, String text) {
        try {
            WebElement element = getWebelement(type, value);
            Select select = new Select(element);
            List<WebElement> opt = select.getOptions();
            waitFor(3000);
//            for (WebElement options: opt) {
                    select.selectByVisibleText(text);
//            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDropDownByValue( String type, String value, String text) {
        try {
            WebElement element = getWebelement(type, value);
            Select select = new Select(element);
            List<WebElement> opt = select.getOptions();
            for (WebElement options: opt) {
                select.selectByValue(text);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDropDownByIndex( String type, String value, int index) {
        try {
            WebElement element = getWebelement(type, value);
            Select select = new Select(element);
            List<WebElement> opt = select.getOptions();
            if(opt.size() <= index){
                select.selectByIndex(index);
            }else {
                Assert.fail("Index not present");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitFor(int number) {
        try {
            Thread.sleep(number);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectCheckbox(String type, String value) {
        WebElement element = getWebelement(type,value);
        try {
            if(element.isSelected()) {
                System.out.println("Element is already selected");
            }else {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            }
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void mouseActions(String type, String value, String action) {
        Actions actions = new Actions(driver);
        WebElement element = getWebelement(type, value);
        try {
            if (action.equalsIgnoreCase("Mouse hover")) {
                actions.moveToElement(element).perform();
            } else if(action.equalsIgnoreCase("drag and drop")) {
                actions.dragAndDrop(element,element).perform();
            } else  if(action.equalsIgnoreCase("scrollToElement")) {
                actions.scrollToElement(element).perform();
            }

        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail(element+ " not present to perform actions");
        }
    }

    public String getAttributeOfAnElement(String type, String value, String attributeValue) {
        WebElement element = null;
        try {
            element = getWebelement(type,value);
            String valueOfAttribute = element.getAttribute(attributeValue);
            System.out.println("Attribute value -->" + valueOfAttribute);
            return valueOfAttribute;
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to fetch attribute value of "+ element);
        }
        return null;
    }

    public void scrollToElement(String type, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = getWebelement(type,value);
        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to scroll to "+ element);
        }
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollBy(0,3000)");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            waitFor(3000);
            js.executeScript("window.scrollBy(5000,0)");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}