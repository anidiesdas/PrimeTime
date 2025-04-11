package sdd.PrimeTime.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Rating> ratings;

    public Member() {}

    public Member(String name) {
        this.name = name;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
