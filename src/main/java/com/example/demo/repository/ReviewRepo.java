package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Catalog;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
	
	List<Review> findAllByProduct(Product one);
}
