package com.provectus.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    protected static WebDriver driver;

    /**
     * This method will launch the browser and navigate to the url from configurations file
     */
    @BeforeMethod(alwaysRun = true)
    public static void setUp(){
        ConfigsReader.readProperties(Constants.CONFIGURATIONS_FILEPATH);
        switch (ConfigsReader.getPropertyValue("browser").toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                driver=new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported");
        }
        driver.get(ConfigsReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * This method will close any opened browser
     */
    @AfterMethod (alwaysRun = true)
    public static void tearDown(){
        if(driver !=null){
            driver.quit();
        }
    }

    /**
     * This method returns values from studentInfo file
     * @param key
     * @return
     */
    public static String studentInfo(String key){
        return ConfigsReader.readProperties(Constants.STUDENT_INFO_FILEPATH).getProperty(key);
    }

    /**
     * This method will clear a textbox and send text to it
     *
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    /**
     * This method will return an object of explicit wait
     *
     * @return WebDriverWait
     */
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    /**
     * This method will wait until the given element is clickable
     *
     * @param element
     */
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method will wait until the given element is clickable and then click
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);

    }

    public static void locatingElement(WebElement element){
        getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickingOnSVG(WebElement element){
        Actions builder=new Actions(driver);
        builder.moveToElement(element).click().build().perform();
    }

    /**
     * This method returns a unique time that the test took place at
     * @param pattern
     * @return
     */
    public static String getTimeStamp(String pattern) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }

    /**
     * This method will take a screenshot whenever the test is passed or failed
     *
     * @param fileName
     */
    public static void takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+ ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method allows to pick the date from the calendar based on the passed parameters.
     * @param mDD
     * @param yDD
     * @param month
     * @param year
     * @param day
     * @param dates
     */
    public static void calendarHandling(WebElement mDD, WebElement yDD, String month, String year, String day, List<WebElement> dates) {
        Select monthDD = new Select(mDD);
        monthDD.selectByVisibleText(month);

        Select yearDD = new Select(yDD);
        yearDD.selectByVisibleText(year);

        for (WebElement date : dates) {
            if (date.getText().equals(day)) {
                date.click();
                break;
            }
        }
    }


    public static void radioButtonsClick(List<WebElement> elements, String value, String expectedStr){
        for(WebElement element:elements){
            if(element.getAttribute(value).equalsIgnoreCase(expectedStr)){
                jsClick(element);
            }
        }
    }


}
