package com.company.internship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.internship.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}