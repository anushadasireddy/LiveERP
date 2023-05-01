package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	
	String data;
	//constructor for reading  xl file

	 public ExcelFileUtil(String Excelpath) throws Throwable{
		FileInputStream fi= new FileInputStream(Excelpath);
		wb=WorkbookFactory.create(fi);
	}
	//get rowcount method	
	public int RowCount(String sheetname ) {
		return wb.getSheet(sheetname).getLastRowNum();
		
	}
	//get celldata method
	public String getCellData(String sheetname, int row,int column)throws Throwable {

		data="";
	//System.out.println(sheetname+ " ;"+row+" : "+column);
	//System.out.println(wb.getSheet(sheetname).getRow(row).getCell(column));
		//if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC){
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {	
	int d =(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(d);

		}
		else {
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}

		return data;

	}

	//Set data into aCell
	public void setCellData(String sheetname, int row,int column,String status,String writeExcel) throws Throwable {
		//get sheet from workboook
		Sheet ws=wb.getSheet(sheetname);
		//get row from sheet
		Row rownum=ws.getRow(row);
		//create cell in a row
		Cell cell=rownum.createCell(column);
		//write status
		cell.setCellValue(status);

		if (status.equalsIgnoreCase("pass")) {
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//set color
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//set color
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked")){
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//set color
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rownum.getCell(column).setCellStyle(style);	
			}
		 
		FileOutputStream fo=new FileOutputStream(writeExcel);
		wb.write(fo);
	}
	




	/*public static void main(String[] args) throws Throwable{

		ExcelFileUtil xl=new ExcelFileUtil("C:\\ANUSHA\\LiveProject\\Empdata10.xlsx");
		//calling getrowcount
		int rc=xl.RowCount("Empdata");
		System.out.println("rowcount::"+rc);


		//calling getCellData
		for(int i=1;i<=rc;i++) {
			String Fname=xl.getCellData("Empdata", i, 0);
			String Mname=xl.getCellData("Empdata", i, 1);
			String Lname=xl.getCellData("Empdata", i, 2);
			String EID=xl.getCellData("Empdata", i, 3);

			System.out.println(Fname+"  "+Mname+"  "+Lname+"  "+EID);
			//set cell value
			//xl.setCellData("Empdata", i, 4, "pass", "C:\\ANUSHA\\LiveProject\\Result10.xlsx");
			xl.setCellData("Empdata", i, 4, "fail", "C:\\ANUSHA\\LiveProject\\Result100.xlsx");
			//xl.setCellData("Empdata", i, 4, "Blocked", "C:\\ANUSHA\\LiveProject\\Result10.xlsx");
		}
	}
	*/	
}

