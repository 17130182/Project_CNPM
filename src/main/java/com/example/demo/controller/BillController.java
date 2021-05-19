package com.example.demo.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Product;
import com.example.demo.services.BillService;

@RequestMapping("/admin")
@Controller
public class BillController {

	@Autowired
	BillService billse;
	

	@GetMapping("/danhsachdonhang")
	public String donHang(Model req) {
		List<Bill> billlist = billse.getAll();
		req.addAttribute("list", billlist);
		return "admin/order_list";
	}
	@GetMapping("/xoadonhang")
	public String xoaUser(Model req, @RequestParam("id") String id) {
		int dele = Integer.parseInt(id);
		billse.delete(dele);
		return "redirect:/admin/danhsachdonhang";
	}

}
