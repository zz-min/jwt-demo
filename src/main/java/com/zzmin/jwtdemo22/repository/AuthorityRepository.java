package com.zzmin.jwtdemo22.repository;

import com.zzmin.jwtdemo22.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}