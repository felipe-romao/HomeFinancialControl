package com.example.g508029.homefinancialcontrol.helper;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.model.Instalment;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.IntelmentModeView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.MMMMyyyy_DATE_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyyyykkmm_DATE_FORMAT_PATTERN;

public final class TransactionHelper {
    public static List<String> getTransactionTypes(){
        List<String> transactionTypes = new ArrayList<>();
        transactionTypes.add(INCOME_DESCRIPTION);
        transactionTypes.add(EXPENSE_DESCRIPTION);
        return transactionTypes;
    }

    public static List<String> getTransactionCategory(String transactionType, ICategoryRepository repository){
        ArrayList<String> categories = new ArrayList<>();
        for (Category category: repository.getAll()){
            if(category.getTypeMovement().equals(transactionType)){
                categories.add(category.getDescription());
            }
        }
        return categories;
    }

    public static List<Category> getTransactionCategories(String transactionType, ICategoryRepository repository){
        ArrayList<Category> categories = new ArrayList<>();
        for (Category category: repository.getAll()){
            if(category.getTypeMovement().equals(transactionType)){
                categories.add(category);
            }
        }
        return categories;
    }

    public static List<String> getTransactionKind(IPaymentModeRepository repository){
        ArrayList<String> kinds = new ArrayList<>();
        for (PaymentMode paymentMode: repository.getAll()){
            kinds.add(paymentMode.getMode());
        }
        return kinds;
    }

    public static List<String> getOptionsCashes(){
        ArrayList<String> optionsCashes = new ArrayList<>();

        for (int option = 1; option <= 20; option++){
            optionsCashes.add(GetOptionCashText(option));
        }
        return optionsCashes;
    }

    public static String GetOptionCashText(int number) {
        if (number == 1) {
            return "À vista";
        }
        return number + " vezes";
    }

    public static List<Instalment> getInstalmentsFromOptionsCashesSelected(double value, int instalmentQuantity, Date date, String description, String category){

        BigDecimal parcela = BigDecimal.valueOf(value).divide(BigDecimal.valueOf(instalmentQuantity), 2, RoundingMode.CEILING);
        BigDecimal ultimaParcela = BigDecimal.valueOf(value).subtract(parcela.multiply(BigDecimal.valueOf(instalmentQuantity - 1)));

        ArrayList<Instalment> instalments = new ArrayList<>();

        for (int id = 1; id <= instalmentQuantity; id++){
            String instalmentSequence    = id + "/" + instalmentQuantity;
            String instalmentDescription = description + "(" + instalmentSequence + ")";
            Date instalmentDate          = getDateFromInstalment(date, id - 1);
            double instalmentValue       = id == instalmentQuantity
                                                ? ultimaParcela.doubleValue()
                                                : parcela.doubleValue();

            instalments.add(new Instalment(id, instalmentValue, instalmentDescription, instalmentSequence, instalmentDate, category));
        }

        return instalments;
    }

    public static Date getDateFromInstalment(Date dateActual, int monthQuantity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateActual);
        calendar.add(calendar.MONTH, monthQuantity);

        return calendar.getTime();
    }

    public static List<Transaction> getTransactionsFromInstalmentViewList(Transaction transactionActual, List<IntelmentModeView> intelmentModeViews, FormatHelper formatHelper) throws ParseException {
        List<Transaction> transactions = new ArrayList<>();

            for(IntelmentModeView view : intelmentModeViews){
                String id          = UUID.randomUUID().toString();
                String type        = transactionActual.getType();
                String category    = transactionActual.getCategory();
                String frequency   = transactionActual.getFrequency();
                String paymentMode = transactionActual.getPaymentMode();
                double value       = formatHelper.fromCurrencyStringToDouble(view.getValue());
                Date date          = formatHelper.fromStringToDate(ddMMyyyykkmm_DATE_FORMAT_PATTERN, view.getDate());
                String description = "(" + view.getDescription() + ")";

                if(transactionActual.getDescription() == null || transactionActual.getDescription().isEmpty()) {
                    description = transactionActual.getCategory().concat(" ").concat(description);
                } else {
                    description = transactionActual.getDescription().concat(" ").concat(description);
                }

                transactions.add(new Transaction(id, type, description, value, date, category, paymentMode, frequency));
            }
        return transactions;
    }

    public static List<TransactionModelView> toTransactionsModelViewList(List<Transaction> transactions, FormatHelper formatHelper){
        List<TransactionModelView> transactionModelViews = new ArrayList<>();
        for (Transaction transaction: transactions){
            transactionModelViews.add(TransactionHelper.toTransactionModelView(transaction, formatHelper));
        }
        return transactionModelViews;
    }

    public static TransactionModelView toTransactionModelView(Transaction transaction, FormatHelper formatHelper){
        TransactionModelView modelView = new TransactionModelView();
        modelView.setId(transaction.getId());
        modelView.setDate(formatHelper.fromDateToString(ddMMyyyykkmm_DATE_FORMAT_PATTERN, transaction.getDate()));
        modelView.setDescription(transaction.getDescription());
        modelView.setPaymentMode(transaction.getPaymentMode());
        modelView.setCategory(transaction.getCategory());
        modelView.setType(transaction.getType());
        modelView.setValue(formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
        modelView.setTypeSymbol(Constants.getTransactionSymbol(transaction.getType()));
        modelView.setFrequency(transaction.getFrequency() == null || transaction.getFrequency().isEmpty()
                                                            ? "" : Character.toString(transaction.getFrequency().charAt(0)));
        return modelView;
    }

    public static List<IntelmentModeView> toInstalmentModelViewList(List<Instalment> instalments, FormatHelper formatHelper){
        List<IntelmentModeView> intelmentModelViews = new ArrayList<>();
        for (Instalment instalment: instalments){
            intelmentModelViews.add(TransactionHelper.toInstalmentModelView(instalment, formatHelper));
        }
        return intelmentModelViews;
    }

    public static IntelmentModeView toInstalmentModelView(Instalment instalment, FormatHelper formatHelper){
        IntelmentModeView modeView = new IntelmentModeView();
        modeView.setId(String.valueOf(instalment.getId()));
        modeView.setDescription(instalment.getSequence());
        modeView.setValue(formatHelper.fromDoubleToCurrencyString(instalment.getValue()));
        modeView.setDate(formatHelper.fromDateToString(ddMMyyyykkmm_DATE_FORMAT_PATTERN, instalment.getDate()));
        return modeView;
    }

    public static List<TransactionsMonthlyModelView> toTransactionMonthlyModelViewList(List<TransactionsMonthly> transactionsMonthlies, FormatHelper formatHelper){
        List<TransactionsMonthlyModelView> monthlyModelViews = new ArrayList<>();
        for(TransactionsMonthly transactionsMonthly: transactionsMonthlies){
            monthlyModelViews.add(TransactionHelper.toTransactionMonthlyModelView(transactionsMonthly, formatHelper));
        }
        return monthlyModelViews;
    }

    public static TransactionsMonthlyModelView toTransactionMonthlyModelView(TransactionsMonthly transactionsMonthly, FormatHelper formatHelper){
        TransactionsMonthlyModelView modelView = new TransactionsMonthlyModelView();
        modelView.setExpenseValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyExpense()));
        modelView.setIncomeValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyIncome()));
        modelView.setBalanceValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyBalanceValue()));
        modelView.setDate(formatHelper.fromDateToString(MMMMyyyy_DATE_FORMAT_PATTERN, transactionsMonthly.getPeriod()));
        return modelView;
    }
}
