package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 12/06/2025.
 * Author: An Nguyen
 */
public class MemberWatchedMoviesStatsDto {

    private String memberName;
    private Long totalWatchedMovies;
    private Long totalWatchedMinutes;

    // Constructor
    public MemberWatchedMoviesStatsDto(String memberName, Long totalWatchedMovies, Long totalWatchedMinutes) {
        this.memberName = memberName;
        this.totalWatchedMovies = totalWatchedMovies;
        this.totalWatchedMinutes = totalWatchedMinutes;
    }

    // Getters und Setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getTotalWatchedMovies() {
        return totalWatchedMovies;
    }

    public void setTotalWatchedMovies(Long totalWatchedMovies) {
        this.totalWatchedMovies = totalWatchedMovies;
    }

    public Long getTotalWatchedMinutes() {
        return totalWatchedMinutes;
    }

    public void setTotalWatchedMinutes(Long totalWatchedMinutes) {
        this.totalWatchedMinutes = totalWatchedMinutes;
    }
}