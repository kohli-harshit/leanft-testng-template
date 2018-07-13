package test;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.CSVAnnotation.CSVFileParameters;
import utils.GenericDataProvider;
import utils.PropertiesManager;

public class HomePageTest extends BaseTest{
		
	//Navigate to Home Page Before each Test
	@BeforeMethod(alwaysRun=true)
	public void navigateToHomePage() throws Exception
	{	
		browser.navigate(PropertiesManager.getProperty("websiteURL"));
		logger.debug("Home Page Navigated");
	}
	
	@Test(priority=0,groups={"Smoke","Regression"},dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	@CSVFileParameters(path = "src\\test\\java\\test-data\\LoginTestData.csv", delimiter = "###")
	public void testLogin(int rowNo, Map<String, String> inputDataMap)	
	{
		try
		{
			HomePage HP = new HomePage(browser);
			HP.login(inputDataMap.get("username"),inputDataMap.get("password"));
			Assert.assertTrue(HP.isLoggedIn(), "Login should be successfull");
			HP.logOut();
		}
		catch(Exception ex)
		{
			logger.error(ex);
			Assert.fail(ex.getMessage());
		}		
	}
	
	//Check Home Page Sections
	@Test(priority=0,groups={"Smoke","Regression"})
	public void checkHomePageSections() throws Exception
	{		
		try
		{
			//Declare Page Object
			HomePage HP = new HomePage(browser);

			//Check that the Home Page is present
			Assert.assertTrue(HP.checkHomePageExists());

			//Find the Number of Side Sections in the Home Page
			Assert.assertTrue(HP.getSectionCount()>=Integer.parseInt(PropertiesManager.getProperty("no_of_sections")));
		}
		catch(Exception ex)
		{
			logger.error(ex);
			Assert.fail(ex.getMessage());
		}
	}
	
	//Check Specials section
	@Test(priority=1,groups={"Regression"})
	public void checkSpecials() throws Exception
	{		
		try
		{
			//Declare Page Object
			HomePage HP = new HomePage(browser);

			//Check that the Specials Section is saved inside an Excel File
			Assert.assertTrue(HP.saveSpecials(PropertiesManager.getProperty("excelPath")));

		}
		catch(Exception ex)
		{
			logger.error(ex);
			Assert.fail(ex.getMessage());
		}
	}
}
