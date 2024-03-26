package com.zzmin.jwtdemo22.repository;

import com.zzmin.jwtdemo22.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 extends하면 findAll, save 등의 메소드 사용가능
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
    //username으로 User정보 와 Authorities권한정보 같이 반환
}