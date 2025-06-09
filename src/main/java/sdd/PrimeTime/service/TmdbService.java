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

    private final RestTemplate restTemplate;

    public TmdbService() {
        this.restTemplate = new RestTemplate();
    }

    public String searchMovie(String query) {
        String url = buildUrl("/search/movie", "query=" + query);
        return restTemplate.getForObject(url, String.class);
    }

    public String getPopularMovies() {
        String url = buildUrl("/movie/popular");
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieDetails(int id) {
        String url = buildUrl("/movie/" + id);
        return restTemplate.getForObject(url, String.class);
    }

    private String buildUrl(String endpoint, String... additionalParams) {
        StringBuilder url = new StringBuilder(apiUrl)
                .append(endpoint)
                .append("?api_key=")
                .append(apiKey);

        for (String param : additionalParams) {
            url.append("&").append(param);
        }

        return url.toString();
    }
}