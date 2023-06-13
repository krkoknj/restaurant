package com.toyproject.restaurant.entity;

import com.toyproject.restaurant.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String role;

    public static User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

}
