package com.example.g508029.homefinancialcontrol.service;

import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;

public interface IExternalService {
    void exportTransactionsMonthly(String path, TransactionsMonthly transactionsMonthly);
}
