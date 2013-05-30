package helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import models.ReviewDetail;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class CustomExcelHelper {

    public static File createExcel(List<ReviewDetail> reviewList) throws Exception{
	
	// create a new file
	File file = new File("workbook.xls");
	FileOutputStream out = new FileOutputStream(file);
	// create a new workbook
	Workbook wb = new HSSFWorkbook();
	// create a new sheet
	Sheet sheet = wb.createSheet();
	// declare a row object reference
	Row row = null;
	// declare a cell object reference
	Cell cell = null;

	wb.setSheetName(0, "Reviews" );
	
	
	
	
	
	int rownum=0;
	
	createTopHeaders(wb, sheet, rownum);
	
	rownum= 4;
	rownum++;

	if(rownum==5){
		createHeaderRow(wb, sheet, rownum);
		rownum++;
	}

	for(ReviewDetail review : reviewList){
	createReviewRow(wb, sheet, rownum,review);
	    rownum++;
	}

	//draw a thick black border on the row at the bottom using BLANKS
	// advance 2 rows
	rownum++;
	rownum++;

	row = sheet.createRow(rownum);

	for (short cellnum = (short) 0; cellnum < 10; cellnum++)
	{
	    cell = row.createCell(cellnum);
	}


	// write the workbook to the output stream
	// close our file (don't blow out our file handles
	wb.write(out);
	out.close();
	
	return file;
}
    
    
    
    
    private static void createTopHeaders(Workbook wb, Sheet sheet, int rownum) {
	
		Font font = wb.createFont();
	    font.setFontHeightInPoints((short)11);
	    font.setFontName("Calibri");

	   
	 CellStyle cs = wb.createCellStyle();
	cs.setAlignment(CellStyle.ALIGN_RIGHT);
	 cs.setFont(font);
	
	Row row0 = sheet.createRow(0); 
	Cell c0 = row0.createCell(0);
	c0.setCellValue("MQC Ref. No.:");
	row0.getSheet().setColumnWidth(0, 256*20);
	
	
	Cell c03 = row0.createCell(3);
	c03.setCellValue("Reviewed By:");
	Cell c05 = row0.createCell(5);
	c05.setCellValue("Rejected By:");
	
	c0.setCellStyle(cs);
	c03.setCellStyle(cs);
	c05.setCellStyle(cs);
	
	
	Row row1 = sheet.createRow(1); 
	Cell c1 = row1.createCell(0);
	c1.setCellValue("Project Name:");
	Cell c13 = row1.createCell(3);
	c13.setCellValue("Review completed on:");
	Cell c15 = row1.createCell(5);
	c15.setCellValue("Rejection Date:");
	
	c1.setCellStyle(cs);
	c13.setCellStyle(cs);
	c15.setCellStyle(cs);
	
	Row row2 = sheet.createRow(2); 
	Cell c2 = row2.createCell(0);
	c2.setCellValue("Agile Story(s) or PR/CR:");
	Cell c23 = row2.createCell(3);
	c23.setCellValue("Principle Remediation Developer:");
	Cell c25 = row2.createCell(5);
	c25.setCellValue("Final Review By:");
	
	c2.setCellStyle(cs);
	c23.setCellStyle(cs);
	c25.setCellStyle(cs);
	
	Row row3 = sheet.createRow(3); 
	Cell c3 = row3.createCell(0);
	c3.setCellValue("");
	Cell c33 = row3.createCell(3);
	c33.setCellValue("Remediation work completed on:");
	Cell c35 = row3.createCell(5);
	c35.setCellValue("completed on:");
	
	c3.setCellStyle(cs);
	c33.setCellStyle(cs);
	c35.setCellStyle(cs);
	
	Row row4 = sheet.createRow(4); 
	
	
	
	
    }




    private static void createHeaderRow(Workbook wb ,Sheet sheet,int rownum) {
	CellStyle cs = wb.createCellStyle();
	Font headerfont = wb.createFont();
	headerfont.setFontHeightInPoints((short)11);
	headerfont.setFontName("Cambria");
	headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	cs.setFillForegroundColor(IndexedColors.TAN.getIndex());
	cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	cs.setFont(headerfont);
	cs.setAlignment(CellStyle.ALIGN_LEFT);
	cs.setBorderTop(CellStyle.BORDER_THIN);
	cs.setBorderBottom(CellStyle.BORDER_THIN);
	Row row = sheet.createRow(rownum); 
	//row.setRowStyle(cs);
	
	row.setHeight((short) 0x149);

	createHeaderCell(row, cs, 0, "Category",20);
	createHeaderCell(row, cs, 1, "Ref Name",30);
	createHeaderCell(row, cs, 2, "Priority",10);
	createHeaderCell(row, cs, 3, "Status",10);
	createHeaderCell(row, cs, 4, "Importance",10);
	createHeaderCell(row, cs, 5, "Short Explanation",60);
	createHeaderCell(row, cs, 6, "Functional Area",20);
	createHeaderCell(row, cs, 7, "File Name",30);
	createHeaderCell(row, cs, 8, "Risk of Change",20);
	createHeaderCell(row, cs, 9, "Cross Impact Risk",20);
	createHeaderCell(row, cs, 10, "Objectives",30);
	createHeaderCell(row, cs, 11, "Steps To Reproduce",20);
	createHeaderCell(row, cs, 12, "Attachments",20);
	createHeaderCell(row, cs, 13, "Applies To",20);
	createHeaderCell(row, cs, 14, "Findings details or Requirements Details or Standards Guidelines Reference",120);
	createHeaderCell(row, cs, 15, "Reviewer",20);
	createHeaderCell(row, cs, 16, "Date",20);
	
}  
    

private static void createReviewRow(Workbook wb, Sheet sheet, int rownum,
		ReviewDetail review) {
	
	Row row = sheet.createRow(rownum); 
	createReviewCell(row, 0, "Static Code Analysis");
	createReviewCell(row, 1, "General Good Coding Practice");
	createReviewCell(row, 2, review.getSeverity());
	createReviewCell(row, 3, review.getStatus());
	createReviewCell(row, 4, "Required");
	createReviewCell(row, 5, review.getSummery());
	createReviewCell(row, 6, review.getGroup());
	createReviewCell(row, 7, review.getFileName());
	createReviewCell(row, 8, "Small");
	createReviewCell(row, 9, "Small");
	createReviewCell(row, 10,"Clean Code, Robust code");
	createReviewCell(row, 11,"");
	createReviewCell(row, 12,"");
	createReviewCell(row, 13,"Java");
	createReviewCell(row, 14,review.getSummery() + " \n " + review.getDescription());
	createReviewCell(row, 15,review.getReviewerId());
	createReviewCell(row, 16,review.getCreationDate().toString());
	
	
	
	
}

private static void createReviewCell(Row row, int index, String content) {
	Cell cell = row.createCell(index);
	cell.setCellValue(content);
}



private static void createHeaderCell(Row row, CellStyle cs,int index, String content,int widthFactor){
	
	Cell c0 = row.createCell(index);
	c0.setCellStyle(cs);
	c0.setCellValue(content);
	row.getSheet().setColumnWidth(index, 256*widthFactor);
}

}
