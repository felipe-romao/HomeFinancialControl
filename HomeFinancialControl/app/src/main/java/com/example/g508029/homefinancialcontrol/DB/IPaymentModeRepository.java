package com.example.g508029.homefinancialcontrol.DB;

import com.example.g508029.homefinancialcontrol.model.PaymentMode;

import java.util.List;

public interface IPaymentModeRepository {
    void addPaymentMode(PaymentMode paymentMode);
    List<PaymentMode> getAll();
    void deletePaymentModeById(String id);
}
