package com.example.ipotracker; // Replace with your actual package name

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IpoAdapter extends RecyclerView.Adapter<IpoAdapter.IpoViewHolder> {

    private List<IpoModel> ipoList;

    public IpoAdapter(List<IpoModel> ipoList) {
        this.ipoList = ipoList;
    }

    @NonNull
    @Override
    public IpoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the layout for individual list items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ipo, parent, false);
        return new IpoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IpoViewHolder holder, int position) {
        // Binds data to the views
        IpoModel ipo = ipoList.get(position);
        holder.companyName.setText(ipo.getCompanyName());
        holder.priceBand.setText(ipo.getPriceBand());
        holder.closingDate.setText(ipo.getClosingDate());
    }

    @Override
    public int getItemCount() {
        return ipoList.size();
    }

    public static class IpoViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, priceBand, closingDate;

        public IpoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Links the Java variables to the XML layout IDs
            companyName = itemView.findViewById(R.id.textCompanyName);
            priceBand = itemView.findViewById(R.id.textPriceBand);
            closingDate = itemView.findViewById(R.id.textClosingDate);
        }
    }
}