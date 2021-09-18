package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.AppRoleCustom;
import com.example.demo.services.CartServices;
import com.example.demo.services.UserServices;

@Controller
public class UserController {
	
	@Autowired
	CartServices cart;
	@Autowired
	AppRoleCustom appRoleCustom;
	@Autowired
	UserServices us;
	@RequestMapping("/dangnhap")
	public String dangNhap(Model req) {
		req.addAttribute("listCart", cart.listCartItems());
		req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "login";
	 }
	@RequestMapping(value = { "/checkRole" })
	public String checkRole(Principal user) throws SQLException, IOException {
		if (us.getDefaultRole(user.getName()).equalsIgnoreCase("Admin")) {
			System.out.println("admin");
			return "redirect:/admin/index";
		}
		else if (us.getDefaultRole(user.getName()).equalsIgnoreCase("User")) {
			System.out.println("User");
			return "redirect:/";}
		return "redirect:/";
	}
	
	
}
