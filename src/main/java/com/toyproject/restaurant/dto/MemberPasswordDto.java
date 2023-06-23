package com.toyproject.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPasswordDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "기존 비밀번호를 입력해주세요.")
    @Size(min = 2, max = 15, message = "기존 패스워드는 2 ~ 15자 사이로 입력해주세요.")
    private String password;

    @NotBlank(message = "바꿀 비밀번호를 입력해주세요.")
    @Size(min = 2, max = 15, message = "바꿀 패스워드는 2 ~ 15자 사이로 입력해주세요.")
    private String changePassword;
}
