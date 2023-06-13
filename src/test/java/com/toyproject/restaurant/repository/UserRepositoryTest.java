package com.toyproject.restaurant.repository;

import com.toyproject.restaurant.dto.UserDto;
import com.toyproject.restaurant.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("db 저장 테스트")
    void insertTest() {
        UserDto userDto = UserDto.builder()
                .username("test1")
                .role("admin")
                .password("test")
                .build();

        User entity = User.toEntity(userDto);

        User savedUser = userRepository.save(entity);

        System.out.println("savedUser = " + savedUser);
    }
}