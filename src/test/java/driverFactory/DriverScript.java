package driverFactory;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary{
String inputpath="./FileInput/DataEngine.xlsx";
String outputpath="./FileOutput/result.xlsx";
public void startTest() throws Throwable {
	String Module_status="";
	//calling xl file util methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	
	//iterate all rows in mastertestcase sheet
	for(int i=1;i<=xl.RowCount("MasterTestCases");i++) { 
	
		if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("y")) {
			//storing corresponding sheet into a variable
			String TcModule=xl.getCellData("MasterTestCases", i, 1);
			//
			//iterate all rows in Application login sheet
			for(int j=1;j<=xl.RowCount(TcModule);j++) {
				//call all celldata
				String Description=xl.getCellData(TcModule, j, 0);
				String ObjectType=xl.getCellData(TcModule, j, 1);
				String LocatorType=xl.getCellData(TcModule, j, 2);
				String LocatorValue=xl.getCellData(TcModule, j, 3);
				String TestData=xl.getCellData(TcModule, j, 4);
				
				try {
					if(ObjectType.equalsIgnoreCase("startBrowser")) {
						driver=FunctionLibrary.startBrowser();
					}
					else if(ObjectType.equalsIgnoreCase("openUrl")) {
						FunctionLibrary.openUrl(driver);
					}
					else if(ObjectType.equalsIgnoreCase("waitForElemen")) {
						FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("typeAction")){
						FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("clickAction")) {
						FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
					}
					else if(ObjectType.equalsIgnoreCase("validateTitle")) {
						FunctionLibrary.validateTitle(driver, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("closeBrowser")) {
						FunctionLibrary.closeBrowser(driver);
					}
					//write pass in status cell TCModule
					xl.setCellData(TcModule, j, 5, "pass", outputpath);
					Module_status="true";
					
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					xl.setCellData(TcModule, j, 5, "fail", outputpath);
					Module_status="False";
				}
				if(Module_status.equalsIgnoreCase("True")) {
					xl.setCellData("MasterTestCases", i, 3, "pass", outputpath);
				}
				else {
					xl.setCellData("MasterTestCases", i, 3, "fail", outputpath);
				}
				
			}
			
			
		}
		else {
			// write blocked when N is flaged
			xl.setCellData("MasterTestCases", i, 3, "Blocked", outputpath);
		}
		

	}
	
	
	
	
	
	
}
}
