package executionPack;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.demo.utility.ListenerClass;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;

public class TestClass_Updated 
{
	
	//static WritableWorkbook wwbCopy;
	
	private static XSSFSheet sheet;
	 
	private static XSSFWorkbook w;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	static int j;
	static String fileLocation="TestData\\XmlSuite.xlsx";
	static FileOutputStream fileOut;
	static FileInputStream fs;
	static String sheetName;
	static XmlSuite suite;
	static XmlTest test;
	static ArrayList<XmlClass> classes;
	static String suiteName;
	static String testName;
	static String packageName;
	static String className;
	static String scriptName;
	static Collection<XmlInclude> colScript;
	static XmlInclude xmlIn;
	static String classNameWithPackage;
	static XmlClass xmlClass ;
	public static void main(String args[]) throws Exception
	{
		//String fileLocation="XmlSuite.xls";
	
		sheetName = "Regression";
		fs  = new FileInputStream(fileLocation);
		
		xmlIn=new  XmlInclude();
		colScript= new ArrayList<XmlInclude>();
		
		w = new XSSFWorkbook(fs);
		//wwbCopy = Workbook.createWorkbook(inputWorkbook, w);
		// Get the first sheet
		 sheet = w.getSheet(sheetName);
		for ( j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
		{
			Cell = sheet.getRow(j).getCell(1);
			 
  			String runMode = Cell.getStringCellValue();
			
			if(runMode.equalsIgnoreCase("Yes"))
			{
				ListenerClass.row=j;
				// Read suite name from excel sheet
				
				Cell = sheet.getRow(j).getCell(2);
				 suiteName = Cell.getStringCellValue();
	  			
				
				Cell = sheet.getRow(j).getCell(3);
				 testName = Cell.getStringCellValue();
				
				
				Cell = sheet.getRow(j).getCell(4);
				 packageName = Cell.getStringCellValue();
								
				
				Cell = sheet.getRow(j).getCell(5);
				 className = Cell.getStringCellValue();
				
				Cell = sheet.getRow(j).getCell(6);
				 scriptName = Cell.getStringCellValue();
				//colScript= new ArrayList<XmlInclude>();
				 
				xmlIn.setName(scriptName);
			//	xmlIn=new  XmlInclude();
				
				colScript.add(new XmlInclude(scriptName));
				System.out.println(colScript.size());
				
			}//End of if block
		}//end of for loop
		
		
		suite = new XmlSuite(); 
		suite.setName(suiteName);
		 test = new XmlTest(suite);
		test.setName(testName);
		
		classes = new ArrayList<XmlClass>();
		 classNameWithPackage=packageName+"."+className;
		 xmlClass = new XmlClass(classNameWithPackage, false);
		xmlClass.getIncludedMethods().addAll(colScript);
		classes.add(xmlClass);
		test.setXmlClasses(classes) ;
		
		
		
		ArrayList<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();

		tng.setXmlSuites(suites);
		
		try
		{
			tng.run(); 
		}
		catch(Exception e)
		{
			System.out.println(testName + "failed");
		}
		
	}// end of main method
//	public static void setValueIntoCell(int iRowNumber,String result) throws Exception
//    {
//       Row = sheet.getRow(iRowNumber);
//      Cell=  Row.getCell(6,Row.RETURN_BLANK_AS_NULL);
//                
//
//		if (Cell == null) {
//
//			Cell = Row.createCell(6);
//
//			Cell.setCellValue(result);
//
//			} else {
//
//				Cell.setCellValue(result);
//
//			}
//		FileOutputStream fileOut = new FileOutputStream(fileLocation);
//
//		w.write(fileOut);
//		
//    }
//    
    public static void closeFile()
    {
        try {
            // Closing the writable work book
        	fileOut.flush();
			fileOut.close();

            // Closing the original work book
            w.close();
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

}
