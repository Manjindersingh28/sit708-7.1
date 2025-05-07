package com.example.lost_and_found_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RecordViewHolder> {

    Context context;
    ArrayList<LostFoundItem> recordsList;

    public ItemAdapter(Context context, ArrayList<LostFoundItem> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new RecordViewHolder(view);
    }
// Bind data from LostFoundItem to the RecyclerView row views

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        LostFoundItem record = recordsList.get(position);
        holder.typeView.setText(record.getAdvertType());
        holder.nameView.setText(record.getItemName());
        holder.locationView.setText(record.getItemLocation());
// Open detail screen when item is clicked

        holder.itemView.setOnClickListener(v -> {
            Intent detailIntent = new Intent(context, ItemDetailActivity.class);
            detailIntent.putExtra("id", record.getRecordId());
            detailIntent.putExtra("type", record.getAdvertType());
            detailIntent.putExtra("name", record.getItemName());
            detailIntent.putExtra("phone", record.getContactPhone());
            detailIntent.putExtra("description", record.getDetails());
            detailIntent.putExtra("date", record.getRecordDate());
            detailIntent.putExtra("location", record.getItemLocation());
            context.startActivity(detailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {

        TextView typeView, nameView, locationView;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            typeView = itemView.findViewById(R.id.textItemType);
            nameView = itemView.findViewById(R.id.textItemName);
            locationView = itemView.findViewById(R.id.textItemLocation);
        }
    }
}
