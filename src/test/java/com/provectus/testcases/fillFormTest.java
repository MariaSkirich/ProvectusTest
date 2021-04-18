package com.provectus.testcases;

import com.provectus.pages.FormsPage;
import com.provectus.utils.CommonMethods;
import org.openqa.selenium.Keys;
import org.testng.Assert;
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

        radioButtonsClick(formsPage.genderButtons, "value", gender);

        sendText(formsPage.phoneNumber, studentInfo("mobile"));

        String dateOfBirth = studentInfo("dateOfBirth");
        String[] DOB = dateOfBirth.split("/");
        String month = DOB[0];
        String day = DOB[1];
        String year = DOB[2];

        click(formsPage.dateOfBirthBox);

        calendarHandling(formsPage.monthDD, formsPage.yearDD, month, year, day, formsPage.weeksAndDays);

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

        sendText(formsPage.textArea, studentInfo("currentAddress"));

        sendText(formsPage.state, studentInfo("state"));
        formsPage.state.sendKeys(Keys.ENTER);
        sendText(formsPage.city, studentInfo("city"));
        formsPage.city.sendKeys(Keys.ENTER);

        jsClick(formsPage.submitButton);

        formsPage.message.isDisplayed();

    }

    @Test (groups = "smoke")
    public void fillEmptyForm() throws InterruptedException {
        FormsPage formsPage = new FormsPage();
        locatingElement(formsPage.forms);
        clickingOnSVG(formsPage.forms);
        click(formsPage.practiceForm);

        jsClick(formsPage.submitButton);

        getWait();

        locatingElement(formsPage.firstNameBox);

        Thread.sleep(2000);

        String expectedColorCode="rgb(220, 53, 69)";

        String colorCodeFN=formsPage.firstNameBox.getCssValue("border-color");

        Assert.assertEquals(colorCodeFN, expectedColorCode);

        String colorCodeLN=formsPage.lastNameBox.getCssValue("border-color");

        Assert.assertEquals(colorCodeLN, expectedColorCode);

        String colorCodeG="rgba(220, 53, 69, 1)";

        String colorCodeGender=formsPage.genderLabel.getCssValue("color");
        Assert.assertEquals(colorCodeGender, colorCodeG);

    }


}
