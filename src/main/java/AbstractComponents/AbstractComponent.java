package AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractComponent {
    protected WebDriver driver; // Change visibility to protected

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void selectDropdownOptionByText(WebElement dropdown, String optionText) {
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdown).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-select-item-option-content")));

        for (WebElement option : driver.findElements(By.cssSelector("div.ant-select-item-option"))) {
            WebElement optionContent = option.findElement(By.cssSelector("div.ant-select-item-option-content"));
            if (optionContent.getText().equals(optionText)) {
                actions.moveToElement(optionContent).click().perform();
                return;
            }
        }
        throw new IllegalArgumentException("Option not found in dropdown: " + optionText);
    }

    public boolean selectDateRange(WebElement dateInput, String startDate, String endDate) throws InterruptedException {
        boolean dateFound = false;
        dateInput.click();

        dateFound = selectSpecificDate(startDate);
        if (dateFound) {
            dateFound = selectSpecificDate(endDate);
        }

        return dateFound;
    }

    private boolean selectSpecificDate(String targetDate) throws InterruptedException {
        for (WebElement dateElement : driver.findElements(By.xpath("//td[@class='ant-picker-cell ant-picker-cell-in-view']"))) {
            if (targetDate.equals(dateElement.getAttribute("title"))) {
                dateElement.click();
                return true;
            }
        }
        return false;
    }
}
