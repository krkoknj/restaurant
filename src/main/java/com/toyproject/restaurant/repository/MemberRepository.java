package com.toyproject.restaurant.repository;

import com.toyproject.restaurant.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

