package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@Service
public class TmdbService {

    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

//    public String searchMovie(String query) {
//        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, query);
//        return restTemplate.getForObject(url, String.class);
//    }

    public String searchMovie(String query) {
        // Debugging: Zeige die übergebene query
        System.out.println("Suchbegriff: " + query);

        // Baue die URL mit der query
        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, query);

        // Debugging: Zeige die gebaute URL
        System.out.println("Anfrage URL: " + url);

        // Sende die Anfrage an TMDB und erhalte die Antwort als String
        String response = restTemplate.getForObject(url, String.class);

        // Debugging: Zeige die Antwort von TMDB
        System.out.println("Antwort von TMDB: " + response);

        return response;  // Gebe die rohe Antwort als String zurück
    }

}
