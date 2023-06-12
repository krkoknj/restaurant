package com.toyproject.restaurant.repository;

import com.toyproject.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
