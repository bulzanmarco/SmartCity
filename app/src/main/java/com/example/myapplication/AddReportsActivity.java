package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.TableEntity;

import java.util.List;

public class AddReportsActivity extends BaseActivity {

    private Spinner problemSpinner, citySpinner, streetSpinner;
    private EditText descriptionEditText;
    private Button doneButton;

    private TableClient tableClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreports);


        // Initialize views
        problemSpinner = findViewById(R.id.problem_spinner);
        citySpinner = findViewById(R.id.city_spinner);
        streetSpinner = findViewById(R.id.street_spinner);
        descriptionEditText = findViewById(R.id.description);
        doneButton = findViewById(R.id.done_button);

        // Set up spinners
        setupSpinner(citySpinner, AppData.CITIES);
        setupSpinner(problemSpinner, AppData.PROBLEM_TYPES.values().toArray(new String[0]));

        // Update streets when a city is selected
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = citySpinner.getSelectedItem().toString();
                List<String> streets = AppData.getStreetsForCity(selectedCity);

                // Update the street spinner
                setupSpinner(streetSpinner, streets.toArray(new String[0]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when no city is selected
            }
        });

        // Set up button click listener
        doneButton.setOnClickListener(view -> handleAddReport());
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void handleAddReport() {
        String selectedProblem = problemSpinner.getSelectedItem().toString();
        String selectedCity = citySpinner.getSelectedItem().toString();
        String selectedStreet = streetSpinner.getSelectedItem().toString();
        String description = descriptionEditText.getText().toString().trim();

        // Validate input
        if (problemSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a problem type", Toast.LENGTH_SHORT).show();
            return;
        }
        if (citySpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a city", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(description)) {
            descriptionEditText.setError("Description is required");
            descriptionEditText.requestFocus();
            return;
        }

        // Prepare and submit the report
        submitReport(selectedProblem, selectedCity, selectedStreet, description);
    }

    private void submitReport(String problem, String city, String street, String description) {
        try {
            // Create a table entity for the report
            TableEntity reportEntity = new TableEntity(city, "Report-" + System.currentTimeMillis())
                    .addProperty("Problem", problem)
                    .addProperty("Street", street)
                    .addProperty("Description", description);

            // Insert the entity into the table
            tableClient.createEntity(reportEntity);

            Toast.makeText(this, "Report submitted successfully.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to submit report: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
