package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic_Component.Base_class;
import PageObject_Component.PageObject_Search;

public class Scenario_Search extends Base_class {
	
	static Logger log= Logger.getLogger(Scenario_Search.class);
	SoftAssert SAssert= new SoftAssert();
	
	@Test(dataProvider="dp_InvalidSearch", dataProviderClass=DataProvider_Component.Dataprovider_Search.class,groups={"smoke"})
	public void testInvalidSearch(Map Search) throws IOException, InterruptedException
	{
		
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString();
		
		log.info("Executing the TC_ID  " +TC_ID+ " order is "+Order);
		
		Start_Server();
		InitializeApp();
		
		PageObject_Search BS_Pob= new PageObject_Search(driver);
		
		Explicit_wait(BS_Pob.Search_btn, 20);
		BS_Pob.Click_Searchbtn();
		
		Explicit_wait(BS_Pob.Search_txtbox, 20);
		BS_Pob.Enter_Searchtxt(Search_item);
		
		
		Explicit_wait(BS_Pob.Invalid_msg, 20);
		String Actual_Result = BS_Pob.getInvalidmsg();
		
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			Capture_screenshot(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			SAssert.fail("Failed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			Capture_screenshot(TC_ID, Order);
		}
		
		Stop_Server();
		
		SAssert.assertAll();
		
		
		
	}
	

	@Test(dataProvider="dp_ValidSearch",dataProviderClass=DataProvider_Component.Dataprovider_Search.class,groups={"regression"})
	public void testValidSearch(Map Search) throws IOException, InterruptedException
	{
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString().replace(".0", "");
		
		log.info("Executing the TC_ID  " +TC_ID+ " order is "+Order);
		
		Start_Server();
		InitializeApp();
		
		PageObject_Search BS_Pob= new PageObject_Search(driver);
		
		Explicit_wait(BS_Pob.Search_btn, 20);
		BS_Pob.Click_Searchbtn();
		
		Explicit_wait(BS_Pob.Search_txtbox, 20);
		BS_Pob.Enter_Searchtxt(Search_item);
		
		
		Explicit_wait(BS_Pob.Valid_msg, 20);
		String Output = BS_Pob.getValidmsg();
		
		String Actual_Result = Output.replace(" products","");
		
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			Capture_screenshot(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			Capture_screenshot(TC_ID, Order);
			SAssert.fail("Failed as Actual Result is  " +Actual_Result+ "  Expected Result is " +Exp_Result);
			
		}
		
		Stop_Server();
		
		SAssert.assertAll();
		
		
		
		
		
		
		
	}
}
