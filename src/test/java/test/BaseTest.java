package test;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.SDKConfigurationFactory;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;

import utils.PropertiesManager;

public class BaseTest {
	
	//Browser instance on which tests would be run
	protected Browser browser;
	private BrowserType bType;
	
	static final Logger logger = Logger.getLogger(BaseTest.class);
	
	@BeforeSuite(alwaysRun=true)
	public void Start_Engine() throws Exception
	{							
		PropertiesManager.initializeProperties();
		
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		
		ModifiableSDKConfiguration config;
		config =  SDKConfigurationFactory.loadConfigurationFromExternalPropertiesFile("src\\main\\resources\\leanft.properties");
	    SDK.init(config);		   
	    Reporter.init();
	    
	    logger.info("SDK Initialized");
	    
	    FileInputStream iStr = new FileInputStream("src\\main\\resources\\leanft.properties");
	    Properties prop = new Properties();
	    prop.load(iStr);
	    
	    switch(prop.getProperty("BrowserType"))
	    {
	    	case "INTERNET_EXPLORER":
	    		bType = BrowserType.INTERNET_EXPLORER;
	    		break;
	    	case "FIREFOX":
	    		bType = BrowserType.FIREFOX;
	    		break;
	    	case "CHROME":
	    		bType= BrowserType.CHROME;
	    		break;
	    	default:
	    		bType = BrowserType.FIREFOX;
	    }
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchBrowser() throws Exception
	{		    
	    browser = BrowserFactory.launch(bType);
	    logger.info("Browser Launched");
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws Exception
	{		
		browser.close();
	}
	
	//Close Browser, Generate Report and Cleanup SDK
	@AfterSuite(alwaysRun=true)
	public static void tearDown() throws Exception
	{			
		Reporter.generateReport();		
        SDK.cleanup();		
	}
}
