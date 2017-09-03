package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* *
 * Created by sampathmahavithana on 12/01/2017.
 */
public class ExcelHelper {

    private XSSFSheet ExcelWSheet;

    private XSSFWorkbook ExcelWBook;

    private XSSFCell Cell;

    private XSSFRow Row;


    //public String File_Path = "./PostgresVerify.xlsx";
    public String File_TestData = "/Users/sampathmahavithana/MyCrawler/booking.xlsx";

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

    public void setExcelFile(String File_Path) throws Exception {

        try {

            // Open the Excel file

            FileInputStream ExcelFile = new FileInputStream(File_TestData);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheetAt(0);

        } catch (Exception e) {

            throw (e);

        }

    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public String getCellData(String File_Path,int RowNum, int ColNum) throws Exception {

        try {
            setExcelFile(File_Path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String CellData = "";
        try {

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            CellData = Cell.getStringCellValue();


        } catch (Exception e) {

            e.printStackTrace();

        }
        return CellData;
    }


    //This method is to write in the Excel cell, Row num and Col num are the parameters

    public void setCellData(String Result,  int RowNum, int ColNum) throws Exception {

        try {
            setExcelFile(File_TestData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{

            Row  = ExcelWSheet.getRow(RowNum);

            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

            if (Cell == null) {

                Cell = Row.createCell(ColNum);

                Cell.setCellValue(Result);

            } else {

                Cell.setCellValue(Result);

            }

            // Constant variables Test Data path and Test Data file name

            FileOutputStream fileOut = new FileOutputStream(File_TestData);

            ExcelWBook.write(fileOut);

            fileOut.flush();

            fileOut.close();

        }catch(Exception e){

            throw (e);

        }

    }
}
