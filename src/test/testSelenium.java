package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testSelenium {

	public static void main(String[] args) throws Exception {

		// Load browser
		System.setProperty("webdriver.chrome.driver", "C://Users//fabio//Desktop//chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Load browser
		// WebDriver driver = new FirefoxDriver();
		
		// Maximize browser
		driver.manage().window().maximize();

		// Open Application
		driver.get("http://tartu.ridango.com/");

		testSelenium testSuite = new testSelenium();

		// Test suite sequence
		//testSuite.TestCaseChangeProfile(driver, testSuite);
		//testSuite.TestCaseCardNumber(driver, testSuite);
		testSuite.TestCaseAddMoney(driver, testSuite);
		//testSuite.TestCasePersonaliseCard(driver, testSuite);
	}

	/*
	 * The scope of this function is execute the login procedure for a
	 * registered user
	 *
	 * @Param : WebDriver driver
	 *
	 */
	public void Login(WebDriver driver) throws Exception {

		//Select English Language
		WebElement selectLanguage = driver.findElement(By.xpath("/html/body/div/div/section/nav/section/div/div/ul/li[1]/a"));
		Actions builder = new Actions(driver);
		Action mouseOverselectLanguage = builder.moveToElement(selectLanguage).build();
		mouseOverselectLanguage.perform();  
		Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div/div/section/nav/section/div/div/ul/li[1]/ul/li[3]/a")).click();
	    
		// Select Log in ;
		driver.findElement(By.className("login-item")).click();
		Thread.sleep(1000);

		// Username field
		driver.findElement(By.id("username")).sendKeys("fabsic.itc@gmail.com");

		// Password field
		driver.findElement(By.id("password")).sendKeys("jerod79fa");

		// Submit user data
		driver.findElement(By.xpath("//*[@id='login-form']/div[2]/a")).submit();

		/*
		 * Check that the user authentication works correctly, the message
		 * "Login successful" have to be displayed
		 */

		// Wait for element Change profile
		WebDriverWait waitMessageLogin = new WebDriverWait(driver, 60);// wait 1
																		// minute
		waitMessageLogin.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")));
		String messageLogin = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")).getText();
		
		messageLogin = messageLogin.trim(); // Remove the blank space before and
											// after the string message

		try {
			if (!messageLogin.equals("Login succsessful")) { // Check the
																// message
				throw new Exception("Wrong message displayed at the Login procedure:" + messageLogin + "\n");// Wrong
																												// message
																												// displayed
			}
		} catch (Exception e) {

		}

		// Close Web site
		// driver.close();
	}

	/*@
	 * The scope of this function is to execute and check after the login
	 * procedure of a registered user that the change of its profile working
	 * properly
	 *
	 * @Param : WebDriver driver
	 * 
	 * @Param :testSelenium testSuite
	 */
	public void TestCaseChangeProfile(WebDriver driver, testSelenium testSuite) throws Exception {

		// Select My Tickets
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[3]/a")).click();

		// Login user
		testSuite.Login(driver);

		// Select My Tickets
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[3]/a")).click();

		// Wait for element Change profile
		WebDriverWait waitChangeProfile = new WebDriverWait(driver, 60);// wait
																		// 1
																		// minute
		waitChangeProfile.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='panel2a']/div[1]/div[8]/div[2]/a")));

		// Select Change profile
		driver.findElement(By.xpath("//*[@id='panel2a']/div[1]/div[8]/div[2]/a")).click();

		// Name field
		Thread.sleep(1000);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("fabio siciliano");

		// Birth date fields
		Select dropdownYear = new Select(driver.findElement(By.id("year")));
		dropdownYear.selectByValue("1979");
		Select dropdownMonth = new Select(driver.findElement(By.id("month")));
		dropdownMonth.selectByValue("08");
		Select dropdownDay = new Select(driver.findElement(By.id("day")));
		dropdownDay.selectByValue("21");

		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;

		// Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.name("country"));

		// now execute query which actually will scroll until that element is
		// not appeared on page.
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(By.name("country")).clear();
		driver.findElement(By.name("country")).sendKeys("ITALIA");
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys("ITALIA2");
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys("ROMA");
		driver.findElement(By.name("street1")).clear();
		driver.findElement(By.name("street1")).sendKeys("ala1");
		driver.findElement(By.name("street2")).clear();
		driver.findElement(By.name("street2")).sendKeys("ala2");
		driver.findElement(By.name("postal_code")).clear();
		driver.findElement(By.name("postal_code")).sendKeys("11111111");

		// Phone: Mobile, Phone , Submit
		driver.findElement(By.name("mobile_info")).clear();
		driver.findElement(By.name("mobile_info")).sendKeys("15198");
		driver.findElement(By.name("phone")).clear();
		driver.findElement(By.name("phone")).sendKeys("15198");
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//*[@id='main-content-container-row']/div/div[1]/div[1]/form/div/div[2]/div/input")).submit();

		/*
		 * Check that the profile it was correctly changed, the message
		 * "profile saved" have to be displayed
		 */

		// Wait for element Change profile
		WebDriverWait waitMessageChangeProfile = new WebDriverWait(driver, 60);// wait
																				// 1
																				// minute
		waitMessageChangeProfile.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")));
		String messageChangeProfile = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]"))
				.getText();
		messageChangeProfile = messageChangeProfile.trim(); // Remove the blank
															// space before and
															// after the string
															// message

		try {
			if (!messageChangeProfile.equals("Profile saved")) { // Check the
																	// message
				throw new Exception("Wrong message displayed:" + messageChangeProfile); // Wrong
																						// message
																						// displayed
			}
		} catch (Exception e) {
			// testResult.TestResult(e.getMessage()); //write message in the
			// file
		}

		// Log out
		driver.findElement(By.xpath("/html/body/div/div/section/nav/section/div/div/ul/li[3]")).click();
		Thread.sleep(1000);

		// Close Web site
		// driver.close();
	}

	/*
	 * The scope of this function is that after the login procedure of a
	 * registered user check that after to digit a wrong card number the
	 * registration wasn't executed
	 *
	 * @Param : WebDriver driver
	 * 
	 * @Param :testSelenium testSuite
	 */

	public void TestCaseCardNumber(WebDriver driver, testSelenium testSuite) throws Exception {

		// Select My Tickets
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[3]/a")).click();

		// Login
		testSuite.Login(driver);

		// Select the User logged
		driver.findElement(By.xpath("/html/body/div[1]/div/section/nav/section/div/div/ul/li[2]/a")).click();

		// Select add related travel card
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[3]/a")).click();

		// Select Add Card
		driver.findElement(By.xpath("//*[@id='main-content-container-row']/div/div[2]/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id='panel3a']/div/div/div/a")).click();
		Thread.sleep(1000);

		// Card number
		driver.findElement(By.name("related_card_number")).sendKeys("7006015104");

		// Pin code
		driver.findElement(By.name("related_card_pin_code")).sendKeys("3990");

		// Card name
		driver.findElement(By.name("related_card_name")).sendKeys("fabio Siciliano");

		// Submit
		driver.findElement(By.xpath("//*[@id='panel2a']/form/div/input")).submit();
		Thread.sleep(1000);

		/*
		 * Check that the bus cards fail, the message
		 * "Card number is incorrect length" have to be displayed
		 */
		// Wait for element Change profile
		WebDriverWait waitMessageRelatedTravelCard = new WebDriverWait(driver, 60);// wait
																					// 1
																					// minute
		waitMessageRelatedTravelCard.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")));
		String messageRelatedTravelCard = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]"))
				.getText();
		messageRelatedTravelCard = messageRelatedTravelCard.trim(); // Remove
																	// the blank
																	// space
																	// before
																	// and after
																	// the
																	// string
																	// message

		try {
			if (!messageRelatedTravelCard.equals("Card added successfull")) { // Check
																						// the
																						// message
				throw new Exception("Wrong message displayed ddd related travelcard:" + messageRelatedTravelCard);// Wrong
																													// message
																													// displayed
			}
		} catch (Exception e) {
			// testResult.TestResult(e.getMessage()); //write message in the
			// file
			System.out.println("Wrong message displayed");
		}

		// Log out
		driver.findElement(By.xpath("/html/body/div/div/section/nav/section/div/div/ul/li[3]/a")).click();
		Thread.sleep(1000);

		// Close Web site
		// driver.close();
	}

	/*
	 * The scope of this function is check that after to digit a wrong card
	 * number the procedure for add money to bus card fail
	 *
	 * @Param : WebDriver driver
	 * 
	 * @Param : testSelenium testSuite
	 */

	public void TestCaseAddMoney(WebDriver driver, testSelenium testSuite) throws Exception {

		// Login
		testSuite.Login(driver);

		// Select buy ticket
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[2]/a")).click();
		Thread.sleep(1000);

	
		//Select from available cards
		driver.findElement(By.xpath("//*[@id='s2id_rel_0_0']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='select2-results-1']")).click();
		
		
		
		// Enter card number/personal code
		//driver.findElement(By.name("card[0]")).sendKeys("700601510");

		// Add to cart 5 euro
		driver.findElement(By.xpath("//*['@id=panel0']/form/div[2]/div[3]/div/div/button")).click();
		/*
		 * Check that the add money to bus card fail, the message
		 * "Card number is incorrect length" have to be displayed
		 */

		WebDriverWait waitMessageAddMoney = new WebDriverWait(driver, 60);// wait
																			// 1
																			// minute
		waitMessageAddMoney.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")));
		String messageAddMoney = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]"))
				.getText();
		messageAddMoney = messageAddMoney.trim(); // Remove the blank space
													// before and after the
													// string message

		try {
			if (!messageAddMoney.equals("Card number is incorrect length")) { // Check
																				// the
																				// message
				throw new Exception("Wrong message displayed ddd related travelcard:" + messageAddMoney + "/n");// Wrong
																												// message
																												// displayed
			}
		} catch (Exception e) {
			// testResult.TestResult(e.getMessage()); //write message in the
			// file
		}
		
		Thread.sleep(1000);
		//Payment methods
		//  driver.findElement(By.id("//*[@id='payment_method']")).click();
		//Thread.sleep(1000);
		
		//DropDown menu bank
		Select dropdown = new Select(driver.findElement(By.id("payment_method")));
		dropdown.selectByVisibleText("SEB");;
		
		// Log out
		driver.findElement(By.xpath("/html/body/div/div/section/nav/section/div/div/ul/li[3]/a")).click();
		Thread.sleep(1000);

		// Close Web site
		// driver.close();
	}

	/*
	 * The scope of this function is check that the procedure to personalise
	 * card with wrong user data fail
	 *
	 * @Param : WebDriver driver
	 * 
	 * @Param : testSelenium testSuite
	 */

	public void TestCasePersonaliseCard(WebDriver driver, testSelenium testSuite) throws Exception {

		// Login
		testSuite.Login(driver);

		// Select Personalise
		driver.findElement(By.xpath("//*[@id='menu-bar']/div/div/ul/li[4]/a")).click();
		Thread.sleep(1000);

		// Card number
		driver.findElement(By.name("card_id")).sendKeys("123456");

		// Personal code
		driver.findElement(By.name("personal_code")).sendKeys("123456");

		// ID card number
		driver.findElement(By.name("id_card_number")).sendKeys("123456");

		// Beneficiary
		driver.findElement(By.id("cbx-send-replacement")).click();

		// Submit
		driver.findElement(By.xpath("//*[@id='panel2a']/form/div/input")).submit();

		/*
		 * Check that the personalise card fail, the message
		 * "Personalisation failed" have to be displayed
		 */

		WebDriverWait waitMessagePersonalise = new WebDriverWait(driver, 60);// wait
																				// 1
																				// minute
		waitMessagePersonalise.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]")));
		String messagePersonalise = driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div/div/div[2]"))
				.getText();
		messagePersonalise = messagePersonalise.trim(); // Remove the blank
														// space before and
														// after the string
														// message

		try {
			if (!messagePersonalise.equals("Personalisation failed")) { // Check
																		// the
																		// message
				throw new Exception("Wrong message displayed Personalise:" + messagePersonalise + "/n");// Wrong
																										// message
																										// displayed
			}
		} catch (Exception e) {
			// testResult.TestResult(e.getMessage()); //write message in the
			// file
		}
		// Close Web site
		driver.close();
	}
}