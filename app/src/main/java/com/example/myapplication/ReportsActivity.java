package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        // Initialize buttons
        Button addReportsButton = findViewById(R.id.add_reports);
        Button seeReportsButton = findViewById(R.id.see_reports);

        // Set click listener for "Add Reports" button
        addReportsButton.setOnClickListener(view -> navigateToAddReports());

        // Set click listener for "See All Reports" button
        seeReportsButton.setOnClickListener(view -> fetchAndNavigateToSeeReports());
    }

    private void navigateToAddReports() {
        Intent intent = new Intent(ReportsActivity.this, AddReportsActivity.class);
        startActivity(intent);
    }

    private void fetchAndNavigateToSeeReports() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Check if there are any reports in the table
                boolean hasReports = tableClient.listEntities().iterator().hasNext();

                if (hasReports) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(ReportsActivity.this, SeeReportsActivity.class);
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(
                            ReportsActivity.this,
                            "No reports available. Please add a report first.",
                            Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(
                        ReportsActivity.this,
                        "Error fetching reports: " + e.getMessage(),
                        Toast.LENGTH_LONG).show());
            }
        });
    }
}
