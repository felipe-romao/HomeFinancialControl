package com.example.g508029.homefinancialcontrol;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.DB.DBHelper;
import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.presenter.ExportTransactionsPresenter;
import com.example.g508029.homefinancialcontrol.service.FileExternalService;
import com.example.g508029.homefinancialcontrol.service.IExternalService;
import com.example.g508029.homefinancialcontrol.system.FileSystem;

import java.util.Locale;

public class ExportTransactionsActivity extends AppCompatActivity implements ExportTransactionsPresenter.IExportTransactionsView{

    private EditText yearEditText;
    private TextView resultTextView;
    private Button exportButton;
    private FormatHelper formatHelper;
    private TransactionRepository repository;
    private IExternalService externalService;
    private ExportTransactionsPresenter presenter;
    private TextView filePathTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_transactions);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeListernsEvent();
    }

    @Override
    public void setYear(String year) {
        this.yearEditText.setText(year);
    }

    @Override
    public String getYear() {
        return this.yearEditText.getText().toString();
    }

    @Override
    public void showResult(String result) {
        this.resultTextView.setText(result);
    }

    @Override
    public String getPath() {
        if (ActivityCompat.checkSelfPermission(ExportTransactionsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ExportTransactionsActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    @Override
    public void setPath(String path) {
        this.filePathTextView.setText(path);
    }

    private void getItemsFromActivity() {
        this.yearEditText = findViewById(R.id.export_transactions_year_editText);
        this.resultTextView = findViewById(R.id.export_transactions_result_textView);
        this.filePathTextView = findViewById(R.id.export_transactions_file_path_textView);
        this.exportButton = findViewById(R.id.export_transactions_button);
    }

    private void initialize() {
        Locale mLocale = new Locale("pt", "BR");
        DBHelper dbHelper = new DBHelper(this);
        FileSystem fileSystem = new FileSystem();
        this.formatHelper = new FormatHelper(mLocale);
        this.repository = new SQLiteTransactionRepository(this, dbHelper);
        this.externalService = new FileExternalService(fileSystem, this.formatHelper);
        this.presenter = new ExportTransactionsPresenter(this, this.repository, this.externalService);
        this.presenter.initialize();
    }

    private void initializeListernsEvent() {
        this.exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDownloaderTransactionExternalFile();
            }
        });
    }
}
