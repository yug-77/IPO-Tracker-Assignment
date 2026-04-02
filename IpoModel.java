package com.example.ipotracker;

import java.io.Serializable;

public class IpoModel implements Serializable {
    private String companyName, priceBand, closingDate, gmp, issueSize, category, history, retailQuota, lotInvestment, status;

    public IpoModel(String companyName, String priceBand, String closingDate, String gmp,
                    String issueSize, String category, String history,
                    String retailQuota, String lotInvestment, String status) {
        this.companyName = companyName;
        this.priceBand = priceBand;
        this.closingDate = closingDate;
        this.gmp = gmp;
        this.issueSize = issueSize;
        this.category = category;
        this.history = history;
        this.retailQuota = retailQuota;
        this.lotInvestment = lotInvestment;
        this.status = status;
    }

    public String getCompanyName() { return companyName; }
    public String getPriceBand() { return priceBand; }
    public String getClosingDate() { return closingDate; }
    public String getGmp() { return gmp; }
    public String getIssueSize() { return issueSize; }
    public String getCategory() { return category; }
    public String getHistory() { return history; }
    public String getRetailQuota() { return retailQuota; }
    public String getLotInvestment() { return lotInvestment; }
    public String getStatus() { return status; }
}
