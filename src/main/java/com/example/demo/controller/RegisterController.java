package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.AppUserDAO;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.CartServices;
import com.example.demo.services.ProductService;
import com.example.demo.services.SendMail;
import com.example.demo.services.UserServices;
import com.example.demo.tools.Validate;
import com.example.demo.utils.EncrytedPasswordUtils;

@Controller
public class RegisterController {
	
	
	
	private User user;
	private String code; // luu code lai
	
	
	@Autowired
	SendMail sendMail;
	
	@Autowired
	CartServices cart;

	@Autowired
	UserServices userServices;
	
	
	@GetMapping("/dangky")
	public String dangKy(Model req) {
		User user = new User();
		req.addAttribute("regis", user);
		req.addAttribute("listCart", cart.listCartItems());
		req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "register";
		 
	 }
	
	// 1. Xac thuc tai khoan bang cach gui email
		@PostMapping("/validate")
		public String sendEmailSignup(@ModelAttribute("regis") User userRegis, Model model)
				throws SQLException {
			userRegis.setPassword(EncrytedPasswordUtils.encrytePassword(userRegis.getPassword()));
			user = userRegis;
			code = Validate.getCodeNumber();
			System.out.println("Thuc hien gui email dang ky tai khoan!");
			System.out.println("Send to email: " + userRegis.getEmail());
			sendMail.sendSimpleMessage(userRegis.getEmail(), "NongSanShop - Finishing the registration!", "Ma xac nhan cua ban la: "+ code);
			model.addAttribute("email", userRegis.getEmail());
			return "validateCode";
		}
		// 2. Kiem tra ma xac thuc
		@PostMapping("/checkValidateCode")
		public String checkEmailSignup(@RequestParam("code") String code, Model model) throws SQLException {
			if (code.equals(this.code)) {
				return "redirect:/register";
			}
			// Neu nhap sai validate code
			model.addAttribute("message", "The code is invalid!");
			model.addAttribute("email", user.getEmail());
			return "validateCode";
		}
		// 3. Thuc hien dang ky tai khoan, luu vao database
		@RequestMapping(value = { "/register" })
		public String signupAccount(Model model) throws SQLException {
			
			userServices.createNewUser(user);
			System.out.println("Dang ky tai khoan thanh cong!");
			return "redirect:/";
		}
}
