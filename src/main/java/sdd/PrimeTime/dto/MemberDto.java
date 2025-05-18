package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 18/05/2025.
 * Author: An Nguyen
 */
public class MemberDto {
    private Long id;
    private String name;

    public MemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
