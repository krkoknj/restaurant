package com.toyproject.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MainController {
    @GetMapping(value = "/home")
    public String main(Model model) {
        log.info("========================");

        log.info("=======================2=");
        return "/page/home";
    }
}
