package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;
import com.example.g508029.homefinancialcontrol.service.IExternalService;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class ExportTransactionsPresenter {
    public interface IExportTransactionsView{
        void setYear(String year);
        String getYear();
        void showResult(String result);
        String getPath();
        void setPath(String path);
    }

    private IExportTransactionsView view;
    private TransactionRepository repository;
    private IExternalService externalService;

    public ExportTransactionsPresenter(IExportTransactionsView view, TransactionRepository repository, IExternalService externalService) {
        this.view = view;
        this.repository = repository;
        this.externalService = externalService;
    }

    public void initialize(){
        try {
            Calendar calendar = Calendar.getInstance();
            this.view.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
        }catch (Exception ex){
            this.view.showResult("Ocorreu um erro ao tentar inicializar: " + ex.getMessage());
        }
    }

    public void onDownloaderTransactionExternalFile(){
        try {
            int year = Integer.valueOf(this.view.getYear());
            TransactionsBuilder transactionsBuilder = new TransactionsBuilder(repository);
            TransactionsYearly transactionsYearly = transactionsBuilder.buildTransactionsYearly(1, 12, year);

            String fullPath = this.view.getPath() + "/lista_movimentacao_" + year + ".xls";
            this.externalService.exportTransactionsMonthly(fullPath, transactionsYearly);
            this.view.showResult("Arquivo exportado com sucesso");
            this.view.setPath(fullPath);

        }catch (Exception ex){
            this.view.showResult("Não foi possivel exportar as transações: " + ex.getMessage());
        }
    }
}

