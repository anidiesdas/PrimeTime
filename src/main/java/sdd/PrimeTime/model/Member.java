package sdd.PrimeTime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    @NotNull
    private Long id;

    @NotNull
    String name;

    @OneToMany(mappedBy = "member")
    private Set<MemberMovie> memberMovies;

    // Getter + Setter
    public Set<MemberMovie> getMemberMovies() {
        return memberMovies;
    }

    public void setMemberMovies(Set<MemberMovie> memberMovies) {
        this.memberMovies = memberMovies;
    }

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

}
