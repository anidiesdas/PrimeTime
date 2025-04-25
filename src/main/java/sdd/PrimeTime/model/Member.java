package sdd.PrimeTime.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

//    @OneToMany(mappedBy = "member")
//    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private Set<MemberMovie> memberMovies;

    public Set<MemberMovie> getMemberMovies() {
        return memberMovies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberMovies(Set<MemberMovie> memberMovies) {
        this.memberMovies = memberMovies;
    }

}
