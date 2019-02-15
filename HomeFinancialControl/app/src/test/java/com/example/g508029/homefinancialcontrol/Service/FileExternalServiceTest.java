package com.example.g508029.homefinancialcontrol.Service;

import android.support.test.filters.LargeTest;

import com.example.g508029.homefinancialcontrol.DataTestHelper;
import com.example.g508029.homefinancialcontrol.System.MemoryStream;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.service.FileExternalService;
import com.example.g508029.homefinancialcontrol.service.IExternalService;
import com.example.g508029.homefinancialcontrol.system.FileSystem;
import com.example.g508029.homefinancialcontrol.system.IFileSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@LargeTest
public class FileExternalServiceTest {
    private IExternalService service;
    private MemoryStream fileSystem;

    @Before
    public void setUp(){
        Locale mLocale = new Locale("pt", "BR");
        FormatHelper formatHelper = new FormatHelper(mLocale);
        //this.service = new FileExternalService(new FileSystem(), formatHelper);
        this.fileSystem = new MemoryStream();
        this.service = new FileExternalService(this.fileSystem, formatHelper);
    }

    @Test
    public void validateFileExported_createFileToExport_resultIsSuccess(){
        TransactionsMonthly transactionsMonthly = new TransactionsMonthly(02, 2019, DataTestHelper.createTransactionList());
        List<Transaction> transactionsYearly = DataTestHelper.createTransactionsYearly();

        this.service.exportTransactionsMonthly("C:\\data\\system\\teste.xls", null);

        Assert.assertEquals(externalFileGenerated(), Arrays.toString(this.fileSystem.getByteArray()));
    }

