package com.example.myapplication.Model;
import com.example.myapplication.Domain.Adress;

import android.location.Address;

import com.example.myapplication.Domain.ProblemStatus;
import com.example.myapplication.Domain.ProblemType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetReportsResponse {

        private String id;
        private String reporterEmail;
        private String description;
       private Adress problemAddress;
        private Date reportDate;
        private int status;
        private int problem;




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


        public Adress getProblemAddress() {
            return problemAddress;
        }

        public void setProblemAddress(Adress problemAddress) {
            this.problemAddress = problemAddress;
        }

        // Getter și Setter pentru reportDate
        public Date getReportDate() {
            return reportDate;
        }

        public void setReportDate(Date reportDate) {
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
    @Override
    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", description='" + description + '\'' +
                ", street=" + problemAddress.getStreet() +
                ", city=" + problemAddress.getCity() +
                ", reportDate=" + (reportDate != null ? reportDate.toString() : "null") +
                ", status=" + status +
                ", problem=" + problem +
                '}';
    }

}

