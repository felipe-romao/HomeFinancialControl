package com.example.g508029.homefinancialcontrol.service;

import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;

public interface IExternalService {
    void exportTransactionsMonthly(String path, TransactionsYearly transactionsYearly);
}
