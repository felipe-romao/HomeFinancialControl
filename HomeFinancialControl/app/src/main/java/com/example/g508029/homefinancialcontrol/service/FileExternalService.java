package com.example.g508029.homefinancialcontrol.service;

import android.content.Context;
import android.util.Log;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;
import com.example.g508029.homefinancialcontrol.system.IFileSystem;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyyyyKma_DATE_FORMAT_PATTERN;

public class FileExternalService implements IExternalService {
    private IFileSystem fileSystem;
    private FormatHelper formatHelper;

    public FileExternalService(IFileSystem fileSystem, FormatHelper formatHelper) {
        this.fileSystem = fileSystem;
        this.formatHelper = formatHelper;
    }

    @Override
    public void exportTransactionsMonthly(String path, TransactionsYearly transactionsYearly) {

        try {
            Workbook wb = new HSSFWorkbook();
            Cell c = null;

            CellStyle cs = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.YELLOW.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            Sheet sheet1 = null;
            sheet1 = wb.createSheet("Transações");

            Row headerRow = sheet1.createRow(0);
            Map<Integer, String> headerValues = this.createHeaderValues();
            for (int headerPos : headerValues.keySet()){
                c = headerRow.createCell(headerPos);
                c.setCellValue(headerValues.get(headerPos));
                c.setCellStyle(cs);
            }
            int pos = 1;

            for(TransactionsMonthly transactionsMonthly: transactionsYearly.getTransactionsMonthlies()){
                for (Transaction transaction: transactionsMonthly.getTransactions()){
                    Row detailRow = sheet1.createRow(pos);
                    this.populateRow(detailRow, c, 0, formatHelper.fromDateToString(ddMMyyyyKma_DATE_FORMAT_PATTERN,transaction.getDate()));
                    this.populateRow(detailRow, c, 1, transaction.getType());
                    this.populateRow(detailRow, c, 2, transaction.getCategory());
                    this.populateRow(detailRow, c, 3, transaction.getFrequency());
                    this.populateRow(detailRow, c, 4, transaction.getPaymentMode());
                    this.populateRow(detailRow, c, 5, formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
                    this.populateRow(detailRow, c, 6, transaction.getDescription());
                    pos++;
                }
            }

            OutputStream stream = fileSystem.create(path);
            wb.write(stream);
            stream.flush();
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "exportTransactionsMonthly: error:" + e.getMessage());
        }
    }

    private void populateRow(Row row, Cell cell, int column, String value){
        cell = row.createCell(column);
        cell.setCellValue(value);
    }

    private Map<Integer,String> createHeaderValues() {
        HashMap<Integer, String> headerValues = new HashMap<>();
        headerValues.put(0, "Data");
        headerValues.put(1, "Tipo");
        headerValues.put(2, "Categoria");
        headerValues.put(3, "Frequencia");
        headerValues.put(4, "Pagamento");
        headerValues.put(5, "Valor");
        headerValues.put(6, "Descrição");
        return headerValues;
    }
}
