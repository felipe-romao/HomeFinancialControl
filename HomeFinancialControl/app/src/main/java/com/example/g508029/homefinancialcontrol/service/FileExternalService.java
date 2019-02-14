package com.example.g508029.homefinancialcontrol.service;

import android.content.Context;
import android.util.Log;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.system.IFileSystem;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.MMddyyyyKma_DATE_FORMAT_PATTERN;

public class FileExternalService implements IExternalService {
    private IFileSystem fileSystem;
    private FormatHelper formatHelper;

    public FileExternalService(IFileSystem fileSystem, FormatHelper formatHelper) {
        this.fileSystem = fileSystem;
        this.formatHelper = formatHelper;
    }

    @Override
    public void exportTransactionsMonthly(String path, TransactionsMonthly transactionsMonthly) {

        try {
            Workbook wb = new HSSFWorkbook();
            Cell c = null;

            //Cell style for header row
            CellStyle cs = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.AUTOMATIC.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            //New Sheet
            Sheet sheet1 = null;
            sheet1 = wb.createSheet("Transações");

            Map<Integer, String> headerValues = new HashMap<>();
            headerValues.put(0, "Data");
            headerValues.put(1, "Tipo");
            headerValues.put(2, "Categoria");
            headerValues.put(3, "Pagamento");
            headerValues.put(4, "Valor");
            headerValues.put(5, "Descrição");

            Row row = sheet1.createRow(0);
            for (int headerPos : headerValues.keySet()){
                c = row.createCell(headerPos);
                c.setCellValue(headerValues.get(headerPos));
                c.setCellStyle(cs);
            }
            int pos = 1;
            for (Transaction transaction: transactionsMonthly.getTransactions()){
                Row row2 = sheet1.createRow(pos);
                c = row2.createCell(0);
                c.setCellValue(formatHelper.fromDateToString(MMddyyyyKma_DATE_FORMAT_PATTERN,transaction.getDate()));

                c = row2.createCell(1);
                c.setCellValue(transaction.getType());

                c = row2.createCell(2);
                c.setCellValue(transaction.getCategory());

                c = row2.createCell(3);
                c.setCellValue(transaction.getPaymentMode());

                c = row2.createCell(4);
                c.setCellValue(formatHelper.fromDoubleToCurrencyString(transaction.getValue()));

                c = row2.createCell(5);
                c.setCellValue(transaction.getDescription());

                pos++;
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
}
