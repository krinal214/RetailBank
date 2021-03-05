package com.utils;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.Transaction;
 
/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */
public class DownloadFile {
 
    public static XSSFWorkbook getExcel(List<Transaction> transactions) throws IOException {
		
			XSSFWorkbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Countries");
		
		
		int rowIndex = 0;
		Row row = sheet.createRow(rowIndex++);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("Transaction Id");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Transaction Type");
	   Cell cell2=row.createCell(2);
	   cell2.setCellValue("Transaction Amount");
	   Cell cell3=row.createCell(3);
	   cell3.setCellValue("Transaction Time");
	   
	  for(Transaction transaction:transactions) 
	  {
		   row = sheet.createRow(rowIndex++);
			cell0 = row.createCell(0);
			cell0.setCellValue(transaction.getTrxnId()+"");
			cell1 = row.createCell(1);
			char type=transaction.getType();
			String msg=null;
			if(type=='W')msg="Withdraw";
			else if(type=='D')msg="Deposit";
			else msg="Transfer";
			cell1.setCellValue(msg);
		   cell2=row.createCell(2);
		   cell2.setCellValue(transaction.getAmount()+"");
		   cell3=row.createCell(3);
		   cell3.setCellValue(transaction.getTimestamp().toString());
	  }
	  
        return workbook;
    }
 
}