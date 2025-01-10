package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.azure.data.tables.models.TableEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpActivity extends BaseActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, phoneNumberEditText, passwordEditText;
    private Spinner residenceCitySpinner;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        residenceCitySpinner = findViewById(R.id.city_spinner);
        signUpButton = findViewById(R.id.signUpButton);

        // Populate spinner with city options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residenceCitySpinner.setAdapter(adapter);

        // Set click listener for the sign-up button
        signUpButton.setOnClickListener(view -> handleSignUp());
    }

    private void handleSignUp() {
        // Get input values
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String residenceCity = residenceCitySpinner.getSelectedItem().toString();

        // Validate input
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("First name is required");
            firstNameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Last name is required");
            lastNameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumberEditText.setError("Phone number is required");
            phoneNumberEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }

        // Register the user
        registerUser(firstName, lastName, email, phoneNumber, password, residenceCity);
    }

    private void registerUser(String firstName, String lastName, String email, String phoneNumber, String password, String residenceCity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Check if a user with the same email already exists
                TableEntity existingUser = tableClient.getEntity("User", email);

                if (existingUser != null) {
                    runOnUiThread(() -> Toast.makeText(SignUpActivity.this, "An account with this email already exists.", Toast.LENGTH_SHORT).show());
                    return;
                }
            } catch (Exception ignored) {
                // If the user doesn't exist, proceed with registration
            }

            try {
                // Create a new user entity
                TableEntity userEntity = new TableEntity("User", email)
                        .addProperty("FirstName", firstName)
                        .addProperty("LastName", lastName)
                        .addProperty("PhoneNumber", phoneNumber)
                        .addProperty("Password", password)
                        .addProperty("ResidenceCity", residenceCity);

                // Insert the entity into the Users table
                tableClient.createEntity(userEntity);

                runOnUiThread(() -> {
                    Toast.makeText(SignUpActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, ReportsActivity.class);
                    startActivity(intent);
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(SignUpActivity.this, "Sign-up failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
