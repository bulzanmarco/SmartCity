package com.example.myapplication.Model;

import android.location.Address;

import com.example.myapplication.Domain.Adress;
import com.example.myapplication.Domain.ProblemType;

public class CreateReportRequest {
    private String reporterEmail;
    private String description;
    private Adress problemAddress;
    private int problem;

    // Constructor
    public CreateReportRequest(String reporterEmail, String description, Adress problemAddress, int problem) {
        this.reporterEmail = reporterEmail;
        this.description = description;
        this.problemAddress = problemAddress;
        this.problem = problem;
    }

    // Getter for reporterEmail
    public String getReporterEmail() {
        return reporterEmail;
    }

    // Setter for reporterEmail
    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for problemAddress
    public Adress getProblemAddress() {
        return problemAddress;
    }

    // Setter for problemAddress
    public void setProblemAddress(Adress problemAddress) {
        this.problemAddress = problemAddress;
    }

    // Getter for problem
    public int getProblem() {
        return problem;
    }

    // Setter for problem
    public void setProblem(int problem) {
        this.problem = problem;
    }
}
