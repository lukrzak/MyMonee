package com.lukrzak.MyMonee.MyMonee.export;

import com.lukrzak.MyMonee.MyMonee.repositories.ExpenseRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelReport {

    @Autowired
    public ExcelReport(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    private final ExpenseRepository expenseRepository;
    private final int STARTING_ROW = 0;
    private final int NUMBER_OF_COLUMNS = 7;
    private final int PRICE_COLUMN_NUMBER = 3;
    private final int QUANTITY_COLUMN_NUMBER = 4;
    private final String[] COLUMN_NAMES = {"Name", "Model", "Category", "Price", "Quantity", "Date", "User", "Total cost"};

    @Value("${dataExport.path}")
    private String PATH;

    public void generateReport() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Expenses");

        addHeadRow(sheet);
        addDataToExcel(sheet);
        getReport(workbook);
    }

    public void addHeadRow(XSSFSheet sheet){
        XSSFRow row = sheet.createRow(STARTING_ROW);
        int index = 0;
        for (String col : COLUMN_NAMES){
            Cell cell = row.createCell(index++);
            cell.setCellValue(col);
        }
    }

    public void addDataToExcel(XSSFSheet sheet){
        List<Object[]> data = expenseRepository.getDataForExcelReport();
        int rowNumber = 1;
        for(Object[] d : data){
            XSSFRow row = sheet.createRow(rowNumber++);
            for(int i = 0; i < NUMBER_OF_COLUMNS; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(String.valueOf(d[i]));
            }
            Cell cell = row.createCell(NUMBER_OF_COLUMNS);
            cell.setCellValue(String.valueOf((double) d[PRICE_COLUMN_NUMBER] * (int) d[QUANTITY_COLUMN_NUMBER]));
        }
    }

    public void getReport(XSSFWorkbook workbook) throws IOException {
        FileOutputStream out = new FileOutputStream(PATH);
        workbook.write(out);
        out.close();
    }
}
