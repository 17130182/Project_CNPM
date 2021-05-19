package com.example.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.entity.Catalog;
import com.example.demo.services.CatalogService;
import com.example.demo.utils.CatalogPDFExporter;


@RequestMapping("/admin")
@Controller
public class CatalogController_Admin {
	@Autowired
	CatalogService catalogService;

	@GetMapping("/danhsachdanhmuc")
	public String danhmuc(Model req) {
		List<Catalog> catelist = catalogService.getAllCatalog();
		req.addAttribute("listcata", catelist);
		return "admin/cate_list";
	}

	@RequestMapping("/themdanhmuc")
	public String themDanhMuc(Model req) {
		Catalog catalog = new Catalog();
		req.addAttribute("addcatalog", catalog);
		return "admin/cate_add";
	}

	@PostMapping("/themdanhmuc")
	public String taoDanhMuc(Model req, @ModelAttribute("addcatalog") Catalog c) {
		catalogService.insert(c);
		return "redirect:/admin/danhsachdanhmuc";
	}

	@GetMapping("/xoadanhmuc")
	public String xoaDanhMuc(Model req, @RequestParam("id") String id) {
		int dele = Integer.parseInt(id);
		catalogService.delete(dele);
		return "redirect:/admin/danhsachdanhmuc";
	}
	
	@GetMapping("/suadanhmuc")
	public String suaSanPham(Model req, @RequestParam("id") String id) {
		int edit = Integer.parseInt(id);
		Catalog catalog=catalogService.getId(edit);
		req.addAttribute("editcatalog", catalog);
		return "admin/cate_edit";
	}
	
	@PostMapping("/suadanhmuc")
	public String suaDanhMuc_Luu(Model req, @ModelAttribute("editcatalog") Catalog c) {
		catalogService.edit(c);
		return "redirect:/admin/danhsachdanhmuc";
	}
	@GetMapping("/exportcatecsv")
	public void exportoCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
		DateFormat created = new SimpleDateFormat("dd-MM-yy");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "danhmuc"+ currentDateTime + ".csv";
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=" + fileName;
		
		response.setHeader(headerKey, headervalue);
		List<Catalog> cataloglist = catalogService.getAllCatalog();
	
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Ma DM","Ten DM","Danh Muc Cha"};
		String[] nameMapping = {"id","name","parent_id"};
		csvWriter.writeHeader(csvHeader);
		for(Catalog catalog : cataloglist) {
			csvWriter.write(catalog, nameMapping);
			
		}
		csvWriter.close();
	}
	@GetMapping("/exportcatepdf")
	public void exportoPDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd_HH-mm-ss");
		DateFormat created = new SimpleDateFormat("dd-MM-yy");
		String currentDateTime = dateFormatter.format(new Date());
		String fileName = "sanpham"+ currentDateTime + ".pdf";
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headervalue);
		
		List<Catalog> cataloglist = catalogService.getAllCatalog();
		CatalogPDFExporter exporter = new CatalogPDFExporter(cataloglist);
		exporter.exportpdf(response);
	}
}
