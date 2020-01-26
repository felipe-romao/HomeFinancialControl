package com.example.g508029.homefinancialcontrol;

public class Constants {
    public static final int LAST_TRANSACTIONS_COUNTER = 10;

    public static final String HHmm_TIME_FORMAT_PATTERN = "HH:mm";
    public static final String ddMMyy_DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    public static final String MMMMyyyy_DATE_FORMAT_PATTERN = "MMMM / yyyy";
    public static final String ddMMyyyyKma_DATE_FORMAT_PATTERN = "dd/MM/yyyy K:m a";
    public static final String ddMMyyyykkmm_DATE_FORMAT_PATTERN = "dd/MM/yyyy kk:mm";
    public static final String INCOME_DESCRIPTION = "RECEITA";
    public static final String EXPENSE_DESCRIPTION = "DESPESA";

    public static final String getTransactionSymbol(String transactionType){
        String symbol = "";
        switch (transactionType){
            case INCOME_DESCRIPTION:
                symbol = "+";
                break;
            case EXPENSE_DESCRIPTION:
                symbol = "-";
                break;
        }
        return symbol;
    }

    public static final String PAYMENT_MODE_FIXED_DESCRIPTION = "FIXA";
    public static final String PAYMENT_MODE_EVENTUAL_DESCRIPTION = "EVENTUAL";
}
