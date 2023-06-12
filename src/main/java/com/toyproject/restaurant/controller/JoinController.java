package com.toyproject.restaurant.controller;

import com.toyproject.restaurant.dto.UserDto;
import com.toyproject.restaurant.entity.User;
import com.toyproject.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @GetMapping("")
    public String join(Model model) {
        return "/page/join";
    }

    @PostMapping("/joinProc")
    public String joinProc(UserDto userDto) {
        String rawPassword = userDto.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
        userDto.setRole("ROLE_USER");
        User user = User.toEntity(userDto);
        userRepository.save(user);
        return "redirect:/";
    }
}
