package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {
    // Cities
    public static final String[] CITIES = {
            "Timisoara",
            "Bucharest"
    };

    // Streets for each city
    public static final Map<String, List<String>> CITY_STREETS = new HashMap<String, List<String>>() {{
        put("Timisoara", new ArrayList<String>() {{
            add("Bulevardul Mihai Viteazu");
            add("Strada Feldioara");
            add("Strada Cluj");
            add("Bulevardul Liviu Rebreanu");
        }});
        put("Bucharest", new ArrayList<String>() {{
            add("Bulevardul Mircea Eliade");
            add("Bulevardul Primaverii");
            add("Strada Racari");
            add("Calea Dorobantilor");
            add("Bulevardul Camil Ressu");
        }});
    }};

    // Problem Types
    public static final Map<Integer, String> PROBLEM_TYPES = new HashMap<Integer, String>() {{
        put(0, "Broken Road");
        put(1, "Falling Roof");
        put(2, "Pothole");
        put(3, "Water Leak");
        put(4, "Power Outage");
        put(5, "Street Light Not Working");
        put(6, "Illegal Dumping");
        put(7, "Noise Complaint");
        put(8, "Vandalism");
        put(9, "Traffic Signal Malfunction");
        put(10, "Blocked Drain");
        put(11, "Fallen Tree");
        put(12, "Graffiti");
        put(13, "Unsafe Building");
        put(14, "Animal Control");
        put(15, "Public Safety Concern");
        put(16, "Playground Damage");
        put(17, "Flooding");
        put(18, "Littering");
        put(19, "Other");
    }};

    // Convert City Name to ID
    public static int convertStringToCityId(String cityName) {
        for (int i = 0; i < CITIES.length; i++) {
            if (CITIES[i].equalsIgnoreCase(cityName)) {
                return i;
            }
        }
        return -1; // Invalid city
    }

    // Convert Problem Type to ID
    public static int convertStringToProblemTypeId(String problemType) {
        for (Map.Entry<Integer, String> entry : PROBLEM_TYPES.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(problemType)) {
                return entry.getKey();
            }
        }
        return -1; // Invalid problem type
    }

    // Get Streets for a City
    public static List<String> getStreetsForCity(String cityName) {
        return CITY_STREETS.getOrDefault(cityName, new ArrayList<>());
    }

    // Convert API Address to Readable String
    public static String convertApiAddressToString(String city, String street) {
        if (city == null || street == null) return "Unknown Address";
        return city + ", " + street;
    }
}
