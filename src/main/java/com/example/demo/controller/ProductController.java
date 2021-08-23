package com.example.demo.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Catalog;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.models.Pagination;
import com.example.demo.services.CartServices;
import com.example.demo.services.CatalogService;
import com.example.demo.services.ProductService;
import com.example.demo.services.ReviewService;
import com.example.demo.services.UserServices;
import com.example.demo.tools.Tools;



@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CartServices cart;
	@Autowired
	CatalogService catalogService;
	@Autowired
	ReviewService review;
	@Autowired
	UserServices us;
	
//	@Autowired
//	BoardnewService boardnewService;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	 @GetMapping("/")
	 public String trangChu(Model req) {
		
		// Product Rau củ quả
		List<Product> product_raucu= productService.getProductByCatalog(1);
		req.addAttribute("raucu", product_raucu);	
		
		// Product hạt
		List<Product> product_hat= productService.getProductByCatalog(2);
		req.addAttribute("product_hat", product_hat);	
		
		// Product trái cây
		List<Product> product_traicay= productService.getProductByCatalog(3);
		req.addAttribute("product_traicay", product_traicay);	
		
		// Product mật ong
		List<Product> product_matong= productService.getProductByCatalog(4);
		req.addAttribute("product_matong", product_matong);	
		
		// Product mới
		List<Product> product_new= productService.getProductByCatalog(5);
		req.addAttribute("product_new", product_new);	
				
		// Product bán chạy
		List<Product> product_banchay= productService.getProductByCatalog(6);
		req.addAttribute("product_banchay", product_banchay);	
		
		List<Product> productList = productService.getAll();
		req.addAttribute("productlist", productList);	
		//Giá giảm
		List<Product> productsList1 = new ArrayList<Product>();
		

		req.addAttribute("productlist1", productsList1);
		
		// Product giảm giá
		List<Product> product_sale= productService.getProductByCatalog(7);
		req.addAttribute("product_sale", product_sale);	
		
		req.addAttribute("listCart", cart.listCartItems());
		req.addAttribute("totalPrice", cart.getPriceTotal());
		
		return "index";
	}
	 
	 @GetMapping("/sanpham")
	 public String sanPham(Model req,@RequestParam(required = false) Integer _page,@RequestParam(required = false) Integer _limit ) {
		 List<Product> productlist = productService.getAll();// get all data product
//		 pagination{
		 //default value
		 int totalProduct = productlist.size();
		 int page = _page == null ? 1:_page ;
		 int limit = _limit == null ? 10:_limit;
		 int start = (page-1)*limit;
		 req.addAttribute("pagination",new Pagination(limit, page,(int) Math.round(totalProduct/limit+0.5)));
		 req.addAttribute("productlist",  Tools.copyArrayList(productlist,start,limit));
//		 }
		 
		 List<Catalog> catelist = catalogService.getAllCatalog();
		 req.addAttribute("catelist", catelist);
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "product";
	 }


		@GetMapping("/chitietsanpham")
		public String chiTietSanPham(@RequestParam("id") String id, Model req, Principal u ) {
			int id2 = Integer.parseInt(id);
			Product product = productService.getId(id2);
			System.out.println(product.getName());
			req.addAttribute("product", product);
			req.addAttribute("danhmuc", productService.getCatalog(id2));
			req.addAttribute("listCart", cart.listCartItems());
			req.addAttribute("totalPrice", cart.getPriceTotal());
			
			
			List<Review> reviewlist = review.getReviewByProduct(id2);
			req.addAttribute("review", reviewlist);
			req.addAttribute("re", review);
			if(u!=null) {
			User user=us.getUser(u.getName());
			req.addAttribute("user", user);
			}
			return "product-detail";
		}
	 @GetMapping("/danhmucsanpham")
	 public String sanPhamtheodanhmuc(@RequestParam("id") String id, Model req,@RequestParam(required = false) Integer _page,@RequestParam(required = false) Integer _limit ) {
		 int id2 = Integer.parseInt(id);
		 List<Catalog> catelist = catalogService.getAllCatalog();
		 req.addAttribute("catelist", catelist);
		 List <Product> productbycatalog = productService.getProductByCatalog(id2);
		 
//		 pagination{
		 //default value
		 int totalProduct = productbycatalog.size();
		 int page = _page == null ? 1:_page ;
		 int limit = _limit == null ? 10:_limit;
		 int start = (page-1)*limit;
		 req.addAttribute("pagination",new Pagination(limit, page,(int) Math.round(totalProduct/limit+0.5)));
		 req.addAttribute("productbycatalog",  Tools.copyArrayList(productbycatalog,start,limit));
//		 }
	
		 
//		 req.addAttribute("productbycatalog", productbycatalog);
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		return "product-cate";	
	 }
	 @GetMapping("/gioithieu")
	 public String tranggioithieu(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "introduce";
	 }
	 @GetMapping("/tintuc")
	 public String trangtintuc(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "news-list";
	 }
	 
	 @GetMapping("/chinhsach")
	 public String trangchinhsach(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "security";
	 }
	 @GetMapping("/lienhe")
	 public String tranglienhe(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "contact";
	 }
	 @GetMapping("/huongdandathang")
	 public String trangHuongDanDatHang(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "order";
	 }
	 @GetMapping("/huongdanthanhtoan")
	 public String trangHuongDanThanhToan(Model req) {
		 req.addAttribute("listCart", cart.listCartItems());
		 req.addAttribute("totalPrice", cart.getPriceTotal());
		 return "payment";
	 }
	 @GetMapping("/search")
		public String Search(Model model, @RequestParam("s") String name
				) throws SQLException {
			List<Product> listProduct = productService.loadProduct();
			productService.setList(listProduct);
			ArrayList<Product> list = productService.findProduct(name);
			ArrayList<Product> list2 = new ArrayList<Product>();
			if (list.size() > 12)
				for (int i = 0; i < 12; i++)
					list2.add(list.get(i));
			else
				list2.addAll(list);
			model.addAttribute("productlist", list2);
			model.addAttribute("name", name);
			model.addAttribute("listCart", cart.listCartItems());
			model.addAttribute("totalPrice", cart.getPriceTotal());
			return "search";
		}

		@ResponseBody
		@GetMapping("/autoComplete")
		public String[] AutoComplete(Model model, @RequestParam("term") String name
				) throws SQLException {
			List<Product> listProduct = productService.loadProduct();
			productService.setList(listProduct);
			ArrayList<Product> list = productService.findProduct(name);
			String[] st = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				st[i] = list.get(i).getName();
			}
			System.out.println(st);
			return st;
		}
		
		
		

		@PostMapping("/Review")
		public String taoSanPham(Model req, @RequestParam("name") String name, @RequestParam("id") String id,
				@RequestParam("email") String email, @RequestParam("content") String content ) {
			int id2 = Integer.parseInt(id);
			Product product = productService.getId(id2);
			review.createNewReview(id2, name, email, content);
			req.addAttribute("listCart", cart.listCartItems());
			req.addAttribute("totalPrice", cart.getPriceTotal());
			req.addAttribute("product", product);
			return "redirect:/chitietsanpham?id="+id;
		}
		
		
	 
	
}
