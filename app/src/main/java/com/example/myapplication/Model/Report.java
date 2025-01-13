package com.example.myapplication.Model;

import com.example.myapplication.Domain.Adress;


import android.location.Address;
import android.os.Build;

import com.example.myapplication.Domain.ProblemType;
import com.example.myapplication.Domain.ProblemStatus;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {

    private String reporterEmail;     // Email of the person reporting the issue
    private String description;       // Description of the problem
    private int street;               // Refers to a street (enum index or ordinal value)
    private int partitionKey;         // Refers to a city (enum index or ordinal value)
    private int status;               // Refers to the status of the report (enum index or ordinal value)
    private String problem;           // A string representation of the problem type
    private boolean isRedeemed;       // Flag to check if the report is redeemed
    private LocalDateTime reportDate; // The date and time the report was created
    private String rowKey;            // Unique identifier for the report
    private String timestamp;         // Timestamp (optional for additional metadata)
    private String eTag;              // ETag (optional for versioning or concurrency)

    // Default constructor
    public Report() {}

    // Copy constructor
    public Report(Report reportToCopy) {
        this.reporterEmail = reportToCopy.reporterEmail;
        this.description = reportToCopy.description;
        this.street = reportToCopy.street;
        this.partitionKey = reportToCopy.partitionKey;
        this.status = reportToCopy.status;
        this.problem = reportToCopy.problem;
        this.isRedeemed = reportToCopy.isRedeemed;
        this.reportDate = reportToCopy.reportDate;
        this.rowKey = reportToCopy.rowKey;
        this.timestamp = reportToCopy.timestamp;
        this.eTag = reportToCopy.eTag;
    }

    // Parameterized constructor
    public Report(String reporterEmail, ProblemType problemType, String description, int partitionKey, int street) {
        this.reporterEmail = reporterEmail;
        this.problem = problemType.toString();
        this.description = description;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.reportDate = LocalDateTime.now();
        }
        this.status = ProblemStatus.Fixed.ordinal(); // Reference enum by its ordinal value
        this.street = street;
        this.partitionKey = partitionKey;
        this.isRedeemed = false;

        // Generate a unique RowKey based on the reporter's email and report date
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.rowKey = reporterEmail + "_" + reportDate.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
        }
    }

    // Getters and setters
    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStreet() {
        return street;
    }

    public void setStreet(int street) {
        this.street = street;
    }

    public int getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(int partitionKey) {
        this.partitionKey = partitionKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public boolean isRedeemed() {
        return isRedeemed;
    }

    public void setRedeemed(boolean redeemed) {
        isRedeemed = redeemed;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}


