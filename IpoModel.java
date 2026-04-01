package com.example.ipotracker; // Replace with your actual package name

public class IpoModel {
    private String companyName;
    private String priceBand;
    private String closingDate;

    public IpoModel(String companyName, String priceBand, String closingDate) {
        this.companyName = companyName;
        this.priceBand = priceBand;
        this.closingDate = closingDate;
    }

    public String getCompanyName() { return companyName; }
    public String getPriceBand() { return priceBand; }
    public String getClosingDate() { return closingDate; }
}