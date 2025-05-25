package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 17/05/2025.
 * Author: An Nguyen
 */
public class RatingDto {
    private Long movieId;
    private Long memberId;
    private String memberName;
    private Double rating;

    public Long getMovieId() { return movieId; }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getMemberName() {return memberName; }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}

