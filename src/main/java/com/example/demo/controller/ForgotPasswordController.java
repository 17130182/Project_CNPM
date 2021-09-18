package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.CartServices;

@Controller
public class ForgotPasswordController {
	@Autowired
	CartServices cart;
	
	@RequestMapping("/quenmatkhau")
	public String quenmatkhau(Model req) {
		req.addAttribute("listCart", cart.listCartItems());
		req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "forgot-password";
	 }
}
