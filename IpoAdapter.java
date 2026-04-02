package com.example.ipotracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IpoAdapter extends RecyclerView.Adapter<IpoAdapter.IpoViewHolder> {

    private Context context;
    private List<IpoModel> ipoList;

    public IpoAdapter(Context context, List<IpoModel> ipoList) {
        this.context = context;
        this.ipoList = ipoList;
    }

    @NonNull
    @Override
    public IpoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ipo, parent, false);
        return new IpoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IpoViewHolder holder, int position) {
        IpoModel ipo = ipoList.get(position);

        holder.companyName.setText(ipo.getCompanyName());
        holder.closingDate.setText("Offer Date: " + ipo.getClosingDate());
        holder.priceBand.setText(ipo.getPriceBand());
        holder.gmp.setText(ipo.getGmp());
        holder.issueSize.setText(ipo.getIssueSize());

        // Safely extract just the share number (e.g., getting "26" from "1 Lot = 26 Shares")
        try {
            String[] parts = ipo.getLotInvestment().split(" ");
            holder.lotSize.setText(parts[3]);
        } catch (Exception e) {
            holder.lotSize.setText("N/A");
        }

        // Make the card clickable to open the detail screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, IpoDetailActivity.class);
            intent.putExtra("IPO_DATA", ipo);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ipoList.size();
    }

    public static class IpoViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, closingDate, priceBand, gmp, issueSize, lotSize;

        public IpoViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.textCompanyName);
            closingDate = itemView.findViewById(R.id.textClosingDate);
            priceBand = itemView.findViewById(R.id.textPriceBand);
            gmp = itemView.findViewById(R.id.textGmp);
            issueSize = itemView.findViewById(R.id.textIssueSize);
            lotSize = itemView.findViewById(R.id.textLotSize);
        }
    }
}
