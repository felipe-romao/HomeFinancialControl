package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.mipmap.finance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent mainIntent = new Intent(this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    finishAffinity();
                }

                startActivity(mainIntent);
                break;

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
