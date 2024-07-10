package Regressionpageobjects;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;

import AbstractComponents.AbstractComponent;

public class Project_Creation_Page extends AbstractComponent {
	WebDriver driver;

	public Project_Creation_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "span[aria-label='appstore-add']")
	WebElement createButton;
	By createBy = By.cssSelector("span[aria-label='appstore-add']");

	@FindBy(css = "body.ant-scrolling-effect:nth-child(2) div.ant-modal-root div.ant-modal-wrap.myw-draggable-modal.ant-modal-centered div.ant-modal div.react-draggable:nth-child(2) div.ant-modal-content div.ant-modal-body div.Collapsible div.Collapsible__contentOuter div.Collapsible__contentInner div.box.react-resizable form.ant-form.ant-form-horizontal div.ant-row:nth-child(2) div.ant-col.ant-col-12 div.ant-row.ant-form-item div.ant-col.ant-form-item-control:nth-child(2) div.ant-form-item-control-input div.ant-form-item-control-input-content span.ant-input-group-wrapper.ant-input-search span.ant-input-wrapper.ant-input-group span.ant-input-group-addon > button.ant-btn.ant-btn-icon-only.ant-input-search-button")
	WebElement searchButton;
	By searchBy = By.cssSelector(
			"body.ant-scrolling-effect:nth-child(2) div.ant-modal-root div.ant-modal-wrap.myw-draggable-modal.ant-modal-centered div.ant-modal div.react-draggable:nth-child(2) div.ant-modal-content div.ant-modal-body div.Collapsible div.Collapsible__contentOuter div.Collapsible__contentInner div.box.react-resizable form.ant-form.ant-form-horizontal div.ant-row:nth-child(2) div.ant-col.ant-col-12 div.ant-row.ant-form-item div.ant-col.ant-form-item-control:nth-child(2) div.ant-form-item-control-input div.ant-form-item-control-input-content span.ant-input-group-wrapper.ant-input-search span.ant-input-wrapper.ant-input-group span.ant-input-group-addon > button.ant-btn.ant-btn-icon-only.ant-input-search-button");

	@FindBy(xpath = "(//input[@placeholder='search'])[1]")
	WebElement inputSearch;
	By inputBy = By.xpath("(//input[@placeholder='search'])[1]");

	@FindBy(css = "td[class='ant-table-cell ant-table-selection-column']")
	WebElement selectSearch;
	By selectSearchBy = By.cssSelector("td[class='ant-table-cell ant-table-selection-column']");

	@FindBy(xpath = "//span[contains(text(),'ok')]")
	WebElement okButton;

	@FindBy(xpath = "(//input[@id='create_project_form_contractor'])[1]")
	WebElement contractorButton;

	@FindBy(xpath = "//div[@title='GRIDSOURCE INCORPORATED, LLC']")
	WebElement dropdownOptionSelect;

	@FindBy(css = "div[class='ant-col ant-col-12'] div[class='ant-row ant-form-item'] div[class='ant-select-selector']")
	WebElement projectsizebutton;

	@FindBy(css = "div[title='0-2k'] div[class='ant-select-item-option-content']")
	WebElement projectsize;
	By projectsizeby = By.cssSelector("div[title='0-2k'] div[class='ant-select-item-option-content']");

	@FindBy(css = "div[class='ant-picker ant-picker-range ant-picker-focused'] input[placeholder='Start date']")
	WebElement projectdateinput;
	By projectdateinputby = By
			.cssSelector("div[class='ant-picker ant-picker-range ant-picker-focused'] input[placeholder='Start date']");

	@FindBy(xpath = "(//button[@type='submit'])[3]")
	WebElement projectsubmitbutton;

	@FindBy(xpath = "(//div[@style='width: 250px; display: inline-block;'])[1]")
	WebElement projectid;
	By projectidby = By.xpath("(//div[@style='width: 250px; display: inline-block;'])[1]");

	private Properties loadProperties() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\src\\main\\java\\RegressionResources\\Globaldata.properties");
		prop.load(fis);
		return prop;
	}

	public void goTo() throws IOException {
		Properties prop = loadProperties();
		String url = prop.getProperty("URL");
		driver.get(url);
	}

	public void createButton() {
		waitForElementToAppear(createBy);
		createButton.click();
	}

	public void searchButton() {
		waitForElementToAppear(searchBy);
		searchButton.click();
	}

	public String getSearchValue() throws IOException {
		Properties prop = loadProperties();
		return prop.getProperty("Wirecenter");
	}

	public String inputSearch() throws IOException {
		String wirecenter = getSearchValue();
		inputSearch.sendKeys(wirecenter);
		return wirecenter;
	}

	public void selectSearch() throws InterruptedException {
		waitForElementToAppear(selectSearchBy);
		selectSearch.click();
	}

	public void okButton() {
		okButton.click();
	}

	public void selectContractorOption() throws IOException {
		clickElement(contractorButton);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-select-item-option-content")));

		Properties prop = loadProperties();
		String contractorName = prop.getProperty("ContractorName");

		for (int attempt = 0; attempt < 3; attempt++) {
			try {
				List<WebElement> options = driver.findElements(By.cssSelector("div.ant-select-item-option"));
				Actions actions = new Actions(driver);
				boolean found = false;

				for (WebElement option : options) {
					WebElement optionContent = option.findElement(By.cssSelector("div.ant-select-item-option-content"));
					String optionText = optionContent.getText();
					// System.out.println("Checking option: " + optionText); // Logging for
					// debugging

					if (optionText.equals(contractorName)) {
						actions.moveToElement(optionContent).click().perform();
						found = true;
						System.out.println("Selected contractor: " + optionText); // Logging for debugging
						break;
					}
				}

				while (!found) {
					actions.sendKeys(Keys.DOWN).perform();
					Thread.sleep(500); // Adding a brief pause to ensure the dropdown has updated
					options = driver.findElements(By.cssSelector("div.ant-select-item-option"));
					for (WebElement option : options) {
						WebElement optionContent = option
								.findElement(By.cssSelector("div.ant-select-item-option-content"));
						String optionText = optionContent.getText();
						// System.out.println("Checking option: " + optionText); // Logging for
						// debugging

						if (optionText.equals(contractorName)) {
							actions.moveToElement(optionContent).click().perform();
							found = true;
							// System.out.println("Selected contractor: " + optionText); // Logging for
							// debugging
							break;
						}
					}
				}

				if (found) {
					break;
				}

			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException caught. Retrying...");
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught. Retrying...");
			}
		}
	}

	public void Projectsize() {
		projectsizebutton.click();
		waitForElementToAppear(projectsizeby);
		projectsize.click();

	}

	public void selectProjectDates(String startDate, String endDate) throws InterruptedException {
		selectDateRange("(//input[@placeholder='Start date'])[1]", startDate, endDate, 2);
	}

	public void selectSurveyDates(String startDate, String endDate) throws InterruptedException {
		selectDateRange("(//input[@placeholder='Start date'])[2]", startDate, endDate, 4);
	}

	public void selectPermitDates(String startDate, String endDate) throws InterruptedException {
		selectDateRange("(//input[@placeholder='Start date'])[3]", startDate, endDate, 6);
	}

	public void selectOltDates(String startDate, String endDate) throws InterruptedException {
		selectDateRange("(//input[@placeholder='Start date'])[4]", startDate, endDate, 8);
	}

	// Combined method to select start and end dates
	private void selectDateRange(String startDateInputXPath, String startDate, String endDate, int nextPageButtonIndex)
			throws InterruptedException {
		// Find and click the start date input
		WebElement startDateInput = driver.findElement(By.xpath(startDateInputXPath));
		startDateInput.click();
		// Thread.sleep(500);

		// Select the start date
		if (selectSpecificDate(startDate, nextPageButtonIndex)) {
			// Select the end date
			selectSpecificDate(endDate, nextPageButtonIndex);
		}
	}

	// Helper method to find and click a date
	private boolean selectSpecificDate(String targetDate, int nextPageButtonIndex) throws InterruptedException {
		boolean dateFound = false;

		// Loop to navigate through calendar pages
		while (!dateFound) {
			// Locate all date elements
			List<WebElement> dateElements = driver
					.findElements(By.xpath("//td[@class='ant-picker-cell ant-picker-cell-in-view']"));

			// Loop through the date elements
			for (WebElement dateElement : dateElements) {
				String dateTitle = dateElement.getAttribute("title");

				// Check if the date matches the target date
				if (targetDate.equals(dateTitle)) {
					// Click on the date element
					dateElement.click();
					dateFound = true;
					break;
				}
			}

			// If the date was not found on the current page, click the next page button
			if (!dateFound) {
				try {
					WebElement nextPageButton = driver.findElement(
							By.xpath("(//button[@class='ant-picker-header-next-btn'])[" + nextPageButtonIndex + "]"));
					nextPageButton.click();

					// Optional: Add a wait to allow page to load
					Thread.sleep(2000);
				} catch (Exception e) {
					System.out.println("Next page button not found or other exception occurred: " + e.getMessage());
					break;
				}
			}
		}

		return dateFound;
	}

	public void submitButton() {

		projectsubmitbutton.click();

	}

	public void wirecenterid() {

		waitForElementToAppear(projectidby);

		String projectIdText = projectid.getText(); // Retrieve the text from projectid
		System.out.print(projectIdText); // Print the text
		String csvFilePath = "C:\\Users\\niranjans\\eclipse-workspace\\IQGEO_Automation\\Project Ids\\Wirecenter id.csv";
	
		 try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
		        String[] record = { projectIdText };
		        writer.writeNext(record);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	
	}

