package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Instalment;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.presenter.modelView.IntelmentModeView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.HHmm_TIME_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyy_DATE_FORMAT_PATTERN;

public class TransactionFragmentPresenter {
    public interface ITransactionFragmentView{
        String getCategory();
        String getTransactionValue();
        String getDescription();
        String getPaymentMode();
        String getTransactionDate();
        String getTransactionTime();
        String getTransactionType();
        int getOptionCashSelected();
        List<IntelmentModeView> getInstalments();
        void setInstalments(List<IntelmentModeView> modelView);
        void setInitialDateTime();
        void setTransactionValue(String value);
        void setCategories(List<String> categories);
        void setPaymentModes(List<String> paymentModes);
        void setOptionsCashes(List<String> optionsCashes);
        void showMessage(String message);
    }

    private ITransactionFragmentView view;
    private TransactionRepository repository;
    private ICategoryRepository categoryRepository;
    private IPaymentModeRepository paymentModeRepository;
    private FormatHelper formatHelper;

    public TransactionFragmentPresenter(ITransactionFragmentView view, TransactionRepository repository, ICategoryRepository categoryRepository, IPaymentModeRepository paymentModeRepository, FormatHelper formatHelper){
        this.view = view;
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.paymentModeRepository = paymentModeRepository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        try {
            List<String> transactionCategories = TransactionHelper.getTransactionCategory(this.view.getTransactionType(), this.categoryRepository);
            List<String> transactionPaymentModes = TransactionHelper.getTransactionKind(this.paymentModeRepository);
            this.view.setCategories(transactionCategories);
            this.view.setPaymentModes(transactionPaymentModes);
            this.view.setOptionsCashes(TransactionHelper.getOptionsCashes());
            initializeValues();

        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao inicializar as informações!");
        }
    }

    public void onAddNewTransaction(){
        try {
            this.validateValues();
            String id           = UUID.randomUUID().toString();
            String type         = this.view.getTransactionType();
            String category     = this.view.getCategory();
            double value        = this.formatHelper.fromCurrencyStringToDouble(this.view.getTransactionValue());
            String description  = this.view.getDescription();
            String paymentMode  = this.view.getPaymentMode();
            Date date           = this.getDateFromView();

            Transaction transaction = new Transaction(id, type, description, value, date, category, paymentMode);
            this.repository.addTransaction(transaction);

            initializeValues();
            this.view.showMessage("Transação '" + transaction +"' gravada com sucesso!");

        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar gravar esta transação: " + ex.getMessage());
        }
    }

    public void onOptionCashSelected() {
        try {
            int optionCashSelected = this.view.getOptionCashSelected();

            if(optionCashSelected == 1){
                this.view.setInstalments(new ArrayList<IntelmentModeView>());
                return;
            }

            String description = this.view.getDescription();
            double value       = this.formatHelper.fromCurrencyStringToDouble(this.view.getTransactionValue());
            Date date          = this.getDateFromView();

            List<Instalment> instalments = TransactionHelper.getInstalmentsFromOptionsCashesSelected(value,
                    this.view.getOptionCashSelected(),
                    date,
                    description);

            this.view.setInstalments(TransactionHelper.toInstalmentModelViewList(instalments, this.formatHelper));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeValues() {
        this.view.setInitialDateTime();
        this.view.setTransactionValue(this.formatHelper.fromDoubleToCurrencyString(0.0));
    }

    private void validateValues(){
        if(this.view.getTransactionType() == null || this.view.getTransactionType().isEmpty())
            throw new RuntimeException("informe um tipo de transação.");
        if(this.view.getCategory() == null || this.view.getCategory().isEmpty())
            throw new RuntimeException("selecione uma categoria.");
        if(this.view.getPaymentMode() == null || this.view.getPaymentMode().isEmpty())
            throw new RuntimeException("selecione um modo de pagamento ou recebimento.");
    }

    private Date getDateFromView() throws ParseException {
        String dateAddFormat = ddMMyy_DATE_FORMAT_PATTERN + " " + HHmm_TIME_FORMAT_PATTERN;
        String dateMerged = this.view.getTransactionDate() + " " + this.view.getTransactionTime();
        return this.formatHelper.fromStringToDate(dateAddFormat, dateMerged);
    }
}
