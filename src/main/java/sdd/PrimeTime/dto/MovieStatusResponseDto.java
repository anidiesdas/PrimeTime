package sdd.PrimeTime.dto;

import java.time.LocalDate;

/**
 * Created by Ani Nguyen on 28/05/2025.
 * Author: An Nguyen
 */
public class MovieStatusResponseDto {
    private String status;
    private LocalDate watchDate;

    public MovieStatusResponseDto(String status, LocalDate watchDate) {
        this.status = status;
        this.watchDate = watchDate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }
}

