package utilities.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.reports.Reports;

import java.time.Duration;
import java.util.List;

public class WebController extends  WebDriverLib{
    public static WebElement createObject(String valueOfElement) {
    WebElement ele;
    String[] values = valueOfElement.split("=", 2);
        switch (values[0].toString().toLowerCase()) {
        case "xpath":
            try {

                ele = driver.findElement(By.xpath(values[1].toString()));
                return ele;
            } catch (Exception e) {
                return null;
            }

        case "id":
            try {
                ele = driver.findElement(By.id(values[1].toString()));
                return ele;
            } catch (Exception e) {
                return null;
            }

        case "css":
            try {
                ele = driver.findElement(By.cssSelector(values[1].toString()));
                return ele;
            } catch (Exception e) {
                return null;
            }
        default:
            return ele = null;
    }

}

    public static List<WebElement> createObjects(String valueOfElement) {

        List<WebElement> ele;
        String[] values = valueOfElement.split("=", 2);
        switch (values[0].toString().toLowerCase()) {
            case "xpath":
                ele = driver.findElements(By.xpath(values[1].toString()));
                if (ele != null)
                    return ele;
                else
                    return null;
            case "id":
                ele = driver.findElements(By.id(values[1].toString()));
                if (ele != null)
                    return ele;
                else
                    return null;
            case "css":
                ele = driver.findElements(By.cssSelector(values[1].toString()));
                if (ele != null)
                    return ele;
                else
                    return null;
            default:
                return ele = null;
        }

    }


    public static void clickOnObject(String valueOfElement, String report) {
        WebElement ele = createObject(valueOfElement);
        if ((ele != null) && (ele.isEnabled())) {
            ele.click();
            Reports.testStep(report);
        } else {
            Reports.Verification("Fail", report + "Element is not clickable");

        }

    }


    public static void inputText(String valueOfElement, String inputVlaue, String detailforReports) {
        WebElement ele = createObject(valueOfElement);
        if ((ele != null) && (ele.isEnabled())) {
            ele.sendKeys(inputVlaue);
            Reports.testStep(detailforReports + " " + inputVlaue);

        } else
            Reports.Verification("Fail", "Input_not_set");
    }

    public static WebElement RelativeLocator(String parentLocator, String tagName, String relativeType) {
        By element;
        WebElement el;
        var e = createObject(parentLocator);
        if (relativeType.toLowerCase().equals("above")) {
            element = RelativeLocator.with(By.tagName(tagName)).above(e);
            el = driver.findElement(element);
            return el;
        }
        if (relativeType.toLowerCase().equals("below")) {
            element = RelativeLocator.with(By.tagName(tagName)).below(e);
            el = driver.findElement(element);
            return el;
        }
        return null;
    }

    public static void scrollintoView(String elementProperty) {
        WebElement ele = createObject(elementProperty);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static void waitforElement(String elementProperty, String report) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            String[] values = elementProperty.split("=", 2);

            switch (values[0].toString().toLowerCase()) {

                case "xpath":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1].toString())));
                    Reports.testStep("Element found by xpath which is " + report);
                    break;

                case "id":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(values[1].toString())));
                    Reports.testStep("Element found by id " + report);
                    break;
            }
        } catch (Exception e) {
            Reports.testStep("Element not found " + report);
        }
    }

    public static void selectValueFromDropDownByText(String elementProperty, String selectionOfValue) {
        WebElement ele = createObject(elementProperty);
        Select se = new Select(ele);
        se.selectByVisibleText(selectionOfValue);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void selectFromDropDownByValue(String elementProperty, String value)

    {
        WebElement ele = createObject(elementProperty);
        Select se = new Select(ele);
        se.selectByValue(value);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void selectFromDropDownByIndex(String elementProperty, int index) {
        WebElement ele = createObject(elementProperty);
        Select se = new Select(ele);
        se.selectByIndex(index);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setValueForCheckbox(String elementProperty, boolean value) {
        WebElement ele = createObject(elementProperty);
        if (ele != null && ele.isDisplayed() && ele.isEnabled()) {
            if (value) {
                if (ele.getAttribute("checked") != null)
                    Reports.testStep("Checkbox already checked");
                else
                    ele.click();

            } else {

                if (ele.getAttribute("checked") == null)
                    Reports.testStep("Checkbox already checked");
                else
                    ele.click();


            }
        }

    }
}
