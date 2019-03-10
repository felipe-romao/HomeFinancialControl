package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.PaymentModeHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.InfoTransactionGrouped;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.presenter.modelView.InfoTransactionGroupedModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PaymentModeReportPresenter {
    public interface IPaymentModeReportView{
        void setMonths(List<String> months);
        void setMonth(String month);
        String getMonthSelected();
        void setYear(String year);
        String getYear();
        void setInfoTransactionGroupedModelViews(List<InfoTransactionGroupedModelView> infoGroupedModelViews);
        void showMessage(String message);
        InfoTransactionGroupedModelView getSelectedInfoTransactionModelView();
        void setTransactionsFromSelectedPaymentMode(List<TransactionModelView> modelViews);
    }

    private IPaymentModeReportView view;
    private TransactionRepository repository;
    private FormatHelper formatHelper;

    public PaymentModeReportPresenter(IPaymentModeReportView view, TransactionRepository repository, FormatHelper formatHelper) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        try {
            List<String> monthNames = this.formatHelper.getMonthNames();
            String monthNameCurrent = this.formatHelper.getMonthNameCurrent();
            int yearCurrent = Calendar.getInstance(this.formatHelper.getLocate()).get(Calendar.YEAR);

            this.view.setMonths(monthNames);
            this.view.setMonth(monthNameCurrent);
            this.view.setYear(String.valueOf(yearCurrent));
            this.onGetAllInfoPaymentModeGrouped();

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar inicializar: " + ex.getMessage());
        }
    }

    public void onGetAllInfoPaymentModeGrouped(){
        try {
            int month = this.formatHelper.getMonthNumberByName(this.view.getMonthSelected());
            int year = Integer.valueOf(this.view.getYear());

            Log.d(TAG, "onGetAllInfoPaymentModeGrouped: mes + ano: " + month +" - " + year);
            List<InfoTransactionGrouped> groupedList = this.repository.getPaymentModeSumByMonth(month, year);
            Log.d(TAG, "onGetAllInfoPaymentModeGrouped: repos: " + groupedList.size());

            List<InfoTransactionGroupedModelView> modelViews = PaymentModeHelper.toInfoTransatcionGroupedListModelView(groupedList, formatHelper);
            Log.d(TAG, "onGetAllInfoPaymentModeGrouped: count: " + modelViews.size());
            this.view.setInfoTransactionGroupedModelViews(modelViews);

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar buscar dados: " + ex.getMessage());
        }
    }

    public void onShowTransactionsDetail() {
        InfoTransactionGroupedModelView modelView = this.view.getSelectedInfoTransactionModelView();
        Integer monthSelected = this.formatHelper.getMonthNumberByName(this.view.getMonthSelected());
        Integer year = Integer.valueOf(this.view.getYear());

        List<Transaction> transactions = this.repository.getTransactionsByPaymentModeAndMonthAndYear(modelView.getDescription(), monthSelected, year);
        this.view.setTransactionsFromSelectedPaymentMode(TransactionHelper.toTransactionsModelViewList(transactions, this.formatHelper));
    }

}
