package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.main_menu_payment_mode_manager:
                Intent paymentModeManagerIntent = new Intent(this, PaymentModeManagerActivity.class);
                startActivity(paymentModeManagerIntent);
                break;
            case R.id.main_menu_yearly_report:
                Intent transactionYearlyReportIntent = new Intent(this, TransactionYearlyReportActivity.class);
                startActivity(transactionYearlyReportIntent);
                break;
            case R.id.main_menu_category_manager:
                Intent categoryManagerIntent = new Intent(this, CategoryManagerActivity.class);
                startActivity(categoryManagerIntent);
                break;
            case R.id.main_menu_category_report:
                Intent categoryReportIntent = new Intent(this, CategoryReportActivity.class);
                startActivity(categoryReportIntent);
                break;
            case R.id.main_menu_transactions_report:
                Intent transactionsReportIntent = new Intent(this, TransactionsReportActivity.class);
                startActivity(transactionsReportIntent);
                break;
            case R.id.main_menu_payment_mode_report:
                Intent paymentModeReportIntent = new Intent(this, PaymentModeReportActivity.class);
                startActivity(paymentModeReportIntent);
                break;
            case R.id.main_menu_export_transactions_report:
                Intent exportTransactionsIntent = new Intent(this, ExportTransactionsActivity.class);
                startActivity(exportTransactionsIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
