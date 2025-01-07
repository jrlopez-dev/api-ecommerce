package com.ecommerce.api.service;

import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusquedaTwitterService {
    private static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAA3sxwEAAAAAJFRJdE8QRHWKcJHibW70A4mJwcU%3Dm1jg6nwpBprMZ0laztxv1gpGFpp7OjqZvkEhbAlmgQTMzN54f3";

    public  String getTwitter(String keyword) {
        int maxResults = 10;
        try {
            String endpoint = String.format(
                    "https://api.twitter.com/2/tweets/search/recent?query=%s&max_results=%d&tweet.fields=text,created_at",
                    keyword,
                    maxResults
            );
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Authorization", "Bearer " + BEARER_TOKEN)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println(response.body());
                return response.body();
            } else {
                return "Error: "+ response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
