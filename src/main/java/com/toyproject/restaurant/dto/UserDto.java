package com.toyproject.restaurant.dto;

import com.toyproject.restaurant.entity.User;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String email;

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .email(entity.getEmail())
                .build();
    }
}
