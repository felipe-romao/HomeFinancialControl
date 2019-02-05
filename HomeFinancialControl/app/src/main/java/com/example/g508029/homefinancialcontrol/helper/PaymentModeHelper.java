package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.model.PaymentMode;

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
}
