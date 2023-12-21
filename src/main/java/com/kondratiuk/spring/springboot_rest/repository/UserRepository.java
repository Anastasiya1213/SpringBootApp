package com.kondratiuk.spring.springboot_rest.repository;

import com.kondratiuk.spring.springboot_rest.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    boolean existsByEmail(String email);
    Users findByEmail(String email);
}