    private String externalFileGenerated(){
        return "[-48, -49, 17, -32, -95, -79, 26, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 59, 0, 3, 0, -2, -1, 9, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 6, 0, 0, 0, 1, 0, 0, 0, -2, -1, -1, -1, 0, 0, 0, 0, 7, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 82, 0, 111, 0, 111, 0, 116, 0, 32, 0, 69, 0, 110, 0, 116, 0, 114, 0, 121, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 0, 5, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, -64, 9, 0, 0, 0, 0, 0, 0, 87, 0, 111, 0, 114, 0, 107, 0, 98, 0, 111, 0, 111, 0, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 2, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -107, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 8, 16, 0, 0, 6, 5, 0, -45, 16, -52, 7, 65, 0, 0, 0, 6, 0, 0, 0, -31, 0, 2, 0, -80, 4, -63, 0, 2, 0, 0, 0, -30, 0, 0, 0, 92, 0, 112, 0, 12, 0, 0, 102, 101, 108, 105, 112, 101, 46, 114, 111, 109, 97, 111, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 66, 0, 2, 0, -80, 4, 97, 1, 2, 0, 0, 0, 61, 1, 2, 0, 0, 0, -100, 0, 2, 0, 14, 0, 25, 0, 2, 0, 0, 0, 18, 0, 2, 0, 0, 0, 19, 0, 2, 0, 0, 0, -81, 1, 2, 0, 0, 0, -68, 1, 2, 0, 0, 0, 61, 0, 18, 0, 104, 1, 14, 1, 92, 58, -66, 35, 56, 0, 0, 0, 0, 0, 1, 0, 88, 2, 64, 0, 2, 0, 0, 0, -115, 0, 2, 0, 0, 0, 34, 0, 2, 0, 0, 0, 14, 0, 2, 0, 1, 0, -73, 1, 2, 0, 0, 0, -38, 0, 2, 0, 0, 0, 49, 0, 21, 0, -56, 0, 0, 0, -1, 127, -112, 1, 0, 0, 0, 0, 0, 0, 5, 0, 65, 114, 105, 97, 108, 49, 0, 21, 0, -56, 0, 0, 0, -1, 127, -112, 1, 0, 0, 0, 0, 0, 0, 5, 0, 65, 114, 105, 97, 108, 49, 0, 21, 0, -56, 0, 0, 0, -1, 127, -112, 1, 0, 0, 0, 0, 0, 0, 5, 0, 65, 114, 105, 97, 108, 49, 0, 21, 0, -56, 0, 0, 0, -1, 127, -112, 1, 0, 0, 0, 0, 0, 0, 5, 0, 65, 114, 105, 97, 108, 30, 4, 28, 0, 5, 0, 23, 0, 0, 34, 36, 34, 35, 44, 35, 35, 48, 95, 41, 59, 92, 40, 34, 36, 34, 35, 44, 35, 35, 48, 92, 41, 30, 4, 33, 0, 6, 0, 28, 0, 0, 34, 36, 34, 35, 44, 35, 35, 48, 95, 41, 59, 91, 82, 101, 100, 93, 92, 40, 34, 36, 34, 35, 44, 35, 35, 48, 92, 41, 30, 4, 34, 0, 7, 0, 29, 0, 0, 34, 36, 34, 35, 44, 35, 35, 48, 46, 48, 48, 95, 41, 59, 92, 40, 34, 36, 34, 35, 44, 35, 35, 48, 46, 48, 48, 92, 41, 30, 4, 39, 0, 8, 0, 34, 0, 0, 34, 36, 34, 35, 44, 35, 35, 48, 46, 48, 48, 95, 41, 59, 91, 82, 101, 100, 93, 92, 40, 34, 36, 34, 35, 44, 35, 35, 48, 46, 48, 48, 92, 41, 30, 4, 55, 0, 42, 0, 50, 0, 0, 95, 40, 34, 36, 34, 42, 32, 35, 44, 35, 35, 48, 95, 41, 59, 95, 40, 34, 36, 34, 42, 32, 92, 40, 35, 44, 35, 35, 48, 92, 41, 59, 95, 40, 34, 36, 34, 42, 32, 34, 45, 34, 95, 41, 59, 95, 40, 64, 95, 41, 30, 4, 46, 0, 41, 0, 41, 0, 0, 95, 40, 42, 32, 35, 44, 35, 35, 48, 95, 41, 59, 95, 40, 42, 32, 92, 40, 35, 44, 35, 35, 48, 92, 41, 59, 95, 40, 42, 32, 34, 45, 34, 95, 41, 59, 95, 40, 64, 95, 41, 30, 4, 63, 0, 44, 0, 58, 0, 0, 95, 40, 34, 36, 34, 42, 32, 35, 44, 35, 35, 48, 46, 48, 48, 95, 41, 59, 95, 40, 34, 36, 34, 42, 32, 92, 40, 35, 44, 35, 35, 48, 46, 48, 48, 92, 41, 59, 95, 40, 34, 36, 34, 42, 32, 34, 45, 34, 63, 63, 95, 41, 59, 95, 40, 64, 95, 41, 30, 4, 54, 0, 43, 0, 49, 0, 0, 95, 40, 42, 32, 35, 44, 35, 35, 48, 46, 48, 48, 95, 41, 59, 95, 40, 42, 32, 92, 40, 35, 44, 35, 35, 48, 46, 48, 48, 92, 41, 59, 95, 40, 42, 32, 34, 45, 34, 63, 63, 95, 41, 59, 95, 40, 64, 95, 41, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 2, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 2, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, -11, -1, 32, 0, 0, -12, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, 1, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 43, 0, -11, -1, 32, 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 41, 0, -11, -1, 32, 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 44, 0, -11, -1, 32, 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 42, 0, -11, -1, 32, 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 1, 0, 9, 0, -11, -1, 32, 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, -64, 32, -32, 0, 20, 0, 0, 0, 0, 0, 1, 0, 32, 0, 0, 0, 0, 0, 8, 4, 8, 4, 0, 4, -64, 32, -109, 2, 4, 0, 16, -128, 3, -1, -109, 2, 4, 0, 17, -128, 6, -1, -109, 2, 4, 0, 18, -128, 4, -1, -109, 2, 4, 0, 19, -128, 7, -1, -109, 2, 4, 0, 0, -128, 0, -1, -109, 2, 4, 0, 20, -128, 5, -1, 96, 1, 2, 0, 0, 0, -123, 0, 18, 0, -28, 6, 0, 0, 0, 0, 10, 0, 84, 114, 97, 110, 115, 97, -25, -11, 101, 115, -116, 0, 4, 0, 1, 0, 1, 0, -82, 1, 4, 0, 1, 0, 1, 4, 23, 0, 8, 0, 1, 0, 0, 0, 0, 0, 0, 0, -4, 0, 90, 1, 24, 0, 0, 0, 23, 0, 0, 0, 4, 0, 0, 68, 97, 116, 97, 4, 0, 0, 84, 105, 112, 111, 9, 0, 0, 67, 97, 116, 101, 103, 111, 114, 105, 97, 9, 0, 0, 80, 97, 103, 97, 109, 101, 110, 116, 111, 5, 0, 0, 86, 97, 108, 111, 114, 9, 0, 0, 68, 101, 115, 99, 114, 105, -25, -29, 111, 17, 0, 0, 48, 49, 47, 48, 50, 47, 50, 48, 49, 57, 32, 48, 58, 48, 32, 65, 77, 7, 0, 0, 68, 69, 83, 80, 69, 83, 65, 15, 0, 0, 67, 65, 84, 69, 71, 79, 82, 89, 95, 84, 69, 83, 84, 95, 49, 14, 0, 0, 80, 65, 89, 77, 69, 78, 84, 95, 77, 79, 68, 69, 95, 49, 8, 0, 0, 82, 36, 32, 49, 48, 44, 53, 48, 18, 0, 0, 68, 69, 83, 67, 82, 73, 80, 84, 73, 79, 78, 95, 84, 69, 83, 84, 95, 49, 17, 0, 0, 48, 50, 47, 50, 56, 47, 50, 48, 49, 57, 32, 48, 58, 48, 32, 65, 77, 7, 0, 0, 82, 69, 67, 69, 73, 84, 65, 15, 0, 0, 67, 65, 84, 69, 71, 79, 82, 89, 95, 84, 69, 83, 84, 95, 50, 14, 0, 0, 80, 65, 89, 77, 69, 78, 84, 95, 77, 79, 68, 69, 95, 50, 8, 0, 0, 82, 36, 32, 49, 56, 44, 48, 48, 18, 0, 0, 68, 69, 83, 67, 82, 73, 80, 84, 73, 79, 78, 95, 84, 69, 83, 84, 95, 50, 17, 0, 0, 48, 55, 47, 49, 56, 47, 50, 48, 49, 56, 32, 48, 58, 48, 32, 65, 77, 15, 0, 0, 67, 65, 84, 69, 71, 79, 82, 89, 95, 84, 69, 83, 84, 95, 51, 14, 0, 0, 80, 65, 89, 77, 69, 78, 84, 95, 77, 79, 68, 69, 95, 51, 7, 0, 0, 82, 36, 32, 56, 44, 55, 52, 18, 0, 0, 68, 69, 83, 67, 82, 73, 80, 84, 73, 79, 78, 95, 84, 69, 83, 84, 95, 51, -1, 0, 26, 0, 8, 0, 112, 5, 0, 0, 12, 0, 0, 0, -56, 5, 0, 0, 100, 0, 0, 0, 76, 6, 0, 0, -24, 0, 0, 0, 10, 0, 0, 0, 9, 8, 16, 0, 0, 6, 16, 0, -69, 13, -52, 7, -63, 0, 0, 0, 6, 0, 0, 0, 11, 2, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 88, 9, 0, 0, 13, 0, 2, 0, 1, 0, 12, 0, 2, 0, 100, 0, 15, 0, 2, 0, 1, 0, 17, 0, 2, 0, 0, 0, 16, 0, 8, 0, -4, -87, -15, -46, 77, 98, 80, 63, 95, 0, 2, 0, 1, 0, 42, 0, 2, 0, 0, 0, 43, 0, 2, 0, 0, 0, -126, 0, 2, 0, 1, 0, -128, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 2, 4, 0, 0, 0, -1, 0, -127, 0, 2, 0, -63, 4, 20, 0, 0, 0, 21, 0, 0, 0, -125, 0, 2, 0, 0, 0, -124, 0, 2, 0, 0, 0, -95, 0, 34, 0, 1, 0, 100, 0, 1, 0, 1, 0, 1, 0, 2, 0, 44, 1, 44, 1, 0, 0, 0, 0, 0, 0, -32, 63, 0, 0, 0, 0, 0, 0, -32, 63, 1, 0, 85, 0, 2, 0, 8, 0, 0, 2, 14, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 5, 0, 0, 0, 8, 2, 16, 0, 0, 0, 0, 0, 6, 0, -1, 0, 0, 0, 0, 0, 0, 1, 15, 0, 8, 2, 16, 0, 1, 0, 0, 0, 6, 0, -1, 0, 0, 0, 0, 0, 0, 1, 15, 0, 8, 2, 16, 0, 2, 0, 0, 0, 6, 0, -1, 0, 0, 0, 0, 0, 0, 1, 15, 0, 8, 2, 16, 0, 3, 0, 0, 0, 6, 0, -1, 0, 0, 0, 0, 0, 0, 1, 15, 0, -3, 0, 10, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, -3, 0, 10, 0, 0, 0, 1, 0, 21, 0, 1, 0, 0, 0, -3, 0, 10, 0, 0, 0, 2, 0, 21, 0, 2, 0, 0, 0, -3, 0, 10, 0, 0, 0, 3, 0, 21, 0, 3, 0, 0, 0, -3, 0, 10, 0, 0, 0, 4, 0, 21, 0, 4, 0, 0, 0, -3, 0, 10, 0, 0, 0, 5, 0, 21, 0, 5, 0, 0, 0, -3, 0, 10, 0, 1, 0, 0, 0, 15, 0, 6, 0, 0, 0, -3, 0, 10, 0, 1, 0, 1, 0, 15, 0, 7, 0, 0, 0, -3, 0, 10, 0, 1, 0, 2, 0, 15, 0, 8, 0, 0, 0, -3, 0, 10, 0, 1, 0, 3, 0, 15, 0, 9, 0, 0, 0, -3, 0, 10, 0, 1, 0, 4, 0, 15, 0, 10, 0, 0, 0, -3, 0, 10, 0, 1, 0, 5, 0, 15, 0, 11, 0, 0, 0, -3, 0, 10, 0, 2, 0, 0, 0, 15, 0, 12, 0, 0, 0, -3, 0, 10, 0, 2, 0, 1, 0, 15, 0, 13, 0, 0, 0, -3, 0, 10, 0, 2, 0, 2, 0, 15, 0, 14, 0, 0, 0, -3, 0, 10, 0, 2, 0, 3, 0, 15, 0, 15, 0, 0, 0, -3, 0, 10, 0, 2, 0, 4, 0, 15, 0, 16, 0, 0, 0, -3, 0, 10, 0, 2, 0, 5, 0, 15, 0, 17, 0, 0, 0, -3, 0, 10, 0, 3, 0, 0, 0, 15, 0, 18, 0, 0, 0, -3, 0, 10, 0, 3, 0, 1, 0, 15, 0, 7, 0, 0, 0, -3, 0, 10, 0, 3, 0, 2, 0, 15, 0, 19, 0, 0, 0, -3, 0, 10, 0, 3, 0, 3, 0, 15, 0, 20, 0, 0, 0, -3, 0, 10, 0, 3, 0, 4, 0, 15, 0, 21, 0, 0, 0, -3, 0, 10, 0, 3, 0, 5, 0, 15, 0, 22, 0, 0, 0, -41, 0, 12, 0, -96, 1, 0, 0, 60, 0, 84, 0, 84, 0, 84, 0, 62, 2, 18, 0, -74, 6, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 0, 15, 0, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 5, 0, 0, 0, 6, 0, 0, 0, 7, 0, 0, 0, 8, 0, 0, 0, 9, 0, 0, 0, 10, 0, 0, 0, 11, 0, 0, 0, 12, 0, 0, 0, 13, 0, 0, 0, 14, 0, 0, 0, 15, 0, 0, 0, 16, 0, 0, 0, 17, 0, 0, 0, 18, 0, 0, 0, 19, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 22, 0, 0, 0, 23, 0, 0, 0, 24, 0, 0, 0, 25, 0, 0, 0, 26, 0, 0, 0, 27, 0, 0, 0, 28, 0, 0, 0, 29, 0, 0, 0, 30, 0, 0, 0, 31, 0, 0, 0, 32, 0, 0, 0, 33, 0, 0, 0, 34, 0, 0, 0, 35, 0, 0, 0, 36, 0, 0, 0, 37, 0, 0, 0, 38, 0, 0, 0, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, 2, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 0, 5, 0, 0, 0, -2, -1, -1, -1, -2, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]";
    }
}
