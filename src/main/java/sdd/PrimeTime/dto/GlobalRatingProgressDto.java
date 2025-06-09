package sdd.PrimeTime.dto;

import java.time.LocalDate;

/**
 * Created by Ani Nguyen on 09/06/2025.
 * Author: An Nguyen
 */
public class GlobalRatingProgressDto {

    private LocalDate date;
    private double averageOfDay;
    private double cumulativeAverage;

    public GlobalRatingProgressDto(LocalDate date, double averageOfDay, double cumulativeAverage) {
        this.date = date;
        this.averageOfDay = averageOfDay;
        this.cumulativeAverage = cumulativeAverage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAverageOfDay() {
        return averageOfDay;
    }

    public void setAverageOfDay(double averageOfDay) {
        this.averageOfDay = averageOfDay;
    }

    public double getCumulativeAverage() {
        return cumulativeAverage;
    }

    public void setCumulativeAverage(double cumulativeAverage) {
        this.cumulativeAverage = cumulativeAverage;
    }

}