package pages;

import org.apache.log4j.Logger;

import com.hp.lft.sdk.web.Browser;

public class BasePage {

	//The Browser instance that will be acted upon
	protected Browser browser;
	protected Logger logger;
	
	//Parameterized Constructor
	public BasePage(Browser browser,Logger logger)
	{
		this.browser = browser;
		this.logger = logger;
	}
}
