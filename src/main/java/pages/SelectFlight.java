package pages;

import java.util.List;

import org.apache.log4j.Logger;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.Image;
import com.hp.lft.sdk.web.ImageDescription;
import com.hp.lft.sdk.web.RadioGroup;
import com.hp.lft.sdk.web.RadioGroupDescription;
import com.hp.lft.sdk.web.Table;
import com.hp.lft.sdk.web.TableCell;
import com.hp.lft.sdk.web.TableDescription;
import com.hp.lft.sdk.web.TableRow;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;

public class SelectFlight extends BasePage {
	
	//Parameterized Constructor
	public SelectFlight(Browser browser,Logger logger)
	{
		super(browser,logger);
	}
	
	//The Select Flight Image
	ImageDescription img_SelectFlight =  new ImageDescription.Builder()
	.src(new RegExpProperty(".*mast_selectflight.*")).type(com.hp.lft.sdk.web.ImageType.NORMAL).tagName("IMG").build();
	
	//Departure Table
	TableDescription tbl_Depart =  new TableDescription.Builder()
	.tagName("TABLE").innerText(new RegExpProperty("DEPART .*")).index(0).build();
	
	//Departure Radio Button
	RadioGroupDescription rg_Departure =  new RadioGroupDescription.Builder()
	.tagName("INPUT").name("outFlight").build();
	
	//Return Table
	TableDescription tbl_Return =  new TableDescription.Builder()
	.tagName("TABLE").innerText(new RegExpProperty("RETURN .*")).index(0).build();
	
	//Return Radio Button
	RadioGroupDescription rg_Return =  new RadioGroupDescription.Builder()
	.tagName("INPUT").name("inFlight").build();
	
	//Continue Image
	ImageDescription img_Continue = new ImageDescription.Builder()
	.type(com.hp.lft.sdk.web.ImageType.BUTTON).tagName("INPUT").name("reserveFlights").build();

	//Check whether Select Flight Page is appearing or not
	public Boolean isFlightSelectLoaded() throws GeneralLeanFtException
	{		
		//Check that Flight finder image should be present
		Image selectFlight = browser.describe(Image.class,img_SelectFlight);		
		return(selectFlight.exists(10));
	}
		
		
	//Select the Departure Airline
	public Boolean selectDepartAirline(String airlineName) throws GeneralLeanFtException, CloneNotSupportedException
	{		
		//Departure Table
		Table departure = browser.describe(Table.class, tbl_Depart);

		//Store the Rows
		List<TableRow> rows = departure.getRows();

		//Iterating through the table to know the Hierarchy
		for(int rowCount=0;rowCount<rows.size();rowCount++)					
		{
			List<TableCell> cells = rows.get(rowCount).getCells();
			System.out.println("No. of Cells in Row " + (rowCount+1) + " = " + cells.size());
			for(int colCount=0;colCount<cells.size();colCount++)
			{					
				System.out.println("Value found at (" + rowCount + "," + colCount + ") : " + cells.get(colCount).getText());
				//This is how we can find the exact value in the cell
				//if(rows.get(rowCount).getCells().get(1).getText().indexOf(airlineName)>=0)
				//{
				//	rows.get(rowCount).getCells().get(0).findChildren(
				//}
			}
		}
		
		//This is how we can Get a Row with a specific text
		//TableRow rowToLookFor = departure.findRowWithCellText(airlineName);

		WebElement[] airlines = departure.findChildren(WebElement.class, new WebElementDescription.Builder()
		.tagName("B").innerText(new RegExpProperty(".*Airlines.*")).build());
					
		RadioGroup rg_Dep = browser.describe(RadioGroup.class,rg_Departure);
						
		Boolean airLineFound=false;
		for(int airLineCount = 0; airLineCount<=airlines.length-1; airLineCount++)
		{
			String airLineName_actual = airlines[airLineCount].getInnerText();
			if(airLineName_actual.contains(airlineName))
			{
				airLineFound=true;
				rg_Dep.select(airLineCount);
				break;
			}
		}
				
		return airLineFound;
	}

	//Select the Return Airline
	public Boolean selectReturnAirline(String airlineName) throws GeneralLeanFtException, CloneNotSupportedException
	{		
		//Return Table
		Table returnTable = browser.describe(Table.class, tbl_Return);

		//Store the Rows
		List<TableRow> rows = returnTable.getRows();

		//Iterating through the table to know the Hierarchy
		for(int rowCount=0;rowCount<rows.size();rowCount++)					
		{
			List<TableCell> cells = rows.get(rowCount).getCells();
			System.out.println("No. of Cells in Row " + (rowCount+1) + " = " + cells.size());
			for(int colCount=0;colCount<cells.size();colCount++)
			{					
				System.out.println("Value found at (" + rowCount + "," + colCount + ") : " + cells.get(colCount).getText());
				//This is how we can find the exact value in the cell
				//if(rows.get(rowCount).getCells().get(1).getText().indexOf(airlineName)>=0)
				//{
				//	rows.get(rowCount).getCells().get(0).findChildren(
				//}
			}
		}

		//This is how we can Get a Row with a specific text
		//TableRow rowToLookFor = returnTable.findRowWithCellText(airlineName);

		WebElement[] airlines = returnTable.findChildren(WebElement.class, new WebElementDescription.Builder()
		.tagName("B").innerText(new RegExpProperty(".*Airlines.*")).build());

		RadioGroup rg_Arr = browser.describe(RadioGroup.class,rg_Return);

		Boolean airLineFound=false;
		for(int airLineCount = 0; airLineCount<=airlines.length-1; airLineCount++)
		{
			String airLineName_actual = airlines[airLineCount].getInnerText();
			if(airLineName_actual.contains(airlineName))
			{
				airLineFound=true;
				rg_Arr.select(airLineCount);
				break;
			}
		}

		return airLineFound;
	}

	
	//Press Continue Button
	public void pressContinue() throws GeneralLeanFtException
	{
		browser.describe(Image.class,img_Continue).click();
	}
		
	
}
