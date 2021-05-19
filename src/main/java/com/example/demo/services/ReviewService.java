package com.example.demo.services;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Bill_Detail;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.models.CartItem;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.RepoCustom;
import com.example.demo.repository.ReviewRepo;
import com.example.demo.repository.ProductRepo;

@Service
public class ReviewService {

	@Autowired
	ReviewRepo review;
	@Autowired
	RepoCustom repoCustom;
	@Autowired
	ProductService productservice;
	@Autowired
	ProductRepo  repoProduct;
	
	
	public List<Review> getAll() {
		return review.findAll();
	}
	public void delete(int id) {
		review.deleteById(id);
	}
	public Review getId(int id) {
		return review.getOne(id);
	}
	public String getProduct(int id) {
		return review.getOne(id).getProduct().getName();
	}
	public void createNewReview(int idProduct, String name, String email, String content) {
		Review re = new Review(productservice.getId(idProduct), name, email, content, new Timestamp(System.currentTimeMillis()));
		repoCustom.addReview(re);
	}
	public List<Review> getReviewByProduct(int idProduct) {
		return review.findAllByProduct(repoProduct.getOne(idProduct));
	}
	

}

