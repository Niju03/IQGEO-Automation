package Regressionpageobjects;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import AbstractComponents.AbstractComponent;

public class Fps_Page extends AbstractComponent {

	WebDriver driver;

	public Fps_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='text-search']")
	WebElement Searchbox;

	By searchboxby = By.xpath("//input[@id='text-search']");

	@FindBy(xpath = "//li[@id='details-editable']")
	WebElement Edit;

	By Editby = By.xpath("//li[@id='details-editable']");

	@FindBy(xpath = "(//input[@class='text ui-input'])[2]")
	WebElement CMtrackingno;

	By cmtrackingnoby = By.xpath("(//input[@class='text ui-input'])[2]");

	@FindBy(xpath = "//button[@class='button primary-btn save ui-button ui-corner-all ui-widget']")
	WebElement savebuuton;

	public void FPSsearch() throws InterruptedException {
		// Load properties file
		Properties properties = new Properties();
		try (FileInputStream input = new FileInputStream(
				"C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\src\\main\\java\\RegressionResources\\Globaldata.properties")) {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// Get the manual string and search keyword from the properties file
		String manualString = properties.getProperty("FPSsearch");
		String searchKeyword = properties.getProperty("Wirecenter");

		// Wait for the search box to appear and send the search keyword
		waitForElementToAppear(searchboxby);
		Searchbox.sendKeys(searchKeyword);

		// Wait for the search results to load
		Thread.sleep(5000); // Adjust sleep time as needed

		// Get the list of search result items
		List<WebElement> listItems = driver.findElements(By.xpath("//ul[@class='noStyleList']/li"));

		for (WebElement listItem : listItems) {
			// Get the span element within the list item
			
			
			WebElement spanElement = listItem
					.findElement(By.xpath(".//span[@class='search-result-label suggestion-item-label']"));

			// Get the title attribute text
			String titleText = spanElement.getAttribute("title");

			// Check if the title matches the manual string
			if (titleText.equals(manualString)) {
				// Perform click action or further actions as needed
				spanElement.click();

				break; // Exit loop after clicking the first matching element
			}
		}
	}

	public void Editscenario() {
		waitForElementToAppear(Editby);
		Edit.click();
	}

	public void CMtrackingno() throws CsvValidationException, IOException {

		FileReader fileReader = new FileReader(
				"C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\Project Ids\\Wirecenter id.csv");
		com.opencsv.CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(0).build();
		String[] firstRow = reader.readNext();

		if (firstRow != null && firstRow.length > 0) {
			String firstCell = firstRow[0];

			waitForElementToAppear(cmtrackingnoby);
			
			CMtrackingno.clear();
			CMtrackingno.sendKeys(firstCell);

		}

	}

	public void Save() {

		savebuuton.click();

	}
}