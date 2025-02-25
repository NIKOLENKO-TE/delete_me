package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidePanel extends BasePage {
    public SidePanel(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //* Login
    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement login;

    public LoginPage selectLogin() {
        //click(login);
        clickWithJS(login, 0, 500);
        return new LoginPage(driver, wait);
    }

    //* Alerts
    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;

    public AlertsPage selectAlerts() {
        clickWithJS(alerts, 0, 500);
        return new AlertsPage(driver, wait);
    }

    //* Frames
    @FindBy(xpath = "//span[.='Frames']")
    WebElement frames;

    public AlertsPage selectFrames() {
        clickWithJS(frames, 0, 500);
        return new AlertsPage(driver, wait);
    }

    //* Browser Windows
    @FindBy(xpath = "//span[.='Browser Windows']")
    WebElement browserWindows;

    public AlertsPage selectBrowserWindows() {
        clickWithJS(browserWindows, 0, 500);
        return new AlertsPage(driver, wait);
    }


    //* Select Menu
    @FindBy(xpath = "//span[.='Select Menu']")
    WebElement selectMenu;

    public WidgetsPage selectSelectMenu() {
        clickWithJS(selectMenu, 0, 800);
        return new WidgetsPage(driver, wait);
    }

    //* Slider Menu
    @FindBy(xpath = "//span[.='Slider']")
    WebElement sliderMenu;

    public SliderPage selectSliderMenu() {
        clickWithJS(sliderMenu, 0, 800);
        return new SliderPage(driver, wait);
    }

    //* Tool Tips
    @FindBy(xpath = "//span[.='Tool Tips']")
    WebElement toolTips;

    public ToolTipsPage selectToolTipsMenu() {
        clickWithJS(toolTips, 0, 800);
        return new ToolTipsPage(driver, wait);
    }

    //* Auto Complete
    @FindBy(xpath = "//span[.='Auto Complete']")
    WebElement autoCompleteMenu;

    public AutoCompletePage selectAutoCompleteMenu() {
        click(autoCompleteMenu);
        return new AutoCompletePage(driver, wait);
    }

    //* Practice Form
    @FindBy(xpath = "//span[.='Practice Form']")
    WebElement practiceForm;

    public PracticeFormPage selectPracticeForm() {
        click(practiceForm);
        return new PracticeFormPage(driver, wait);
    }
}
