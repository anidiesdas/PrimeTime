package sdd.PrimeTime.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private int genreId;
    private int runningTime;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String tags;

    @Enumerated(EnumType.STRING)
    private WatchlistStatus status;

//    @OneToMany(mappedBy = "movie")
//    private List<Rating> ratings = new ArrayList<>();

    public Set<MemberMovie> getParticipantMovies() {
        return participantMovies;
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

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
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

    public void setParticipantMovies(Set<MemberMovie> participantMovies) {
        this.participantMovies = participantMovies;
    }

    @OneToMany(mappedBy = "movie")
    private Set<MemberMovie> participantMovies;
}
