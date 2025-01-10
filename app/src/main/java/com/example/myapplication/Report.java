package com.example.myapplication;

import com.azure.data.tables.models.TableEntity;

import java.util.HashMap;
import java.util.Map;

public class Report {
    private final TableEntity tableEntity;

    // Constructor
    public Report(String partitionKey, String rowKey, String problem, String problemAddress, String reporterEmail, String reportDate, String description, int status) {
        tableEntity = new TableEntity(partitionKey, rowKey);
        tableEntity.addProperty("Problem", problem);
        tableEntity.addProperty("ProblemAddress", problemAddress);
        tableEntity.addProperty("ReporterEmail", reporterEmail);
        tableEntity.addProperty("ReportDate", reportDate);
        tableEntity.addProperty("Description", description);
        tableEntity.addProperty("Status", status);
    }

    // Getters and Setters
    public String getPartitionKey() {
        return tableEntity.getPartitionKey();
    }

    public String getRowKey() {
        return tableEntity.getRowKey();
    }

    public String getProblem() {
        return (String) tableEntity.getProperties().get("Problem");
    }

    public void setProblem(String problem) {
        tableEntity.addProperty("Problem", problem);
    }

    public String getProblemAddress() {
        return (String) tableEntity.getProperties().get("ProblemAddress");
    }

    public void setProblemAddress(String problemAddress) {
        tableEntity.addProperty("ProblemAddress", problemAddress);
    }

    public String getReporterEmail() {
        return (String) tableEntity.getProperties().get("ReporterEmail");
    }

    public void setReporterEmail(String reporterEmail) {
        tableEntity.addProperty("ReporterEmail", reporterEmail);
    }

    public String getReportDate() {
        return (String) tableEntity.getProperties().get("ReportDate");
    }

    public void setReportDate(String reportDate) {
        tableEntity.addProperty("ReportDate", reportDate);
    }

    public String getDescription() {
        return (String) tableEntity.getProperties().get("Description");
    }

    public void setDescription(String description) {
        tableEntity.addProperty("Description", description);
    }

    public int getStatus() {
        return (int) tableEntity.getProperties().get("Status");
    }

    public void setStatus(int status) {
        tableEntity.addProperty("Status", status);
    }

    public String getStatusString() {
        switch (getStatus()) {
            case 0:
                return "New";
            case 1:
                return "Fixing";
            case 2:
                return "Fixed";
            default:
                return "Unknown";
        }
    }

    // Convert to TableEntity for Azure Table Storage operations
    public TableEntity toTableEntity() {
        return tableEntity;
    }

    // Static factory to create Report from TableEntity
    public static Report fromTableEntity(TableEntity entity) {
        return new Report(
                entity.getPartitionKey(),
                entity.getRowKey(),
                (String) entity.getProperties().get("Problem"),
                (String) entity.getProperties().get("ProblemAddress"),
                (String) entity.getProperties().get("ReporterEmail"),
                (String) entity.getProperties().get("ReportDate"),
                (String) entity.getProperties().get("Description"),
                (int) entity.getProperties().get("Status")
        );
    }
}
