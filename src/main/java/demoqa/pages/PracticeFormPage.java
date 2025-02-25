package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String number) {
        //firstName.sendKeys(name);
        type(firstName, name);
        type(lastName, surName);
        type(userEmail, email);
        type(userNumber, number);
        System.out.printf("✅ Personal data entered: First Name: [%s], Last Name: [%s], Email: [%s], Phone: [%s]%n",
                name, surName, email, number);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        try {
            String xpathGender = "//label[contains(text(),'" + gender + "')]";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
            System.out.printf("✅ Gender selected: %s \n", gender);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Gender element not found: [" + gender + "]." + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error selecting gender: [" + gender + "]." + e.getMessage());
        }
        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    public PracticeFormPage chooseDateAsString(String date) {
        click(dateOfBirthInput);
        String os = System.getProperty("os.name");
// type(dateOfBirthInput,date);
        if (os.contains("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        return this;
    }

    @FindBy(css = ".react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(css = ".react-datepicker__year-select")
    WebElement yearSelect;

    public PracticeFormPage chooseDate(String day, String month, String year) {
        if (day == null || month == null || year == null || day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            throw new IllegalArgumentException("❌ Invalid date values: day=[" + day + "], month=[" + month + "], year=[" + year + "]");
        }
        click(dateOfBirthInput);
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);

        By dayLocator = By.xpath("//div[contains(@aria-label, '" + month + "') and contains(@aria-label, '" + year + "') and text()='4']");
        WebElement dayElement = driver.findElement(dayLocator);
        dayElement.click();

        System.out.printf("✅ Date selected: %s %s %s%n", day, month, year); // %n – новая строка (аналог \n).
        return this;
    }


    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage enterSubject(String[] subjects) {
        if (subjects == null || subjects.length == 0) {
            throw new NullPointerException("❌ The `subjects` array is null or empty");
        }
        for (String subject : subjects) {
            type(subjectsInput, subject);
            subjectsInput.sendKeys(Keys.ENTER);
            System.out.println("✅ Selected Subject: " + subject);
        }
        return this; // ✅ Возвращаем `this` только после обработки всех элементов
    }

    public PracticeFormPage chooseHobbies(String[] hobbies) {
        if (hobbies == null || hobbies.length == 0) {
            throw new IllegalArgumentException("❌ The `hobbies` array is null or empty.");
        }

        for (String hobby : hobbies) {
            try {
                By hobbyLocator = By.xpath("//label[.='" + hobby + "']");
                WebElement element = driver.findElement(hobbyLocator);

                if (!element.isDisplayed()) {
                    System.out.println("⚠️ Warning: Hobby not found on the page: [" + hobby + "]");
                } else {
                    click(element);
                    System.out.println("✅ Selected hobby: " + hobby);
                }
            } catch (Exception e) {
                System.out.println();
                throw new RuntimeException("❌ Error selecting hobby: [" + hobby + "]" + e);
            }
        }
        return this;
    }

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadPicture(String imgPath) {
        uploadPicture.sendKeys(imgPath);
        System.out.printf("✅ Picture uploaded: %s%n", imgPath);
        return this;
    }

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public PracticeFormPage enterCurrentAddress(String address) {
        type(currentAddress, address);
        System.out.printf("✅ Address entered: %s%n", address);
        return this;
    }

    @FindBy(id = "state")
    WebElement stateContainer;

    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        click(stateContainer);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ State selected: %s%n", state);
        return this;
    }

    @FindBy(id = "city")
    WebElement cityContainer;
    @FindBy(id = "react-select-4-input")
    WebElement cityInput;

    public PracticeFormPage enterCity(String city) {
        click(cityContainer);
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ City selected: %s%n", city);
        return this;
    }

    @FindBy(id = "submit")
    WebElement submitButton;

    public PracticeFormPage submitForm() {
        click(submitButton);
        return this;
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement registrationModal;

    public PracticeFormPage verifySuccessRegistration(String text) {
        shouldHaveText(registrationModal, text, 5000);
        System.out.printf("✅ Registration success: %s%n", text);
        return this;
    }
}
