package com.toyproject.restaurant.vaildator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked") // 컴파일러가 경고하는 내용 제외 - (unchecked : 검증되지 않은 연산자 관련 경고)
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException re) {
            log.error("중복 검증 에러 : ", re);
            throw re;
        }
    }

    protected abstract void doValidate(final T dto, Errors errors);
}
