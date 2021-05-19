package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;

public interface CartRepo extends JpaRepository<Cart, String>{

	List<Cart> findAllByUser(User user);




}
