package pages;

import org.apache.log4j.Logger;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.*;

public class FlightDetails extends BasePage{
	
	//Parameterized Constructor
	public FlightDetails(Browser browser,Logger logger)
	{
		super(browser,logger);
	}	
	
	//Flight Finder Image
	ImageDescription img_FlightFinder =  new ImageDescription.Builder()
	.src(new RegExpProperty(".*mast_flightfinder.*")).type(com.hp.lft.sdk.web.ImageType.NORMAL).tagName("IMG").index(0).build();
	
	//Flight Type Radio Group
	RadioGroupDescription rg_TripType = new RadioGroupDescription.Builder()
	.tagName("INPUT").name("tripType").build();
	
	//Departure List Box
	ListBoxDescription lst_Departure = new ListBoxDescription.Builder()
	.tagName("SELECT").name("fromPort").build();
	
	//Arrival List Box
	ListBoxDescription lst_Arrival = new ListBoxDescription.Builder()
	.tagName("SELECT").name("toPort").build();
	
	//Continue Image
	ImageDescription img_Continue = new ImageDescription.Builder()
	.type(com.hp.lft.sdk.web.ImageType.BUTTON).tagName("INPUT").name("findFlights").build();
	
	//Check whether Flight Details Page is appearing or not
	public Boolean isFlightLoaded() throws GeneralLeanFtException
	{		
		//Check that Flight finder image should be present
		Image flightFinder = browser.describe(Image.class,img_FlightFinder);		
		return(flightFinder.exists(10));
	}
	
	
	//Select the Correct Flight Type
	public void selectFlightType(String flightType) throws GeneralLeanFtException
	{
		switch(flightType.toUpperCase())
		{
		case "ROUNDTRIP":
			browser.describe(RadioGroup.class,rg_TripType).select(0);
			break;
		case "ONEWAY":
			browser.describe(RadioGroup.class,rg_TripType).select(1);
			break;
		default:
			browser.describe(RadioGroup.class,rg_TripType).select(1);
		}
	}
	
	//Select Departure
	public void selectDeparture(String from) throws GeneralLeanFtException
	{		
		ListBox ListDeparture = browser.describe(ListBox.class,lst_Departure);		
		ListDeparture.select(from);		
	}

	//Select Arrival
	public void selectArrival(String to) throws GeneralLeanFtException
	{		
		ListBox ListArrival = browser.describe(ListBox.class,lst_Arrival);		
		ListArrival.select(to);			
	}
	
	//Press Continue Button
	public void pressContinue() throws GeneralLeanFtException
	{
		browser.describe(Image.class,img_Continue).click();
	}
		
	
}
