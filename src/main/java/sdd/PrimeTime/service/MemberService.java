package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.dto.MemberWatchedMoviesStatsDto;
import sdd.PrimeTime.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ani Nguyen on 13/05/2025.
 * Author: An Nguyen
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(m -> new MemberDto(m.getId(), m.getName()))
                .toList();
    }

    public List<MemberWatchedMoviesStatsDto> getMemberWatchedMoviesStats() {
        List<Object[]> results = memberRepository.findMemberWatchedMoviesStats();

        return results.stream()
                .map(row -> new MemberWatchedMoviesStatsDto(
                        (String) row[0],        // memberName
                        (Long) row[1],          // totalWatchedMovies
                        (Long) row[2]           // totalWatchedMinutes
                ))
                .collect(Collectors.toList());
    }
}
