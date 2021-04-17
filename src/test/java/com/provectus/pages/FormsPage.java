package com.provectus.pages;

import com.provectus.utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormsPage extends CommonMethods {
    @FindBy(xpath = "//*[text()='Forms']")
    public WebElement forms;

    @FindBy(xpath = "//span[contains(text(),'Practice Form')]")
    public WebElement practiceForm;

    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstNameBox;

    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastNameBox;

    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmailBox;

    @FindBy(xpath = "//input[@id='gender-radio-1']")
    public WebElement maleGender;

    @FindBy(xpath = "//input[@id='gender-radio-2']")
    public WebElement femaleGender;

    @FindBy(xpath = "//input[@id='gender-radio-2']")
    public WebElement otherGender;

    @FindBy(xpath = "//input[@id='userNumber']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
    public WebElement dateOfBirthBox;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthDD;

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearDD;

    @FindBy(xpath = "//div[@class='react-datepicker__week']")
    public List<WebElement> weeksAndDays;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-1']")
    public WebElement sportsHobby;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-2']")
    public WebElement readingHobby;

    @FindBy(xpath = "//input[@id='hobbies-checkbox-3']")
    public WebElement musicHobby;

    @FindBy(xpath = "//input[@id='uploadPicture']")
    public WebElement uploadPicture;

    @FindBy(xpath = "//input[@id='subjectsInput']")
    public WebElement subjectsAutoComplete;

    @FindBy(xpath = "//textarea[@class='form-control']")
    public WebElement textArea;

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElement state;

    @FindBy(xpath = "//input[@id='react-select-4-input']")
    public WebElement city;

    @FindBy(xpath = "//button[@id='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//div[@id='example-modal-sizes-title-lg']")
    public WebElement message;

    public FormsPage() {
        PageFactory.initElements(driver, this);
    }


}
