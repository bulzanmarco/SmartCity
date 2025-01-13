package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Domain.Streets;

import java.util.Date;

public class ItemDetailsActivity extends AppCompatActivity {




    @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_seereport);

            TextView emailTextView;
            TextView descriptionTextView;
            TextView streetTextView;
            TextView reportDateTextView;
            TextView problemTextView;
            TextView statusTextView;

            emailTextView=findViewById(R.id.emailTextView);
            descriptionTextView=findViewById(R.id.descriptionTextView);
            streetTextView=findViewById(R.id.streetTextView);
            reportDateTextView=findViewById(R.id.reportDateTextView);
            problemTextView=findViewById(R.id.problemTextView);
            statusTextView=findViewById(R.id.statusTextView);

            Bundle extras = getIntent().getExtras();

            if(extras!=null)
            {
                String reporterEmail=extras.getString("Email");
                String reporterDescription=extras.getString("Description");
                String reporterStreet=extras.getString("Street");
                String reporterDate=extras.getString("Report_Date");
                String reporterProblem=extras.getString("Problem");
                String reporterStatus=extras.getString("Status");

                emailTextView.setText(reporterEmail);
                descriptionTextView.setText(reporterDescription);
                streetTextView.setText(reporterStreet);
                reportDateTextView.setText(reporterDate);
                problemTextView.setText(reporterProblem);
                statusTextView.setText(reporterStatus);

            }


        }
    }

