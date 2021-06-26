package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.UserRole;
import com.example.demo.services.UserRoleService;

@RequestMapping({ "/admin" })
@Controller
public class UserRoleController_Admin {
	@Autowired
	UserRoleService urService;

	public UserRoleController_Admin() {
	}

	@GetMapping({ "/danhsachphanquyen" })
	public String userRole(Model req) {
		List<UserRole> urList = urService.getAll();
		req.addAttribute("listuserrole", urList);
		return "admin/userRole_list";
	}

	@RequestMapping("/themquyen")
	public String themUserRole(Model req) {
		UserRole ur = new UserRole();
		req.addAttribute("adduserRole", ur);
		return "admin/userRole_add";
	}

	@PostMapping("/themquyen")
	public String taoUserRole(Model req, @ModelAttribute("adduserRole") UserRole ur) {
		urService.insert(ur);
		return "redirect:/admin/danhsachphanquyen";
	}

	/*
	 * @GetMapping("/xoaquyen") public String xoaUserRole(Model
	 * req, @RequestParam("id") String id) { int dele = Integer.parseInt(id);
	 * urService.delete(dele); return "redirect:/admin/danhsachphanquyen"; }
	 */

	@GetMapping("/suaphanquyen")
	public String suaUserRole(Model req, @RequestParam("id") String id) {
		int edit = Integer.parseInt(id);
		UserRole url = urService.getId(edit);
		req.addAttribute("editur", url);
		return "admin/userRole_edit";
	}

	@PostMapping("/suaphanquyen")
	public String suaUserRole_Luu(Model req, @ModelAttribute("editur") UserRole ur) {
		urService.edit(ur);
		return "redirect:/admin/danhsachphanquyen";
	}
}
