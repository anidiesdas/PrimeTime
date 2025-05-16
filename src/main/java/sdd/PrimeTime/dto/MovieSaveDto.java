package sdd.PrimeTime.dto;

import java.util.List;

/**
 * Created by Ani Nguyen on 16/05/2025.
 * Author: An Nguyen
 */
public class MovieSaveDto {

    public Long id;
    public String title;
    public String genre;
    public int runningTime;

    public String releaseDate;
    public String watchDate;

    public String platform;
    public String status;

    public List<String> tags;

    public List<MemberRatingDto> users;

    public static class MemberRatingDto {
        public Long id;
        public String name;
        public String rating;
    }
}



