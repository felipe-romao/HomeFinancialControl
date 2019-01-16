package com.example.g508029.homefinancialcontrol.helper;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChartHelper {
    public static void setValues(PieChart pieChart, HashMap<String, Double> categoriesValues){
        ArrayList<PieEntry> chartValues = new ArrayList<PieEntry>();

        int chartPosition = 0;
        for(Map.Entry<String, Double> map: categoriesValues.entrySet()){
            chartValues.add(new PieEntry(map.getValue().floatValue(),map.getKey(), chartPosition));
            chartPosition++;
        }
        PieDataSet dataSet = new PieDataSet(chartValues ,"Categorias");

        pieChart.setUsePercentValues(true);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.BLACK);

        dataSet.setValueLinePart1OffsetPercentage(100f);
        dataSet.setValueLinePart1Length(0.6f);
        dataSet.setValueLinePart2Length(0.6f);
        dataSet.setSliceSpace(0f);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setColors(ChartHelper.colorsList());

        pieChart.setData(data);
        pieChart.setEntryLabelTextSize(8f);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setHoleRadius(50f);
        pieChart.animateXY(1000, 1000);
        pieChart.setExtraOffsets(0.5f,0.5f,0.5f,0.5f);
        pieChart.setCameraDistance(1000);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();

//        Legend legend = pieChart.getLegend();
//        legend.setTextSize(8f);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setWordWrapEnabled(true);
//        legend.setDrawInside(false);
    }

    public static ArrayList<Integer> colorsList() {
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        return colors;

    }
}
