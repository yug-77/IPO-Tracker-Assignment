package com.example.ipotracker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IpoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipo_detail);

        TextView title = findViewById(R.id.detailTitle);
        TextView details = findViewById(R.id.detailInfo);
        TextView history = findViewById(R.id.detailHistory);

        // Receive the data passed from the click
        IpoModel ipo = (IpoModel) getIntent().getSerializableExtra("IPO_DATA");

        if (ipo != null) {
            title.setText(ipo.getCompanyName());

            String info = "Category: " + ipo.getCategory() + "\n\n" +
                    "Issue Size: " + ipo.getIssueSize() + "\n\n" +
                    "Price Band: " + ipo.getPriceBand() + "\n\n" +
                    "Current GMP: " + ipo.getGmp() + "\n\n" +
                    "Retail Quota: " + ipo.getRetailQuota() + "\n\n" +
                    "Minimum Investment: " + ipo.getLotInvestment() + "\n\n" +
                    "Closing Date: " + ipo.getClosingDate();
            details.setText(info);

            history.setText(ipo.getHistory());
        }
    }
}