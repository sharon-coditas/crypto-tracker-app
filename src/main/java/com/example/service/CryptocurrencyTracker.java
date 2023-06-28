package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CryptocurrencyTracker {

    private static final String API_BASE_URL = "https://api.coingecko.com/api/v3";

    public static void main(String[] args) {
        String cryptocurrency1 = "bitcoin";
        String cryptocurrency2 = "ethereum";

        displayCryptocurrencyData(cryptocurrency1);
        displayCryptocurrencyData(cryptocurrency2);
        compareCryptocurrencies(cryptocurrency1, cryptocurrency2);
    }

    private static void displayCryptocurrencyData(String cryptocurrency) {
        try {
            URL url = new URL(API_BASE_URL + "/coins/markets?vs_currency=usd&ids=" + cryptocurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse and display the cryptocurrency data
                System.out.println("Cryptocurrency: " + cryptocurrency);
                System.out.println("Data: " + response.toString());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Failed to fetch cryptocurrency data. Error code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Error occurred while fetching cryptocurrency data: " + e.getMessage());
        }
    }

    private static void compareCryptocurrencies(String cryptocurrency1, String cryptocurrency2) {
        try {
            URL url = new URL(API_BASE_URL + "/coins/markets?vs_currency=usd&ids=" + cryptocurrency1 + "," + cryptocurrency2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

           
                System.out.println("Comparison:");
                System.out.println("Cryptocurrency 1: " + cryptocurrency1);
                System.out.println("Cryptocurrency 2: " + cryptocurrency2);
                System.out.println("Data: " + response.toString());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Failed to fetch cryptocurrency data. Error code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Error occurred while fetching cryptocurrency data: " + e.getMessage());
        }
    }
}
