package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.TableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportViewHolder> {

    private ArrayList<Report> reports;
    private Context context;
    private TableClient tableClient;

    public ReportsAdapter(Context context) {
        this.context = context;
        this.reports = new ArrayList<>();

        // Initialize Table Storage client
        tableClient = new TableClientBuilder()
                .connectionString(BaseActivity.CONNECTION_STRING) // Use shared connection string from BaseActivity
                .tableName("Reports") // Replace with your table name
                .buildClient();
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report = reports.get(position);
        holder.problemTextView.setText(report.getProblem());
        holder.addressTextView.setText(report.getProblemAddress());
        holder.emailTextView.setText(report.getReporterEmail());
        holder.dateTextView.setText(report.getReportDate());
        holder.statusTextView.setText(report.getStatusString());
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public void updateData(List<Report> newReports) {
        this.reports.clear();
        this.reports.addAll(newReports);
        notifyDataSetChanged();
    }

    public void fetchReportsFromAzure() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<Report> fetchedReports = new ArrayList<>();
                for (TableEntity entity : tableClient.listEntities()) {
                    Report report = Report.fromTableEntity(entity);
                    fetchedReports.add(report);
                }

                // Update data on the main thread
                ((BaseActivity) context).runOnUiThread(() -> updateData(fetchedReports));
            } catch (Exception e) {
                e.printStackTrace();
                ((BaseActivity) context).runOnUiThread(() ->
                        ((BaseActivity) context).showToast("Error fetching reports: " + e.getMessage()));
            }
        });
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView problemTextView, addressTextView, emailTextView, dateTextView, statusTextView;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            problemTextView = itemView.findViewById(R.id.problem_textview);
            addressTextView = itemView.findViewById(R.id.address_textview);
            emailTextView = itemView.findViewById(R.id.email_textview);
            dateTextView = itemView.findViewById(R.id.date_textview);
            statusTextView = itemView.findViewById(R.id.status_textview);
        }
    }
}
