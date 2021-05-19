package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.repository.CatalogRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.RepoCustom;

@Service
public class ProductService {
	
	private static String UPLOADED_FOLDER = "target/classes/static/images/";

	@Autowired
	ProductRepo proRepo;

	@Autowired
	RepoCustom repoCustom;

	@Autowired
	CatalogRepo catalogRepo;
	private List<Product> list;
	private String nameSearch = "";
	private ArrayList<Product> listFound = new ArrayList<Product>();

	public void insert(Product product, MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + product.getId() + ".jpg");
		Files.write(path, bytes);
		product.setImage_link(product.getId() + ".jpg");
		repoCustom.add_editProduct(product);
	}

	public void edit(Product product) {
		repoCustom.add_editProduct(product);
	}

	public void delete(int id) {
		proRepo.deleteById(id);
		;
	}

	public Product getId(int id) {
		return proRepo.getOne(id);
	}

	public String getCatalog(int id) {
		return proRepo.getOne(id).getCatalog().getName();
	}

	public Product getName(String name) {
		return proRepo.findAllByName(name);
	}

	public List<Product> getAll() {
		return proRepo.findAll();
	}

	public List<Product> getProductByCatalog(int idCatalog) {
		return proRepo.findAllByCatalog(catalogRepo.getOne(idCatalog));
	}

	public List<Product> searchByName(String productName) {
		return null;

	}
	public List<Product> loadProduct() throws SQLException {
		List<Product> list = proRepo.findAll();
		return list;
	}

	public ArrayList<Product> getListFound() {
		return listFound;
	}

	public ArrayList<Product> findProduct(String name) {
		System.out.println("Tim Kiem: "+name);
		nameSearch = name;
		if (!listFound.isEmpty())
			listFound.clear();
		char[] slName = name.toCharArray();
		String s1 = "";
		String s2 = "";
		int count = 0;
		for (Product s : list) {
//			if (s.getName().toLowerCase().contains(nameSearch)) {
//				listFound.add(s);
//			}
			for (int i = 0; i < slName.length; i++) {
				System.out.println("Dang so sanh voi "+s.getName());
				s1 += s.getName().charAt(i);
				s2 += slName[i];
				if (!s1.equalsIgnoreCase(s2)) {
					s1 = "";
					s2 = "";
					count = 0;
					break;
				} else
					count++;
				if (count == slName.length) {
					listFound.add(s);
					s1 = "";
					s2 = "";
					count = 0;
				}

			}
		}
		return listFound;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setListFound(ArrayList<Product> listFound) {
		this.listFound = listFound;
	}
}