package sdd.PrimeTime.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sdd.PrimeTime.model.Platform;
import sdd.PrimeTime.model.WatchlistStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ani Nguyen on 16/05/2025.
 * Author: An Nguyen
 */
public class MovieDto {
    private Long id;
    private String title;
    private int runningTime;
    private LocalDate releaseDate;
    private List<String> genres;

    @Enumerated(EnumType.STRING)
    private WatchlistStatus status;
    private LocalDate watchDate;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private List<String> tags;

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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public WatchlistStatus getStatus() {
        return status;
    }

    public void setStatus(WatchlistStatus status) {
        this.status = status;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
