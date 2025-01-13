package com.example.myapplication.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adress {
    public CityName city;
    private Streets street;

    // Constructor
    public Adress(CityName city, Streets street) {
        this.city = city;
        this.street = street;
    }

    // Getter for city
    public CityName getCity() {
        return city;
    }

    // Setter for city
    public void setCity(CityName city) {
        this.city = city;
    }

    // Getter for street
    public Streets getStreet() {
        return street;
    }

    // Setter for street
    public void setStreet(Streets street) {
        this.street = street;
    }
}
