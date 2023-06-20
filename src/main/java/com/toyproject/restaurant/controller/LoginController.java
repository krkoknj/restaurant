package com.toyproject.restaurant.controller;

import com.toyproject.restaurant.dto.UserDto;
import com.toyproject.restaurant.entity.User;
import com.toyproject.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/user")
    public String user(Model model) {
        return "page/user/calendar";
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "/page/join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "/page/login";
    }

    @PostMapping("/joinProc")
    public String joinProc(UserDto userDto) {
        String rawPassword = userDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userDto.setPassword(encPassword);
        userDto.setRole("ROLE_USER");
        User user = User.toEntity(userDto);
        userRepository.save(user);
        return "redirect:/home";
    }

}
