package com.toyproject.restaurant.service.impl;

import com.toyproject.restaurant.dto.MemberPasswordDto;
import com.toyproject.restaurant.dto.MemberRequestDto;
import com.toyproject.restaurant.dto.MemberResponseDto;
import com.toyproject.restaurant.dto.MemberUsernameDto;
import com.toyproject.restaurant.entity.Member;
import com.toyproject.restaurant.entity.domain.Role;
import com.toyproject.restaurant.exception.PasswordNotMatchException;
import com.toyproject.restaurant.repository.MemberRepository;
import com.toyproject.restaurant.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Long join(MemberRequestDto memberRequestDto) {
        memberRequestDto.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));

        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .username(memberRequestDto.getUsername())
                .password(memberRequestDto.getPassword())
                .role(Role.ROLE_USER)
                .build();

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true) // 트랙잭션을 커밋하더라도 양속성 컨텍스트가 플러시 되지 않아 엔티티 등록, 수정, 삭제 동작 X
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError fieldError: errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", fieldError.getField());
            validatorResult.put(validKeyName, fieldError.getDefaultMessage());
        }
        return validatorResult;
    }

    @Override
    public List<MemberResponseDto> findMembers() {
        List<Member> all = memberRepository.findAll();
        List<MemberResponseDto> members = new ArrayList<>();

        for (Member member : all) {
            MemberResponseDto build = MemberResponseDto.builder()
                    .member(member)
                    .build();
            members.add(build);
        }
        return members;
    }

    @Override
    public MemberResponseDto findMember(String username) {
        Member byUsername = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username이 존재하지 않습니다."));

        MemberResponseDto responseDto = MemberResponseDto.builder()
                .member(byUsername)
                .build();

        return responseDto;
    }

    @Override
    public Long updateMemberUsername(MemberUsernameDto dto) {
        Member byUsername = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email 이 존재하지 않습니다."));

        byUsername.updateUsername(dto.getUsername());
        memberRepository.save(byUsername);
        return byUsername.getId();
    }

    @Override
    public Long updateMemberPassword(MemberPasswordDto dto) {
        Member byUsername = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email 이 존재하지 않습니다."));

        boolean matches = passwordEncoder.matches(dto.getPassword(), byUsername.getPassword());

        if (matches) {
            String encodePassword = passwordEncoder.encode(dto.getChangePassword());
            byUsername.updatePassword(encodePassword);
            memberRepository.save(byUsername);
        } else {
            throw new PasswordNotMatchException("패스워드가 일치하지 않습니다.");
        }
        return byUsername.getId();
    }

}
