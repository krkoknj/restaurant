package com.toyproject.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.Map;

public interface GlobalService {
    /**
     * 중복 검사 및 유효성 검사 에러 메세지 핸들링
     * @param errors
     * @param model
     */
    void messageHandling(Errors errors, Model model);

    Map<String, String> validateHandling(Errors errors);
}
