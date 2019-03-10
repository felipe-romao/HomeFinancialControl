package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.model.InfoTransactionGrouped;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.presenter.modelView.InfoTransactionGroupedModelView;

import java.util.ArrayList;
import java.util.List;

public class PaymentModeHelper {
    public static List<String> getAllDescriptionFromPaymentMode(List<PaymentMode> paymentModes){
        List<String> descriptions = new ArrayList<>();
        for (PaymentMode paymentMode: paymentModes){
            descriptions.add(paymentMode.getMode());
        }
        return descriptions;
    }

    public static List<InfoTransactionGroupedModelView> toInfoTransatcionGroupedListModelView(List<InfoTransactionGrouped> groupedList, FormatHelper formatHelper){
        List<InfoTransactionGroupedModelView> groupedModelListView = new ArrayList<>();
        for (InfoTransactionGrouped grouped : groupedList){
            groupedModelListView.add(toInfoTransatcionGroupedModelView(grouped, formatHelper));
        }
        return groupedModelListView;
    }

    private static InfoTransactionGroupedModelView toInfoTransatcionGroupedModelView(InfoTransactionGrouped grouped, FormatHelper formatHelper) {
        InfoTransactionGroupedModelView modelView = new InfoTransactionGroupedModelView();
        modelView.setDescription(grouped.getDescription());
        modelView.setQuantity(String.valueOf(grouped.getQuantity()));
        modelView.setTotal(formatHelper.fromDoubleToCurrencyString(grouped.getTotal()));
        return modelView;
    }
}
