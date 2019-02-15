package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;
import com.example.g508029.homefinancialcontrol.service.IExternalService;

import java.util.Calendar;

public class ExportTransactionsPresenter {
    public interface IExportTransactionsView{
        void setYear(String year);
        String getYear();
        void showResult(String result);
        String getPath();
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
            Calendar calendar = Calendar.getInstance();
            int monthCurrent = calendar.get(Calendar.MONTH - 1);
            int year = Integer.valueOf(this.view.getYear());
            TransactionsBuilder transactionsBuilder = new TransactionsBuilder(repository);
            TransactionsYearly transactionsYearly = transactionsBuilder.buildTransactionsYearly(01, monthCurrent, year);

            String fullPath = this.view.getPath() + "/lista_movimentacao_" + year + ".xls";
            this.externalService.exportTransactionsMonthly(fullPath, transactionsYearly);
            this.view.showResult("Arquivo gerado com sucesso: " + fullPath);

        }catch (Exception ex){
            this.view.showResult("Não foi possivel exportar as transações: " + ex.getMessage());
        }
    }
}

