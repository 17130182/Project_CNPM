package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Cart;
import com.example.demo.entity.User;

public interface BillRepo  extends JpaRepository<Bill, Integer>{
	
	Bill findAllById(int id);
}
