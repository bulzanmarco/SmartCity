package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;

public class BaseActivity extends AppCompatActivity {

    // Centralized TableClient instance
    protected static final String CONNECTION_STRING = "<YOUR_CONNECTION_STRING>"; // Replace with your Azure Table Storage connection string
    protected TableClient tableClient;
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Table Storage client
        tableClient = new TableClientBuilder()
                .connectionString(CONNECTION_STRING)
                .buildClient();

        // Set up the toolbar if it exists in the layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            // Set up buttons in the toolbar
            Button settingsButton = findViewById(R.id.settingsButton);
            Button signOutButton = findViewById(R.id.signOutButton);

            // Handle Settings Button Click
            if (settingsButton != null) {
                settingsButton.setOnClickListener(view -> {
                    // Navigate to Settings
                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);
                });
            }

            // Handle Sign Out Button Click
            if (signOutButton != null) {
                signOutButton.setOnClickListener(view -> {
                    // Perform sign-out operation
                    signOut();
                });
            }
        } else {
            throw new IllegalStateException("Toolbar with id 'toolbar' must be included in the child layout.");
        }
    }

    private void signOut() {
        // Clear user session or token
        getSharedPreferences("APP_PREFS", MODE_PRIVATE).edit().clear().apply();

        // Redirect to login screen
        Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
