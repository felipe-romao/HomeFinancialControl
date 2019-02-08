package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.List;

public class TransactionReportPresenter {
    public interface ITransactionReportView{
        void setMonths(List<String> months);
        String getMonthSelected();
        void setYear(String year);
        String getYear();
        void setTransactions(List<TransactionModelView> modelViews);
        TransactionModelView getTransactionSelected();
        void showMessage(String message);
    }
}
