package com.example.myapplication.Model;

import com.example.myapplication.Domain.CityName;

public class UserLogInResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String token;
    private int pointsNumber;
    private CityName residenceCity;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(int pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    public CityName getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(CityName residenceCity) {
        this.residenceCity = residenceCity;
    }
}
