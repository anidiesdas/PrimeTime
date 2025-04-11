package sdd.PrimeTime.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private int runningTime;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String tags;

    @Enumerated(EnumType.STRING)
    private WatchlistStatus status;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    public Movie() {}

    public Movie(String title, String genre, int runningTime, LocalDate releaseDate, Platform platform,
                 String tags, WatchlistStatus status) {
        this.title = title;
        this.genre = genre;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.platform = platform;
        this.tags = tags;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public WatchlistStatus getStatus() {
        return status;
    }

    public void setStatus(WatchlistStatus status) {
        this.status = status;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
