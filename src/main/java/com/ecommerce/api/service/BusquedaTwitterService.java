package com.ecommerce.api.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class BusquedaTwitterService {
    @Value("${tokentwitter}")
    private String token;
    @Value("${urlendpointtwitter}")
    private String urlendpointtwitter;


    String BEARER_TOKEN = token;

    public  String getTwitter(String keyword) {
        System.out.println("TOKEN: "+token);
        System.out.println("URL: "+urlendpointtwitter);
        int maxResults = 10;
        try {
            String endpoint = String.format(
                    urlendpointtwitter,
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
