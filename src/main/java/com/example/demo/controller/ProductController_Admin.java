package com.example.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.entity.Product;
import com.example.demo.services.CartServices;
import com.example.demo.services.ProductService;
import com.example.demo.utils.UserPDFExporter;

@RequestMapping("/admin")
@Controller
public class ProductController_Admin {
	@Autowired
	ProductService productService;

	@GetMapping("/danhsachsanpham")
	public String sanPham(Model req) {
		List<Product> productlist = productService.getAll();
		req.addAttribute("list", productlist);
		return "admin/product_list";
	}

	@RequestMapping("/themsanpham")
	public String themSanPham(Model req) {
		Product product = new Product();
		req.addAttribute("addproduct", product);
		return "admin/product_add";
	}

	@PostMapping("/themsanpham")
	public String taoSanPham(Model req, @ModelAttribute("addproduct") Product p, @RequestParam("file") MultipartFile file) throws IOException {
		productService.insert(p,file);
		return "redirect:/admin/danhsachsanpham";
	}

	@GetMapping("/xoasanpham")
	public String xoaSanPham(Model req, @RequestParam("id") String id) {
		int dele = Integer.parseInt(id);
		productService.delete(dele);
		return "redirect:/admin/danhsachsanpham";
	}
	
	@GetMapping("/suasanpham")
	public String suaSanPham(Model req, @RequestParam("id") String id) {
		int edit = Integer.parseInt(id);
		Product product=productService.getId(edit);
		req.addAttribute("editproduct", product);
		return "admin/product_edit";
	}
	
	@PostMapping("/suasanpham")
	public String suaSanPham_Luu(Model req, @ModelAttribute("editproduct") Product p) {
		productService.edit(p);
		return "redirect:/admin/danhsachsanpham";
	}
	@GetMapping("/export")
	public void exportoCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
		DateFormat created = new SimpleDateFormat("dd-MM-yy");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "sanpham"+ currentDateTime + ".csv";
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=" + fileName;
		response.setCharacterEncoding("UTF-8");
		response.setHeader(headerKey, headervalue);
		List<Product> productlist = productService.getAll();
	
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Ma San Pham","Ma Danh Muc","Ten San Pham","Gia","So Luong","Trang Thai","Mo Ta Ngan","Content","Giam Gia","Link Hinh","Ngay Tao"};
		String[] nameMapping = {"id","catalog","name","price","qty","status","description","content","discount","image_link","created"};
		csvWriter.writeHeader(csvHeader);
		for(Product product : productlist) {
			csvWriter.write(product, nameMapping);
			
		}
		csvWriter.close();
	}
	@GetMapping("/exportpdf")
	public void exportoPDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
		DateFormat created = new SimpleDateFormat("dd-MM-yy");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "sanpham"+ currentDateTime + ".pdf";
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headervalue);
		
		List<Product> productlist = productService.getAll();
		UserPDFExporter exporter = new UserPDFExporter(productlist);
		exporter.exportpdf(response);
	}

}
