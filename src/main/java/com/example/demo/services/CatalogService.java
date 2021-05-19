package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Catalog;
import com.example.demo.repository.CatalogRepo;
import com.example.demo.repository.RepoCustom;



@Service
public class CatalogService {
	@Autowired
	CatalogRepo cataRepo;

	@Autowired
	RepoCustom repoCustom;
	
	

	



	public  void insert(Catalog catalog) {
		repoCustom.add_editCatalog(catalog);
		
	}
	public void edit(Catalog catalog) {
		repoCustom.add_editCatalog(catalog);
		
	}
	public  void delete(int id) {
		cataRepo.deleteById(id);
		
		
	}
	public Catalog getId(int id) {
		return cataRepo.getOne(id);
	}



	public List<Catalog> getAllCatalog() {
		return cataRepo.findAll();
	}



	

}

