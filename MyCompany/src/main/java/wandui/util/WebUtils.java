package wandui.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class WebUtils extends DriverManager
{
	 public static WebElement CreateObject(String ValueofElement) {


	        WebElement ele;
	        String[] values = ValueofElement.split("=", 2);
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

	    public static List<WebElement> CreateObjects(String ValueofElement) {

	        List<WebElement> ele;
	        String[] values = ValueofElement.split("=", 2);
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


	    public static void clickonObject(String ValueofElement, String report)  {
	        WebElement ele = CreateObject(ValueofElement);
	        if ((ele != null) && (ele.isEnabled())) {
	            ele.click();
	            // ReportCommonMethods.TestStep(report);
	        } else {
	            System.out.println("Element is not clickable");
	            //   ReportCommonMethods.Verification("Fail", "Element is not clickable");

	        }

	    }


	    public static void setValueOnEditBox(String ValueofElement, String InputVlaue, String DetailforReports)
	    {
	        WebElement ele = CreateObject(ValueofElement);
	        if ((ele != null) && (ele.isEnabled())) {
	            ele.sendKeys(InputVlaue);
	            //   ReportCommonMethods.TestStep(DetailforReports + " " + InputVlaue);

	        }
//	        else
	        //  ReportCommonMethods.Verification("Fail", "Input_not_set");
	    }

	    public static WebElement RelativeLocator(String ParentLocator, String TagName, String RelativeType) {
	        By element;
	        WebElement el;
	        WebElement e = CreateObject(ParentLocator);
	        if (RelativeType.toLowerCase().equals("above")) {
	            element = RelativeLocator.with(By.tagName(TagName)).above(e);
	            el = driver.findElement(element);
	            return el;
	        }
	        if (RelativeType.toLowerCase().equals("below")) {
	            element = RelativeLocator.with(By.tagName(TagName)).below(e);
	            el = driver.findElement(element);
	            return el;
	        }
	        return null;
	    }

	    public static void ScrollintoView(String ElementProperty) {
	        WebElement ele = CreateObject(ElementProperty);
	        JavascriptExecutor je = (JavascriptExecutor) driver;
	        je.executeScript("arguments[0].scrollIntoView(true);", ele);
	    }

	    public static void WaitforElement(String ElementProperty , String Report) {

	        try {

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            String[] values = ElementProperty.split("=", 2);

	            switch (values[0].toString().toLowerCase()) {

	                case "xpath":
	                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1].toString())));
	                    // ReportCommonMethods.TestStep("Element found by xpath which is " + Report );
	                    break;

	                case "id":
	                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(values[1].toString())));
	                    //   ReportCommonMethods.TestStep("Element found by id " + Report);
	                    break;
	            }
	        } catch (Exception e) {
	            //  ReportCommonMethods.TestStep("Element not found " + Report);
	        }
	    }

	    public static void SelectValuefromDoropDownByText(String ElementProperty, String SelectionOfValue)

	            throws InterruptedException {
	        WebElement ele = CreateObject(ElementProperty);
	        Select se = new Select(ele);
	        se.selectByVisibleText(SelectionOfValue);
	        Thread.sleep(500);

	    }

	    public static void SelectValuefromDoropDownByIndex(String ElementProperty, int Index)

	            throws InterruptedException {
	        WebElement ele = CreateObject(ElementProperty);
	        Select se = new Select(ele);
	        se.selectByIndex(Index);
	        Thread.sleep(500);

	    }

	    public static void setValueFOrCheckbox(String ElementProperty , boolean value)
	    {
	        WebElement ele = CreateObject(ElementProperty);
	        if(value==true) {
//	            if (ele.getAttribute("checked")!=null)
//	               // ReportCommonMethods.TestStep("Checkbox already checked");
//	            else
	            ele.click();
	        }
	        else
	        {

//	            if (ele.getAttribute("checked")==null)
//	              //  ReportCommonMethods.TestStep("Checkbox already checked");
//	            else
	            ele.click();


	        }
	    }
}
