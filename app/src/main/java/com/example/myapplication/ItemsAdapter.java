package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.ProblemStatus;
import com.example.myapplication.Domain.ProblemType;
import com.example.myapplication.Domain.Streets;
import com.example.myapplication.Model.GetReportsResponse;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private final List<GetReportsResponse> items;
    private final Context context;
    public ItemsAdapter(List<GetReportsResponse> items, Context context) {

        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {


        holder.bind("Problem: "+ProblemType.values()[items.get(position).getProblem()]+", Street: "+Streets.values()[items.get(position).getProblemAddress().getStreet()]);
        Log.d("1",items.get(position).toString());
        GetReportsResponse getReportsResponse=items.get(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra("Problem",String.valueOf(ProblemType.values()[items.get(holder.getAdapterPosition()).getProblem()]));
                intent.putExtra("Email",String.valueOf(getReportsResponse.getReporterEmail()));
                intent.putExtra("Street",String.valueOf(Streets.values()[items.get(holder.getAdapterPosition()).getProblemAddress().getStreet()]));
                intent.putExtra("Report_Date",String.valueOf(getReportsResponse.getReportDate()));
                intent.putExtra("Status",String.valueOf(ProblemStatus.values()[items.get(holder.getAdapterPosition()).getStatus()]));
                intent.putExtra("Description",String.valueOf(getReportsResponse.getDescription()));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            textView.setText(item);
        }
    }
}
