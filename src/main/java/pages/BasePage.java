package pages;

import org.apache.log4j.Logger;

import com.hp.lft.sdk.web.Browser;

public class BasePage {

	//The Browser instance that will be acted upon
	protected Browser browser;
	
	//Parameterized Constructor
	public BasePage(Browser browser)
	{
		this.browser = browser;
	}
}
