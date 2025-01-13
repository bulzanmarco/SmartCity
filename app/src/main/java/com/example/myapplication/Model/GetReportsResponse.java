package com.example.myapplication.Model;
import com.example.myapplication.Domain.Adress;

import android.location.Address;

import com.example.myapplication.Domain.ProblemStatus;
import com.example.myapplication.Domain.ProblemType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetReportsResponse {

        private String id;
        private String reporterEmail;
        private String description;
        private int street;
        private int city;
        private LocalDateTime reportDate;
        private int status;
        private int problem;


        public GetReportsResponse(Report report) {
            this.id = report.getRowKey();
            this.reporterEmail = report.getReporterEmail();
            this.description = report.getDescription();
            
            this.city = report.getPartitionKey();
            this.street = report.getStreet();


            this.reportDate = report.getReportDate();
            this.status = status;
            this.problem = problem;
        }

        // Getter și Setter pentru id
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Getter și Setter pentru reporterEmail
        public String getReporterEmail() {
            return reporterEmail;
        }

        public void setReporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
        }

        // Getter și Setter pentru description
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        // Getter și Setter pentru street
        public int getStreet() {
            return street;
        }

        public void setStreet(int street) {
            this.street = street;
        }

        // Getter și Setter pentru city
        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        // Getter și Setter pentru reportDate
        public LocalDateTime getReportDate() {
            return reportDate;
        }

        public void setReportDate(LocalDateTime reportDate) {
            this.reportDate = reportDate;
        }

        // Getter și Setter pentru status
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        // Getter și Setter pentru problem
        public int getProblem() {
            return problem;
        }

        public void setProblem(int problem) {
            this.problem = problem;
        }
    }

