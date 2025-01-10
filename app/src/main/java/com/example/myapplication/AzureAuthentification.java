package com.example.myapplication;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.TableEntity;

import java.util.Map;

public class AzureAuthentification {

    private static final String CONNECTION_STRING = "<YOUR_CONNECTION_STRING>"; // Replace with your Azure Table Storage connection string
    private static final String TABLE_NAME = "Users"; // Replace with your users table name

    private static TableClient tableClient;

    static {
        tableClient = new TableClientBuilder()
                .connectionString(CONNECTION_STRING)
                .tableName(TABLE_NAME)
                .buildClient();
    }

    public static void authenticate(String email, String password, AzureAuthenticationCallback callback) {
        try {
            TableEntity userEntity = tableClient.getEntity("User", email); // Assuming 'User' is the partition key
            Map<String, Object> properties = userEntity.getProperties();

            if (properties.get("Password").equals(password)) {
                String token = "SIMULATED_TOKEN_" + System.currentTimeMillis(); // Generate a simulated token
                callback.onSuccess(token);
            } else {
                callback.onFailure("Invalid credentials");
            }
        } catch (Exception e) {
            callback.onFailure("Authentication failed: " + e.getMessage());
        }
    }

    public interface AzureAuthenticationCallback {
        void onSuccess(String token);
        void onFailure(String errorMessage);
    }
}
