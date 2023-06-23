package com.toyproject.restaurant.service;

import com.toyproject.restaurant.dto.MemberPasswordDto;
import com.toyproject.restaurant.dto.MemberRequestDto;
import com.toyproject.restaurant.dto.MemberResponseDto;
import com.toyproject.restaurant.dto.MemberUsernameDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

@Service
public interface MemberService {

    Long join(MemberRequestDto memberRequestDto);

    Map<String, String> validateHandling(Errors errors);

    List<MemberResponseDto> findMembers();

    MemberResponseDto findMember(String username);

    Long updateMemberUsername(MemberUsernameDto dto);

    Long updateMemberPassword(MemberPasswordDto dto);
}
