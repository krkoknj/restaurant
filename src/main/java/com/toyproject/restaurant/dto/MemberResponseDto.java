package com.toyproject.restaurant.dto;

import com.toyproject.restaurant.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String email;
    private String username;

    @Builder
    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}
