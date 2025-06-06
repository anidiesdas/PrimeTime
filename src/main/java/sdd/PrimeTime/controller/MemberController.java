package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.repository.MemberRepository;

import java.util.List;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/members")
@CrossOrigin(origins = {"http://localhost:5173", "https://primetimefrontend.onrender.com"})
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("")
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(m -> new MemberDto(m.getId(), m.getName()))
                .toList();
    }


}
