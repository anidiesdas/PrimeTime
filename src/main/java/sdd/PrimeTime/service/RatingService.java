package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sdd.PrimeTime.repository.RatingRepository;
import sdd.PrimeTime.dto.GlobalRatingProgressDto;
import sdd.PrimeTime.dto.MemberAverageRatingDto;
import sdd.PrimeTime.dto.RatingDto;
import sdd.PrimeTime.model.Rating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ani Nguyen on 13/05/2025.
 * Author: An Nguyen
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingDto> getRatingsForMovie(Long movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);
        return ratings.stream().map(r -> {
            RatingDto dto = new RatingDto();
            dto.setMovieId(r.getMovie().getId());
            dto.setMemberId(r.getMember().getId());
            dto.setRating(r.getRating());
            dto.setMemberName(r.getMember().getName());
            return dto;
        }).toList();
    }

    public Double getAverageRating() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .filter(r -> r.getRating() != null)
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);
    }

    public List<String> getTopRatedMovies() {
        return ratingRepository.findTopRatedMovies(PageRequest.of(0, 5));
    }

    public List<String> getWorstRatedMovies() {
        return ratingRepository.findWorstRatedMovies(PageRequest.of(0, 5));
    }

    public List<MemberAverageRatingDto> getAverageRatingsByMember() {
        return ratingRepository.findAverageRatingsGroupedByMember();
    }

    public List<GlobalRatingProgressDto> getGlobalRatingProgress() {
        List<Object[]> rows = ratingRepository.getAverageRatingPerDay();
        List<GlobalRatingProgressDto> result = new ArrayList<>();

        double sum = 0;
        int count = 0;

        for (Object[] row : rows) {
            if (row[0] == null || row[1] == null) {
                continue;
            }

            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            double avgOfDay = ((Number) row[1]).doubleValue();

            sum += avgOfDay;
            count++;

            double cumulativeAvg = sum / count;
            double roundedCumulativeAvg = Math.round(cumulativeAvg * 100.0) / 100.0;

            result.add(new GlobalRatingProgressDto(date, avgOfDay, roundedCumulativeAvg));
        }

        return result;
    }
}
