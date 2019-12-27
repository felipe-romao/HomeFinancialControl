package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.adpter.TransactionsReportAdapter;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.List;

public class FiltredTransactionsActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtred_transactions);

        Intent intent = getIntent();
        List<TransactionModelView> transactionModelViewList = (List<TransactionModelView>) intent.getSerializableExtra("transactions");
        String title = intent.getStringExtra("title");
        TransactionsReportAdapter adapter = new TransactionsReportAdapter(this, transactionModelViewList);

        TextView titleTextView = findViewById(R.id.filtred_transactions_textview);
        ListView listView = findViewById(R.id.filtred_transactions_listview);

        titleTextView.setText(title);
        listView.setAdapter(adapter);
    }
}
