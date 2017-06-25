package pages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.Image;
import com.hp.lft.sdk.web.ImageDescription;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;
import com.hp.lft.sdk.web.Table;
import com.hp.lft.sdk.web.TableCell;
import com.hp.lft.sdk.web.TableDescription;
import com.hp.lft.sdk.web.TableRow;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.XPathDescription;

public class HomePage extends BasePage {
		
	public HomePage(Browser browser, Logger logger) {
		super(browser, logger);		
	}

	//Sign On Link
	LinkDescription link_SignOn = new LinkDescription.Builder()
	.tagName("A").innerText("SIGN-ON").build();
	
	//Sign Off Link
	LinkDescription link_SignOff = new LinkDescription.Builder()
	.tagName("A").innerText("SIGN-OFF").build();
	
	//Section Description	
	XPathDescription sectionDescrption = new XPathDescription("//tr[@class='mouseOut']");
	
	//Username Text Box
	EditFieldDescription txt_userName =  new EditFieldDescription.Builder()
	.type("text").tagName("INPUT").name("userName").build();
	
	//Password Text Box
	EditFieldDescription txt_password =  new EditFieldDescription.Builder()
	.type("password").tagName("INPUT").name("password").build();
			
	//Sign In Button	
	ImageDescription img_SignIn = new ImageDescription.Builder()
	.alt("Sign-In").type(com.hp.lft.sdk.web.ImageType.BUTTON).tagName("INPUT").build();
	
	//Specials Table
	TableDescription tbl_Specials = new TableDescription.Builder()
	.tagName("TABLE").name("WebTable").index(0).build();
		
	
	//Check whether home page is loaded or not
	public Boolean checkHomePageExists() throws GeneralLeanFtException {
		Link signOn =  browser.describe(Link.class,link_SignOn);
		Boolean objFound = signOn.exists(10);
		logger.debug("Home Page Found = " + objFound);
		return (objFound);
	}
	
	//Get the Number of Sections
	public int getSectionCount() throws GeneralLeanFtException, CloneNotSupportedException
	{	
		WebElement[] sections = browser.findChildren(WebElement.class,sectionDescrption);
		for (WebElement section : sections) {
			section.highlight();
		}
		logger.debug("No. of Sections found = " + sections.length);
		return sections.length;		
	}
	
	//Save the Specials section in an Excel file
	public Boolean saveSpecials(String excelPath) throws GeneralLeanFtException, IOException
	{
		Boolean valuesSaved=false;
		HSSFWorkbook workbook;
		HSSFSheet sheet;		
			
		//Looking for the Specials Table
		Table specials = browser.describe(Table.class, tbl_Specials);	

		//Store the Rows
		List<TableRow> rows = specials.getRows();
		
		logger.debug("Specials Section found");

		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("Specials Table");
		
		logger.debug("Rows found in specials table = " + rows.size());
		
		//Iterating through the table
		for(int rowCount=0;rowCount<rows.size();rowCount++)					
		{
			Row xlRow = sheet.createRow(rowCount);
			List<TableCell> cells = rows.get(rowCount).getCells();
			for(int colCount=0;colCount<cells.size();colCount++)
			{				
				valuesSaved=true;
				String tableValue = cells.get(colCount).getText();
				Cell xlCell = xlRow.createCell(colCount);
				xlCell.setCellValue(tableValue);
				logger.debug("Value found at (" + rowCount + "," + colCount + ") : " + tableValue);
			}
		}
		
		FileOutputStream out =  new FileOutputStream(new File(excelPath));
	    workbook.write(out);
	    workbook.close();
	    out.close();
	    logger.debug("Excel written successfully at " + excelPath);
	    return valuesSaved;
	}
	
	//Login to the Application
	public Boolean login(String username,String password) throws GeneralLeanFtException
	{		
		//Enter Credentials and Click on Sign In
		EditField usernameField = browser.describe(EditField.class, txt_userName);
		EditField passWordField = browser.describe(EditField.class, txt_password);
		Image signIn = browser.describe(Image.class, img_SignIn);
		
		logger.debug("Entering Credentials");
		
		usernameField.setValue(username);
		passWordField.setValue(password);			
		signIn.click();

		logger.debug("Logged in = " +isLoggedIn() );
		return isLoggedIn();
		
	}
	
	//Check whether Logged in or not
	public Boolean isLoggedIn() throws GeneralLeanFtException
	{		
		//Check that Sign Off Link should be present
		Link signOff = browser.describe(Link.class, link_SignOff);
		Boolean objFound = signOff.exists(10);
		logger.debug("Logged in found = " + objFound);
		return(objFound);
	}

	//Logout from the Application
	public Boolean logOut() throws GeneralLeanFtException
	{
		//Check that Sign Off Link should be present
		Link signOff = browser.describe(Link.class, link_SignOff);
		
		signOff.click();
				
		logger.debug("Sign Off Clicked");
		
		return isLoggedOut();	
	}

	//Check whether Logged in or not
	public Boolean isLoggedOut() throws GeneralLeanFtException
	{		
		//Check that Sign Off Link should be present
		Link signOn = browser.describe(Link.class, link_SignOn);
		Boolean objFound = signOn.exists(10);
		logger.debug("Logged out = " + objFound);
		return(objFound);
	}

	
}
