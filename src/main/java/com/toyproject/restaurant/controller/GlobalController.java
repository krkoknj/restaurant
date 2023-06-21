package com.toyproject.restaurant.controller;

import com.toyproject.restaurant.dto.MemberRequestDto;
import com.toyproject.restaurant.service.MemberService;
import com.toyproject.restaurant.vaildator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GlobalController {

    private final MemberService memberService;
    private final CheckEmailValidator checkEmailValidator;
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    /**
     * 로그인 페이지
     *
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    /**
     * 홈 페이지
     *
     * @return 홈 페이지
     */
    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "members/memberForm";
    }

    /**
     * 회원 가입 post
     *
     * @param memberRequestDto 회원 정보
     * @return 홈페이지
     */
    @PostMapping("/signup")
    public String createMember(@Valid MemberRequestDto memberRequestDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원 가입 실패 시 입력 데이터 유지
            model.addAttribute("dto", memberRequestDto);

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 회원 가입 페이지로 리턴
            return "members/memberForm";
        }

        // 유효성 검사를 통과하지 못한 필드와 메세지 핸들링


        Long memberId = memberService.join(memberRequestDto);
        return "redirect:/";
    }
}
