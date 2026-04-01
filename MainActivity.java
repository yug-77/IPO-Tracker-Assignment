package com.example.ipotracker; // Make sure this matches your package name!

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ipoRecyclerView;
    private List<IpoModel> ipoList;
    private Button btnViewWatchlist;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        ipoRecyclerView = findViewById(R.id.ipoRecyclerView);
        ipoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnViewWatchlist = findViewById(R.id.btnViewWatchlist);

        // Initialize Database
        dbHelper = new DatabaseHelper(this);

        // Load list data
        loadIpoData();
        ipoRecyclerView.setAdapter(new IpoAdapter(ipoList));

        // Button Click Listener for the Database Pop-Up
        btnViewWatchlist.setOnClickListener(v -> {
            String data = dbHelper.getWatchlistData();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("My Short-Term Targets")
                    .setMessage(data)
                    .setPositiveButton("Close", null)
                    .show();
        });
    }

    private void loadIpoData() {
        ipoList = new ArrayList<>();
        ipoList.add(new IpoModel("TechNova Solutions", "₹120 - ₹125", "April 15, 2026"));
        ipoList.add(new IpoModel("GreenEnergy Corp", "₹450 - ₹475", "April 18, 2026"));
        ipoList.add(new IpoModel("SecureBank Ltd", "₹890 - ₹910", "April 22, 2026"));
    }
}