package sdd.PrimeTime.dto;

import java.util.List;

/**
 * Created by Ani Nguyen on 10/06/2025.
 * Author: An Nguyen
 */
public class MonthlyRecapDto {

    private String monthName;
    private int totalMoviesWatched;
    private int totalRuntime;
    private List<String> topGenres;
    private String bestRatedMovie;
    private String worstRatedMovie;

    public MonthlyRecapDto(String monthName, int totalMoviesWatched, int totalRuntime,
                           List<String> topGenres, String bestRatedMovie, String worstRatedMovie) {
        this.monthName = monthName;
        this.totalMoviesWatched = totalMoviesWatched;
        this.totalRuntime = totalRuntime;
        this.topGenres = topGenres;
        this.bestRatedMovie = bestRatedMovie;
        this.worstRatedMovie = worstRatedMovie;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getTotalMoviesWatched() {
        return totalMoviesWatched;
    }

    public void setTotalMoviesWatched(int totalMoviesWatched) {
        this.totalMoviesWatched = totalMoviesWatched;
    }

    public int getTotalRuntime() {
        return totalRuntime;
    }

    public void setTotalRuntime(int totalRuntime) {
        this.totalRuntime = totalRuntime;
    }

    public List<String> getTopGenres() {
        return topGenres;
    }

    public void setTopGenres(List<String> topGenres) {
        this.topGenres = topGenres;
    }

    public String getBestRatedMovie() {
        return bestRatedMovie;
    }

    public void setBestRatedMovie(String bestRatedMovie) {
        this.bestRatedMovie = bestRatedMovie;
    }

    public String getWorstRatedMovie() {
        return worstRatedMovie;
    }

    public void setWorstRatedMovie(String worstRatedMovie) {
        this.worstRatedMovie = worstRatedMovie;
    }
}