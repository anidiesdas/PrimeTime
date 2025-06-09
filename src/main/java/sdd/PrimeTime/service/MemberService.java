package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdd.PrimeTime.dto.MemberDto;
import sdd.PrimeTime.repository.MemberRepository;

import java.util.List;

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
}
