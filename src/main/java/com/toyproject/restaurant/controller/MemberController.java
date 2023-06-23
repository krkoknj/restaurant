package com.toyproject.restaurant.controller;

import com.toyproject.restaurant.dto.MemberPasswordDto;
import com.toyproject.restaurant.dto.MemberRequestDto;
import com.toyproject.restaurant.dto.MemberResponseDto;
import com.toyproject.restaurant.dto.MemberUsernameDto;
import com.toyproject.restaurant.exception.PasswordNotMatchException;
import com.toyproject.restaurant.service.GlobalService;
import com.toyproject.restaurant.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final GlobalService globalService;

    @GetMapping("/update/username")
    public String updateUsernameForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDto member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/member/updateUsername";
    }

    @PostMapping("/update/username")
    public String updateUsername(@Valid MemberUsernameDto dto,
                                 Errors errors,
                                 Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("member", dto);
            globalService.messageHandling(errors, model);
            return "/member/updateUsername";
        }

        memberService.updateMemberUsername(dto);
        return "redirect:/member/info";
    }

    @GetMapping("/info")
    public String memberInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDto member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/member/info";
    }

    @GetMapping("/update/password")
    public String updateMemberPasswordForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDto member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/member/updatePassword";
    }

    @PostMapping("/update/password")
    public String updateMemberPassword(@Valid MemberPasswordDto dto,
                                       Errors errors,
                                       Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("member", dto);
            globalService.messageHandling(errors, model);
            return "/member/updatePassword";
        }

        try {
            memberService.updateMemberPassword(dto);
        } catch (PasswordNotMatchException e) {
            String message = e.getMessage();
            model.addAttribute("errorMessage", message);
            model.addAttribute("member", dto);
            return "/member/updatePassword";
        }
        return "redirect:/member/info";
    }
}
