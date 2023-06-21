package com.toyproject.restaurant.vaildator;

import com.toyproject.restaurant.dto.MemberRequestDto;
import com.toyproject.restaurant.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberRequestDto> {

    private final MemberRepository memberRepository;
    @Override
    protected void doValidate(MemberRequestDto dto, Errors errors) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
