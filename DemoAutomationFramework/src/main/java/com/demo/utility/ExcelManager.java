package com.demo.utility;



import java.io.*;

import java.util.*;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
public class ExcelManager {

    static XSSFWorkbook wbook;
    static XSSFSheet sheet;
    static File file;
    static XSSFRow row;
    static XSSFCell cell;

    static int rowNum;


    public static void readExcelFile(String workBook,String sheetName) {
        String filePath="TestData\\"+workBook;
        
       // String filePath=System.getProperty("user.dir")+"\\src\\main\\resources\\Data\\"+workBook;
        file=new File(filePath);
        FileInputStream fin= null;
        try {
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wbook = new XSSFWorkbook(fin); // excel sheet
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet=wbook.getSheet(sheetName); // sheet name
    }


    public static int getHeaderColNumber(String header)
    {
        int colNumber=0;

        int firstRow = sheet.getFirstRowNum();
        Iterator<Cell> it =sheet.getRow(firstRow).cellIterator();
        while(it.hasNext())
        {
            colNumber++;
            String actualHeader=it.next().getStringCellValue();
            if(actualHeader.equals(header))
                break;
            //System.out.println(it.next());
        }
        return colNumber;
    }

    public static int getRowNumber(String wBook,String sheetName,String condition) throws IOException {
        readExcelFile(wBook,sheetName);
        String actualValue;
        String conditions[]=condition.split("=");
        String header=conditions[0];
        int headerColNum = getHeaderColNumber(header);
        String value=conditions[1];

        int totalRows=sheet.getPhysicalNumberOfRows();
        int firstRow = sheet.getFirstRowNum();
        int rowNumber=firstRow;
        for(int i=firstRow+1;i<totalRows;i++)
        {
            rowNumber++;
            cell =sheet.getRow(i).getCell(headerColNum-1);

            CellType cT=cell.getCellType();
            if(cT.equals(CellType.STRING))
            {
                actualValue=cell.getStringCellValue();
            }
            else
            {
                actualValue =cell.getNumericCellValue()+"";
            }
            if(actualValue.equals(value))
                break;
        }



        return rowNumber;
    }

    public static int getRowNumber(String wBook,String sheetName,String condition1,String condition2) throws IOException {
        readExcelFile(wBook,sheetName);
        String actualValue;

        String conditions[]=condition1.split("=");
        String header1=conditions[0];
        String value1=conditions[1];
        int  headerColNum1 = getHeaderColNumber(header1);
        String conditions1[]=condition2.split("=");
        String header2=conditions1[0];
        int  headerColNum2 = getHeaderColNumber(header2);
        String value2=conditions1[1];


        int totalRows=sheet.getPhysicalNumberOfRows();
        int firstRow = sheet.getFirstRowNum();
        int rowNumber=0;
        int rowNumberWithCon1=firstRow;
        int rowNumberWithCon2=firstRow;
        for(int i=firstRow+1;i<totalRows;i++)
        {
            rowNumberWithCon2=0;
            cell =sheet.getRow(i).getCell(headerColNum1-1);

            CellType cT=cell.getCellType();
            if(cT.equals(CellType.STRING))
            {
                actualValue=cell.getStringCellValue();
            }
            else
            {
                actualValue =cell.getNumericCellValue()+"";
            }
            if(actualValue.equals(value1))
                rowNumberWithCon1++;
            for(int is=firstRow+1;is<totalRows;is++)
            {
                rowNumberWithCon2++;
                cell =sheet.getRow(is).getCell(headerColNum2-1);

                CellType cTs=cell.getCellType();
                if(cTs.equals(CellType.STRING))
                {
                    actualValue=cell.getStringCellValue();
                }
                else
                {
                    actualValue =cell.getNumericCellValue()+"";
                }
                if(actualValue.equals(value2))
                {

                    if(rowNumberWithCon1==rowNumberWithCon2)
                    {
                        rowNumber=rowNumberWithCon1;
                        break;
                    }
                }

            }

        }




        return rowNumber;
    }

    public static HashMap<String,String> getExcelData(int rowNum)
    {
        int colNumber=0;
        String actualValue=null;
        HashMap<String, String> hm = new  HashMap<String, String>();
        int firstRow = sheet.getFirstRowNum();
        Iterator<Cell> it =sheet.getRow(firstRow).cellIterator();
        while(it.hasNext())
        {
            colNumber++;
            String actualHeader=it.next().getStringCellValue();
            cell =sheet.getRow(rowNum).getCell(colNumber-1);
            CellType cT=cell.getCellType();
            if(cT.equals(CellType.STRING))
            {
                actualValue=cell.getStringCellValue();
            }
            else
            {
                actualValue =cell.getNumericCellValue()+"";
            }
            hm.put(actualHeader, actualValue);
        }


        return hm;
    }


    public static List<Map<String,String>> getExcelRowsdata(String wBook,String sheetName,String condition)  {
        readExcelFile(wBook,sheetName);
        List<Map<String, String>> map = new ArrayList<Map<String, String>>();
        HashMap<String, String> map1;
        ArrayList<String> HeaderValue = new ArrayList<String>();
        ArrayList<String> RowValues1 = new ArrayList<String>();
        String actualValue="";
        String conditions[]=condition.split("=");
        String header=conditions[0];
        int headerColNum = getHeaderColNumber(header);
        String value=conditions[1];

        int totalRows=sheet.getPhysicalNumberOfRows();
        int firstRow = sheet.getFirstRowNum();
        int rowNumber=firstRow;
        int count=-1;
        /// add all headers
        int colNumber=0;
        int fRow = sheet.getFirstRowNum();
        Iterator<Cell> it =sheet.getRow(fRow).cellIterator();
        while(it.hasNext())
        {
            colNumber++;
            String actualHeader=it.next().getStringCellValue();
            HeaderValue.add(actualHeader);
            //System.out.println(it.next());
        }

        for(int i=firstRow+1;i<totalRows;i++)
        {
            rowNumber++;
            cell =sheet.getRow(i).getCell(headerColNum-1);
            
            	
            CellType cT=cell.getCellType();
            if(cT.equals(CellType.STRING))
            {
                actualValue=cell.getStringCellValue();
            }
            else
            {
                actualValue =cell.getNumericCellValue()+"";
            }
            if(actualValue.equals(value))
            {
                int colNumber1=0;
                String actualValue1=null;
                HashMap<String, String> hm = new  HashMap<String, String>();
                int firstRow1 = sheet.getFirstRowNum();
                Iterator<Cell> it1 =sheet.getRow(firstRow1).cellIterator();
                int col=-1;
                while(it1.hasNext())
                {
                    col++;
                    colNumber1++;
                    String actualHeader=it1.next().getStringCellValue();
                    cell =sheet.getRow(rowNumber).getCell(colNumber1-1);
                    CellType cT1=cell.getCellType();
                    if(cT1.equals(CellType.STRING))
                    {
                        actualValue1=cell.getStringCellValue();
                    }
                    else
                    {
                        actualValue1 =cell.getNumericCellValue()+"";
                    }
                    RowValues1.add(col, actualValue1);
                }
                
                  
                }
                count++;
            

            if(!RowValues1.isEmpty())
            {
                map1 = new HashMap<String, String>();
                for(int k =0;k<HeaderValue.size();k++)
                {
                    map1.put(HeaderValue.get(k).toString(), RowValues1.get(k).toString());
                }
                map.add(map1);
                RowValues1.clear();
            }
        }

        return map;
    }
}

