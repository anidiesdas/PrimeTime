package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.dto.MemberWatchedMoviesStatsDto;
import sdd.PrimeTime.service.MemberService;

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
    private MemberService memberService;

    @GetMapping("")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        try {
            List<MemberDto> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/watched-counts")
    public ResponseEntity<List<MemberWatchedMoviesStatsDto>> getMemberWatchedMoviesStats() {
        try {
            List<MemberWatchedMoviesStatsDto> stats = memberService.getMemberWatchedMoviesStats();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}