package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set up toolbar with a title
        setupToolbar("Settings");
    }

    private void setupToolbar(String title) {
        // Find the toolbar and set it as the action bar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            // Set toolbar title
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back navigation
            }
        } else {
            throw new IllegalStateException("Toolbar with id 'toolbar' must be included in the layout.");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle back navigation
        onBackPressed();
        return true;
    }
}
