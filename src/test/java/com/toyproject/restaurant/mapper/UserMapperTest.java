package com.toyproject.restaurant.mapper;

import com.toyproject.restaurant.dto.UserDto;
import com.toyproject.restaurant.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("MyBaits 연결 테스트")
    public void test1() {
        UserDto user = UserDto.builder()
                .username("test1")
                .email("aaaaa@aaaaa.com")
                .build();

        User userEntity = User.toEntity(user);

        Integer insertId = userMapper.insertUsernameAndEmail(userEntity);

        List<User> allUsers = userMapper.findAllUsers();
        Integer selectId = allUsers.get(0).getId();

        System.out.println("allUsers = " + allUsers);
        assertEquals(insertId, selectId);
    }
}