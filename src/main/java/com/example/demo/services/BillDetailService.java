package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill_Detail;
import com.example.demo.repository.BillDetailRopo;
import com.example.demo.repository.RepoCustom;

@Service
public class BillDetailService {
	@Autowired
	BillDetailRopo billDetail;
	@Autowired
	RepoCustom repoCustom;
	
	
	public List<Bill_Detail> getAll() {
		return billDetail.findAll();
	}
	

	public void delete(int id) {
		billDetail.deleteById(id);
	}
	public Bill_Detail getId(int id) {
		return billDetail.getOne(id);
	}

}
