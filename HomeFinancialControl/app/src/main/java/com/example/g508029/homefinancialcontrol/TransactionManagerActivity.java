package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import com.example.g508029.homefinancialcontrol.adpter.FragmentPagerAdpter;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public class TransactionManagerActivity extends AppCompatActivity {
    private FragmentPagerAdpter adpter;
    private TabLayout tabLayout;
    private String transactionTypeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_manager);

        Intent intent = getIntent();
        transactionTypeSelected = intent.getStringExtra("TRANSCATION_TYPE_SELECTED");
        this.createTabLayouts();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu memu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_transaction, memu);
        return super.onCreateOptionsMenu(memu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.transacions_save:
                TransactionFragment fragment = (TransactionFragment) this.adpter.getItem(this.tabLayout.getSelectedTabPosition());
                fragment.addTransactionInDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createTabLayouts(){
        ViewPager pager = findViewById(R.id.transactions_view_pager);

        this.adpter = new FragmentPagerAdpter(this, getSupportFragmentManager());
        adpter.add(TransactionFragment.newIntance(INCOME_DESCRIPTION), INCOME_DESCRIPTION);
        adpter.add(TransactionFragment.newIntance(EXPENSE_DESCRIPTION), EXPENSE_DESCRIPTION);

        pager.setAdapter(adpter);

        this.tabLayout = findViewById(R.id.transactions_tabs);
        this.tabLayout.setupWithViewPager(pager);

        if(transactionTypeSelected != null){
            if(transactionTypeSelected.toString().equals(INCOME_DESCRIPTION)){
                this.tabLayout.getTabAt(0).select();
            }
            if(transactionTypeSelected.toString().equals(EXPENSE_DESCRIPTION)){
                this.tabLayout.getTabAt(1).select();
            }
        }
    }
}
