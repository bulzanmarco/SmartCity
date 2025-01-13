package com.example.myapplication.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adress {
    public int city;
    private int street;

    // Constructor
    public Adress(int city, int street) {
        this.city = city;
        this.street = street;
    }

    // Getter for city
    public int getCity() {
        return city;
    }

    // Setter for city
    public void setCity(int city) {
        this.city = city;
    }

    // Getter for street
    public int getStreet() {
        return street;
    }

    // Setter for street
    public void setStreet(int street) {
        this.street = street;
    }
    @Override
    public String toString()
    {
        return city+" "+street+" ";
    }
}
