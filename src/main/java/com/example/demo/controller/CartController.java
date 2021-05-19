package com.example.demo.controller;

import java.sql.SQLException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Product;
import com.example.demo.models.AJAXRequestBody;
import com.example.demo.models.AJAXRespondBody;
import com.example.demo.models.CartItem;
import com.example.demo.services.CartServices;
import com.example.demo.services.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;

	@Autowired
	CartServices cart;

	// Khi nhan vao nut "Add to cart!"
	@PostMapping("/addToCartAJAX")
	public ResponseEntity<?> addToCartViaAjax(@RequestBody Product product) throws SQLException {
		System.out.println("Them vao gio hang kieu ajax!");
		cart.addItems(product.getId());
		AJAXRespondBody res = new AJAXRespondBody();
		res.setName(product.getName());
		res.setLink_image(product.getImage_link());
		res.setPrice(product.getPrice());
		CartItem getCart = cart.getItemByIDProduct(product.getId());
		res.setQuantity(getCart.getQuantity());
		res.setTotalPrice(getCart.getPriceItem());
		int sizeCart = cart.sizeCart();
		res.setSizeCart(sizeCart);
		System.out.println(sizeCart);
		System.out.println(cart.getList().values().toString());
		return ResponseEntity.ok(res);

	}

	@PostMapping("/changeQuantity")
	public ResponseEntity<?> changeQuantity(@RequestBody AJAXRequestBody req) throws SQLException {
		int id = Integer.parseInt(req.getId());
		int num = Integer.parseInt(req.getQuantity());
		System.out.println("ID dang sua la:"+req.getId());
		cart.changeQuantity(req.getId(), num);
		AJAXRespondBody res = new AJAXRespondBody();
		res.setPrice(cart.getItemByIDProduct(id).getPriceItem() + "");
		res.setTotalPrice((int) cart.getPriceTotal());
		return ResponseEntity.ok(res);

	}

	@GetMapping("/cart")
	public String sanPham(Model req) {
		req.addAttribute("listCart", cart.listCartItems());
		req.addAttribute("totalPrice", cart.getPriceTotal());
		return "cart";
	}
}
