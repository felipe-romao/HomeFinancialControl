package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.model.Instalment;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.MMMMyyyy_DATE_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyyyyKma_DATE_FORMAT_PATTERN;

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

    public static List<Instalment> getInstalmentsFromOptionsCashesSelected(double value, int instalmentQuantity, Date date, String description){
        BigDecimal[] valueFromInstalment = new BigDecimal(value)
                                                    .divideAndRemainder(new BigDecimal(instalmentQuantity));

        ArrayList<Instalment> instalments = new ArrayList<>();

        for (int id = 1; id <= instalmentQuantity; id++){
            String instalmentDescription = description + "(" + id + "/" + instalmentQuantity + ")";
            Date instalmentDate          = getDateFromInstalment(date, id - 1);
            double instalmentValue       = id == instalmentQuantity
                                                ? valueFromInstalment[0].doubleValue() + valueFromInstalment[1].doubleValue()
                                                : valueFromInstalment[0].doubleValue();

            instalments.add(new Instalment(id, instalmentValue, instalmentDescription, instalmentDate));
        }

        return instalments;
    }

    public static Date getDateFromInstalment(Date dateActual, int monthQuantity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateActual);
        calendar.add(calendar.MONTH, monthQuantity);

        return calendar.getTime();
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
        modelView.setDate(formatHelper.fromDateToString(ddMMyyyyKma_DATE_FORMAT_PATTERN, transaction.getDate()));
        modelView.setDescription(transaction.getDescription());
        modelView.setPaymentMode(transaction.getPaymentMode());
        modelView.setCategory(transaction.getCategory());
        modelView.setType(transaction.getType());
        modelView.setValue(formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
        modelView.setTypeSymbol(Constants.getTransactionSymbol(transaction.getType()));
        return modelView;
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
