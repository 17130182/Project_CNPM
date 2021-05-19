package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Review;
import com.example.demo.services.ProductService;
import com.example.demo.services.ReviewService;

@RequestMapping("/admin")
@Controller
public class ReviewController_Admin {
	
	@Autowired
	ProductService productService;
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/danhsachreview")
	public String review(Model req) {
		List<Review> reviewlist = reviewService.getAll();
		req.addAttribute("list", reviewlist);
		return "admin/review_list";
	}
	
	@GetMapping("/xoareview")
	public String xoaReview(Model req, @RequestParam("id") String id) {
		int dele = Integer.parseInt(id);
		reviewService.delete(dele);
		return "redirect:/admin/danhsachreview";
	}

}
