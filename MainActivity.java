package com.example.ipotracker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.content.res.ColorStateList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ipoRecyclerView;
    private IpoAdapter adapter;
    private List<IpoModel> fullIpoList;
    private List<IpoModel> displayList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipoRecyclerView = findViewById(R.id.ipoRecyclerView);
        ipoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnCurrentUpcoming = findViewById(R.id.btnCurrentUpcoming);
        Button btnListed = findViewById(R.id.btnListed);

        dbHelper = new DatabaseHelper(this);
        loadIpoData();

        displayList = new ArrayList<>();
        adapter = new IpoAdapter(this, displayList);
        ipoRecyclerView.setAdapter(adapter);
        filterCurrentUpcoming(); // Default view

        // --- TOP TOGGLE BUTTONS ---
        btnCurrentUpcoming.setOnClickListener(v -> {
            filterCurrentUpcoming();
            btnCurrentUpcoming.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1E1E1E")));
            btnCurrentUpcoming.setTextColor(Color.parseColor("#FFFFFF"));
            btnListed.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5C4BBA")));
            btnListed.setTextColor(Color.parseColor("#FFFFFF"));
        });

        btnListed.setOnClickListener(v -> {
            filterListed();
            btnListed.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1E1E1E")));
            btnListed.setTextColor(Color.parseColor("#FFFFFF"));
            btnCurrentUpcoming.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5C4BBA")));
            btnCurrentUpcoming.setTextColor(Color.parseColor("#FFFFFF"));
        });


        // --- BOTTOM NAVIGATION TABS (UPGRADED!) ---

        // 1. Mainboard Tab: Filters the list to only show MAINBOARD IPOs
        findViewById(R.id.tabMainboard).setOnClickListener(v -> filterByCategory("MAINBOARD"));

        // 2. SME Tab: Filters the list to only show SME IPOs
        findViewById(R.id.tabSme).setOnClickListener(v -> filterByCategory("SME"));

        // 3. Bids Tab: Shows a big pop-up
        findViewById(R.id.tabBids).setOnClickListener(v -> showPopUp("My Bids", "You have not applied for any IPOs yet. Your active bids will appear here."));

        // 4. Allotment Tab: Shows a big pop-up
        findViewById(R.id.tabAllotment).setOnClickListener(v -> showPopUp("Allotment Status", "Enter your PAN Card number on the next screen to check if you received shares. (Feature coming soon)"));

        // 5. News Tab: Shows a big pop-up
        findViewById(R.id.tabNews).setOnClickListener(v -> showPopUp("Market News", "• Sensex hits new all-time high.\n• SEBI announces new T+0 settlement rules.\n• 4 new SME IPOs launching next week."));


        // --- TOP ICONS (UPGRADED!) ---

        // Search Icon
        findViewById(R.id.ivSearch).setOnClickListener(v -> showPopUp("Search", "Search functionality is currently disabled in this prototype version."));

        // Profile/Menu Icons
        findViewById(R.id.ivMenu).setOnClickListener(v -> showPopUp("Settings", "App Version 1.0\nTheme: Dark Mode\nNotifications: Enabled"));

        findViewById(R.id.ivProfile).setOnClickListener(v -> {
            // Let's actually use your Database here for the profile!
            String data = dbHelper.getWatchlistData();
            showPopUp("My Trade Targets (Database)", data);
        });
    }

    // --- HELPER METHODS ---

    // A method to easily create big pop-up boxes
    private void showPopUp(String title, String message) {
        new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Close", null)
                .show();
    }

    // Method to filter by Mainboard or SME
    private void filterByCategory(String category) {
        displayList.clear();
        for (IpoModel ipo : fullIpoList) {
            if (ipo.getCategory().equals(category)) {
                displayList.add(ipo);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void filterCurrentUpcoming() {
        displayList.clear();
        for (IpoModel ipo : fullIpoList) {
            if (ipo.getStatus().equals("CURRENT") || ipo.getStatus().equals("UPCOMING")) {
                displayList.add(ipo);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void filterListed() {
        displayList.clear();
        for (IpoModel ipo : fullIpoList) {
            if (ipo.getStatus().equals("LISTED")) {
                displayList.add(ipo);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void loadIpoData() {
        fullIpoList = new ArrayList<>();
        // CURRENT IPOs
        fullIpoList.add(new IpoModel("Bharti Hexacom", "₹542 - ₹570", "Today", "₹65", "₹4275 Cr", "MAINBOARD", "Telecom services provider in Rajasthan and North East.", "10%", "1 Lot = 26 Shares @ ₹14,820", "CURRENT"));
        fullIpoList.add(new IpoModel("Alita Apparel", "₹89 - ₹91", "Tomorrow", "₹12", "₹45 Cr", "SME", "Garment manufacturer.", "50%", "1 Lot = 1200 Shares @ ₹1,09,200", "CURRENT"));

        // UPCOMING IPOs
        fullIpoList.add(new IpoModel("TechNova Solutions", "₹120 - ₹125", "April 15, 2026", "₹45", "₹500 Cr", "MAINBOARD", "IT consulting firm.", "35%", "1 Lot = 100 Shares @ ₹12,500", "UPCOMING"));
        fullIpoList.add(new IpoModel("GreenEnergy Corp", "₹450 - ₹475", "April 18, 2026", "₹12", "₹1200 Cr", "MAINBOARD", "Solar panel manufacturer.", "35%", "1 Lot = 30 Shares @ ₹14,250", "UPCOMING"));

        // LISTED IPOs
        fullIpoList.add(new IpoModel("Tata Technologies", "₹500", "Listed Nov 2023", "₹350", "₹3042 Cr", "MAINBOARD", "Global engineering services.", "35%", "1 Lot = 30 Shares", "LISTED"));
        fullIpoList.add(new IpoModel("IREDA", "₹32", "Listed Nov 2023", "₹15", "₹2150 Cr", "MAINBOARD", "Renewable energy financing.", "35%", "1 Lot = 460 Shares", "LISTED"));
        fullIpoList.add(new IpoModel("DOMS Industries", "₹790", "Listed Dec 2023", "₹500", "₹1200 Cr", "MAINBOARD", "Stationery products.", "10%", "1 Lot = 18 Shares", "LISTED"));
        fullIpoList.add(new IpoModel("Inox CVA", "₹660", "Listed Dec 2023", "₹400", "₹1459 Cr", "MAINBOARD", "Cryogenic equipment.", "35%", "1 Lot = 22 Shares", "LISTED"));
        fullIpoList.add(new IpoModel("Motisons Jewellers", "₹55", "Listed Dec 2023", "₹80", "₹151 Cr", "MAINBOARD", "Jewelry retail.", "35%", "1 Lot = 250 Shares", "LISTED"));
        fullIpoList.add(new IpoModel("BLS E-Services", "₹135", "Listed Feb 2024", "₹160", "₹310 Cr", "MAINBOARD", "Tech-enabled services.", "10%", "1 Lot = 108 Shares", "LISTED"));
    }
}
