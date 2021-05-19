package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Bill_Detail;
import com.example.demo.services.BillDetailService;

@RequestMapping("/admin")
@Controller
public class BillDetailController_Admin {
	@Autowired
	BillDetailService billDetail;
	
	@GetMapping("/danhsachchitietdonhang")
	public String chiTietDonHang(Model req) {
		List<Bill_Detail> billlist = billDetail.getAll();
		req.addAttribute("list", billlist);
		return "admin/order_list_detail";
	}
	@GetMapping("/xoachitietdonhang")
	public String xoaChiTietDonHang(Model req, @RequestParam("id") String id) {
		int dele = Integer.parseInt(id);
		billDetail.delete(dele);
		return "redirect:/admin/danhsachchitietdonhang";
	}
	

}
