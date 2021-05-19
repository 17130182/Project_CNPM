package com.example.demo.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.services.CartServices;
import com.example.demo.services.PaymentService;
import com.example.demo.services.UserServices;

@Controller
public class CheckOutController {
	@Autowired
	CartServices cart;
	@Autowired
	UserServices us;
	@Autowired
	PaymentService paymentService;
	DecimalFormat df = new DecimalFormat("#.000");
	 @GetMapping("/checkout")
	 public String thanhToan(Model req, Principal user) {
		 User u=us.getUser(user.getName());
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 req.addAttribute("user", u);
		 return "checkout";
	 }
	 
	 @RequestMapping("/goToPay")
	 public String tienhanhthanhtoan(Model req, Principal user, @RequestParam("address") String address, @RequestParam("note") String note) throws SQLException {
		 paymentService.createNewBill(user.getName(), address, note);
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "thanhtoanthanhcong";
	 }
}
