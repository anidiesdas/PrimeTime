package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 17/05/2025.
 * Author: An Nguyen
 */
public class RatingDto {
    private Long memberId;
    private Double rating;

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
}

