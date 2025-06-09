package sdd.PrimeTime.dto;

import sdd.PrimeTime.model.Platform;

import java.time.LocalDate;

/**
 * Created by Ani Nguyen on 28/05/2025.
 * Author: An Nguyen
 */
public class MovieStatusResponseDto {
    private String status;
    private LocalDate watchDate;
    private Platform platform;

    public MovieStatusResponseDto(String status, LocalDate watchDate, Platform platform) {
        this.status = status;
        this.watchDate = watchDate;
        this.platform = platform;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public Platform getPlatform() { return platform; }
}

