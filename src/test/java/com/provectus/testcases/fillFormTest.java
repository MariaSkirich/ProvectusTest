package com.provectus.testcases;

import com.provectus.pages.FormsPage;
import com.provectus.utils.CommonMethods;
import com.provectus.utils.ConfigsReader;
import com.provectus.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class fillFormTest extends CommonMethods {

    @Test(groups = "smoke")
    public void fillForm() throws InterruptedException {
        FormsPage formsPage = new FormsPage();
        locatingElement(formsPage.forms);
        clickingOnSVG(formsPage.forms);
        click(formsPage.practiceForm);
        sendText(formsPage.firstNameBox, studentInfo("firstName"));
        sendText(formsPage.lastNameBox, studentInfo("lastName"));
        sendText(formsPage.userEmailBox, studentInfo("email"));

        String gender = studentInfo("gender");
        if (gender.equalsIgnoreCase("Female")) {
            jsClick(formsPage.femaleGender);
        } else if (gender.equalsIgnoreCase("Male")) {
            jsClick(formsPage.maleGender);
        } else {
            jsClick(formsPage.otherGender);
        }

        sendText(formsPage.phoneNumber, studentInfo("mobile"));

        String dateOfBirth = studentInfo("dateOfBirth");
        String[] DOB = dateOfBirth.split("/");
        String month = DOB[0];
        String day = DOB[1];
        String year = DOB[2];

        //click(formsPage.dateOfBirthBox);

        //calendarHandling(formsPage.monthDD, formsPage.yearDD, month, year, day, formsPage.weeksAndDays);


        String subjects = studentInfo("subjects");
        String[] sub = subjects.split(", ");

        for (int i = 0; i < sub.length; i++) {
            sendText(formsPage.subjectsAutoComplete, sub[i]);
            getWait();
            formsPage.subjectsAutoComplete.sendKeys(Keys.ENTER);
        }
        Thread.sleep(2000);

        String hobby = studentInfo("hobbies").toLowerCase();
        if (hobby.contains("music")) {
            locatingElement(formsPage.musicHobby);
            jsClick(formsPage.musicHobby);
        } else if (hobby.contains("reading")) {
            locatingElement(formsPage.musicHobby);
            jsClick(formsPage.readingHobby);
        } else if (hobby.contains("sports")) {
            locatingElement(formsPage.sportsHobby);
            jsClick(formsPage.sportsHobby);
        }

        formsPage.uploadPicture.sendKeys("C:\\Users\\Masha\\Desktop\\james.jpg");

        sendText(formsPage.textArea, studentInfo("currentAddress"));

        //the same problem with autocomplete
        sendText(formsPage.state, studentInfo("state"));
        formsPage.state.sendKeys(Keys.ENTER);
        sendText(formsPage.city, studentInfo("city"));
        formsPage.city.sendKeys(Keys.ENTER);

        jsClick(formsPage.submitButton);

        formsPage.message.isDisplayed();

    }


}
