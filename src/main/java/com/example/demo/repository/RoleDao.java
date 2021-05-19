package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AppRole;

public interface RoleDao extends JpaRepository<AppRole, Integer> {
	

}
