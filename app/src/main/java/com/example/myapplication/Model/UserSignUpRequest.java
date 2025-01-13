package com.example.myapplication.Model;

public class UserSignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private int residenceCity;

    // Getter pentru firstName
    public String getFirstName() {
        return firstName;
    }

    // Setter pentru firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter pentru lastName
    public String getLastName() {
        return lastName;
    }

    // Setter pentru lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter pentru email
    public String getEmail() {
        return email;
    }

    // Setter pentru email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pentru phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter pentru phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter pentru password
    public String getPassword() {
        return password;
    }

    // Setter pentru password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter pentru residenceCity
    public int getResidenceCity() {
        return residenceCity;
    }

    // Setter pentru residenceCity
    public void setResidenceCity(int residenceCity) {
        this.residenceCity = residenceCity;
    }
    public UserSignUpRequest(String firstName, String lastName, String email, String phoneNumber, String password, int residenceCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.residenceCity = residenceCity;
    }
}
