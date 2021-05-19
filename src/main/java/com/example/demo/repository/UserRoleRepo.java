package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
