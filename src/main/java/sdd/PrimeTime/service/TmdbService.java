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

    // HTTP-Anfragen
    private final RestTemplate restTemplate = new RestTemplate();

    public String searchMovie(String query) {
        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, query);
        return restTemplate.getForObject(url, String.class);
    }

    public String getPopularMovies() {
        String url = String.format("%s/movie/popular?api_key=%s", apiUrl, apiKey);
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieDetails(int id) {
        String url = String.format("%s/movie/%d?api_key=%s", apiUrl, id, apiKey);
        return restTemplate.getForObject(url, String.class);
    }

}
