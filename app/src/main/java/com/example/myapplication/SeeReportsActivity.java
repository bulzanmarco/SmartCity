package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azure.data.tables.models.TableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeeReportsActivity extends BaseActivity {

    private Spinner citySpinner;
    private RecyclerView reportsRecyclerView;
    private ReportsAdapter reportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seereports);

        // Initialize views
        citySpinner = findViewById(R.id.city_spinner);
        reportsRecyclerView = findViewById(R.id.reports_recyclerview);

        // Initialize RecyclerView
        reportsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportsAdapter = new ReportsAdapter(this);
        reportsRecyclerView.setAdapter(reportsAdapter);

        // Set up city spinner
        setupCitySpinner();

        // Fetch all reports from Azure Table Storage
        fetchReportsFromAzure();
    }

    private void setupCitySpinner() {
        String[] cities = {"Select a city", "Timisoara", "Bucharest"};
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedCity = cities[position];
                    filterReportsByCity(selectedCity);
                } else {
                    reportsAdapter.updateData(new ArrayList<>()); // Clear RecyclerView if no city is selected
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void fetchReportsFromAzure() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<Report> fetchedReports = new ArrayList<>();
                for (TableEntity entity : tableClient.listEntities()) {
                    Report report = Report.fromTableEntity(entity);
                    fetchedReports.add(report);
                }

                runOnUiThread(() -> {
                    // Update the adapter with fetched reports
                    reportsAdapter.updateData(fetchedReports);
                });
            } catch (Exception e) {
                runOnUiThread(() -> showToast("Error fetching reports: " + e.getMessage()));
            }
        });
    }

    private void filterReportsByCity(@NonNull String city) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<Report> filteredReports = new ArrayList<>();
                for (TableEntity entity : tableClient.listEntities()) {
                    Report report = Report.fromTableEntity(entity);
                    if (report.getProblemAddress().contains(city)) {
                        filteredReports.add(report);
                    }
                }

                runOnUiThread(() -> {
                    if (filteredReports.isEmpty()) {
                        showToast("No reports found for " + city);
                    }
                    reportsAdapter.updateData(filteredReports);
                });
            } catch (Exception e) {
                runOnUiThread(() -> showToast("Error filtering reports: " + e.getMessage()));
            }
        });
    }
}
