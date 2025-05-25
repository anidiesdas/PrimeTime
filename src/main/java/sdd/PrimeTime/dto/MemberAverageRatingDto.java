package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 25/05/2025.
 * Author: An Nguyen
 */
public class MemberAverageRatingDto {
    private String memberName;
    private Double averageRating;

    public MemberAverageRatingDto(String memberName, Double averageRating) {
        this.memberName = memberName;
        this.averageRating = averageRating;
    }

    public String getMemberName() { return memberName; }

    public void setMemberName(String memberName) { this.memberName = memberName; }

    public Double getAverageRating() { return averageRating; }

    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
