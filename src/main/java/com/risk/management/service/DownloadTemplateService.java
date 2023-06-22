package com.risk.management.service;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

@Service
public class DownloadTemplateService {
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	@Value("${download.template.header}")
	String HEADERS[];
	static String SHEET = "data";
	
	public ByteArrayInputStream makeTemplate() {
		
		
		try{
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		    Sheet sheet = workbook.createSheet(SHEET);

		      // Header
		      Row headerRow = sheet.createRow(0);
		      CellStyle headerStyle = workbook.createCellStyle();
		      Font font = workbook.createFont();
		      font.setColor(IndexedColors.BLACK.getIndex());
		      font.setFontName(HSSFFont.FONT_ARIAL);
		      headerStyle.setFont(font);
		      headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		      headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  



		      for (int col = 0; col < HEADERS.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(HEADERS[col]);
		        cell.setCellStyle(headerStyle);
		        
		      }

		      workbook.write(out);
		      return new ByteArrayInputStream(out.toByteArray());
	  }
		catch (Exception e) {
		      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		    }

	}
}
