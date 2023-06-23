package com.toyproject.restaurant.repository;

import com.toyproject.restaurant.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 유효성 검사 - 중복 체크
     * @param email 회원 이메일
     * @return
     */
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

}

