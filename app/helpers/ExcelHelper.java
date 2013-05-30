package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import models.ReviewDetail;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {
	
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
		
		if(rownum==0){
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

	private static void createReviewRow(Workbook wb, Sheet sheet, int rownum,
			ReviewDetail review) {
		
		Row row = sheet.createRow(rownum); 
		createReviewCell(row, 0, review.getReviewId());
		createReviewCell(row, 1, review.getGroup());
		//createReviewCell(row, 2,"File:" + review.getFileName() + " " + review.getLine() + " " + review.getSummery() + "  \n  " +review.getDescription());
		createReviewCell(row, 2, review.getSummery() + "  \n  " +review.getDescription());
		createReviewCell(row, 3, review.getSeverity());
		createReviewCell(row, 4, review.getType());
		createReviewCell(row, 5, review.getPhase());
		createReviewCell(row, 6, review.getCreationDate().toString());
		createReviewCell(row, 7, review.getModificationDate().toString());
		createReviewCell(row, 8,"");
		createReviewCell(row, 9, review.getStatus());
		createReviewCell(row, 10,review.getComment());
		createReviewCell(row, 11,review.getReviewerId());
		createReviewCell(row, 12,review.getAssignedTo());
		createReviewCell(row, 13,review.getFileName());
		
		
		
	}

	private static void createReviewCell(Row row, int index, String content) {
		Cell cell = row.createCell(index);
		cell.setCellValue(content);
	}

	private static void createHeaderRow(Workbook wb ,Sheet sheet,int rownum) {
		CellStyle cs = wb.createCellStyle();
		Font headerfont = wb.createFont();
		headerfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cs.setFillBackgroundColor(Font.COLOR_NORMAL);
		//cs.setFillPattern((short) CellStyle.SOLID_FOREGROUND);
		cs.setFont(headerfont);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		Row row = sheet.createRow(rownum); 
	//	row.setRowStyle(cs);
		row.setHeight((short) 0x249);
	
		createHeaderCell(row, cs, 0, "Sl. No.",5);
		createHeaderCell(row, cs, 1, "Funtionality",10);
		createHeaderCell(row, cs, 2, "Description",90);
		createHeaderCell(row, cs, 3, "Severity",10);
		createHeaderCell(row, cs, 4, "Type",10);
		createHeaderCell(row, cs, 5, "Cause",10);
		createHeaderCell(row, cs, 6, "Created At",10);
		createHeaderCell(row, cs, 7, "Updated At",10);
		createHeaderCell(row, cs, 8, "Created At",10);
		createHeaderCell(row, cs, 9, "Status",10);
		createHeaderCell(row, cs, 10, "Remarks",10);
		createHeaderCell(row, cs, 11, "Initial Reviewer",10);
		createHeaderCell(row, cs, 12, "AssignedTo",10);
		createHeaderCell(row, cs, 13, "FileName",10);
		
		
	}
	
	private static void createHeaderCell(Row row, CellStyle cs,int index, String content,int widthFactor){
		
		Cell c0 = row.createCell(index);
		c0.setCellStyle(cs);
		c0.setCellValue(content);
		row.getSheet().setColumnWidth(index, 256*widthFactor);
	}

}
