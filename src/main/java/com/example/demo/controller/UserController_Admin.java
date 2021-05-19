package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping({"/admin"})
@Controller
public class UserController_Admin {
    @Autowired
    UserServices us;

    public UserController_Admin() {
    }

    @GetMapping({"/danhsachuser"})
    public String user(Model req) {
        List<User> userlist = this.us.getAll();
        req.addAttribute("user", userlist);
        req.addAttribute("us", this.us);
        return "admin/user_list";
    }

    @RequestMapping({"/themuser"})
    public String themTaiKhoan(Model req) {
        User us = new User();
        req.addAttribute("adduser", us);
        return "admin/user_add";
    }

    @PostMapping({"/themuser"})
    public String taoTaiKhoan(Model req, @ModelAttribute("adduser") User u) {
        this.us.insert(u);
        return "redirect:/admin/danhsachuser";
    }

    @GetMapping({"/xoauser"})
    public String xoaUser(Model req, @RequestParam("id") String id) {
        int dele = Integer.parseInt(id);
        this.us.delete(dele);
        return "redirect:/admin/danhsachuser";
    }

    @GetMapping({"/suauser"})
    public String suaUser(Model req, @RequestParam("id") String id) {
        int edit = Integer.parseInt(id);
        User user = this.us.getId(edit);
        req.addAttribute("edituser", user);
        return "admin/user_edit";
    }

    @PostMapping({"/suauser"})
    public String suaUser_Luu(Model req, @ModelAttribute("edituser") User u) {
        this.us.edit(u);
        return "redirect:/admin/danhsachuser";
    }
}
