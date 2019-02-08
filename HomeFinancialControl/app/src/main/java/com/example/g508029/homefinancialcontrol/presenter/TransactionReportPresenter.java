package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.Calendar;
import java.util.List;

public class TransactionReportPresenter {
    public interface ITransactionReportView{
        void setMonths(List<String> months);
        void setMonth(String month);
        String getMonthSelected();
        void setYear(String year);
        String getYear();
        void setTransactions(List<TransactionModelView> modelViews);
        TransactionModelView getTransactionSelected();
        void showMessage(String message);
    }

    private ITransactionReportView view;
    private TransactionRepository repository;
    FormatHelper formatHelper;

    public TransactionReportPresenter(ITransactionReportView view, TransactionRepository repository, FormatHelper formatHelper) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        try {
            int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
            this.view.setMonths(this.formatHelper.getMonthNames());
            this.view.setMonth(this.formatHelper.getMonthNameCurrent());
            this.view.setYear(String.valueOf(yearCurrent));
            this.onGetAllTransactionsByPeriod();

        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao inicializar: " + ex.getMessage());
        }
    }

    public void onGetAllTransactionsByPeriod(){
        try {
            int month = this.formatHelper.getMonthNumberByName(this.view.getMonthSelected());
            int year = Integer.valueOf(this.view.getYear());
            List<Transaction> transactions = this.repository.getAllTransactionsByMonth(month, year);
            this.view.setTransactions(TransactionHelper.toTransactionsModelViewList(transactions, this.formatHelper));

        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao buscar as transações: " + ex.getMessage());
        }
    }

    public void onDeleteTransactionSelected(){
        try {
            TransactionModelView transactionSelected = this.view.getTransactionSelected();
            this.repository.deleteTransaction(transactionSelected.getId());
            this.view.showMessage("Transação removida com sucesso!");
        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao deletar esta transação: " + ex.getMessage());
        }
    }
}
