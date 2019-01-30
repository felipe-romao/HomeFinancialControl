package com.example.g508029.homefinancialcontrol.Helper;

import android.support.test.filters.LargeTest;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@LargeTest
public class FormaHelperUnitTest {

    @Test
    public void getAllMonthNames_createFormatHelperOfLocatePtBR_GetAllMonthNames(){
        FormatHelper formatHelper = new FormatHelper(new Locale("pt", "BR"));
        List<String> monthNames = formatHelper.getMonthNames();

        Assert.assertEquals("Janeiro", monthNames.get(0));
        Assert.assertEquals("Fevereiro", monthNames.get(1));
        Assert.assertEquals("Março", monthNames.get(2));
        Assert.assertEquals("Abril", monthNames.get(3));
        Assert.assertEquals("Maio", monthNames.get(4));
        Assert.assertEquals("Junho", monthNames.get(5));
        Assert.assertEquals("Julho", monthNames.get(6));
        Assert.assertEquals("Agosto", monthNames.get(7));
        Assert.assertEquals("Setembro", monthNames.get(8));
        Assert.assertEquals("Outubro", monthNames.get(9));
        Assert.assertEquals("Novembro", monthNames.get(10));
        Assert.assertEquals("Dezembro", monthNames.get(11));
    }

    @Test
    public void getMonthNameCurrent_createFormatHelperOfLocatePtBR_GetMonthName() {
        String[] monthNames = {"Janeiro", "Fevereiro", "Março", "Abril",
                                "Maio", "Junho", "Julho", "Agosto",
                                "Setembro", "Outubro", "Novembro", "Dezembro"};

        Locale locale = new Locale("pt", "BR");
        FormatHelper formatHelper = new FormatHelper(locale);
        String monthName = formatHelper.getMonthNameCurrent();

        Assert.assertEquals(monthNames[Calendar.getInstance(locale).MONTH], monthName);
    }
}
