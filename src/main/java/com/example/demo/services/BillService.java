package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.RepoCustom;
import com.example.demo.utils.EncrytedPasswordUtils;



@Service
public class BillService {

	@Autowired
	BillRepo bill;
	@Autowired
	RepoCustom repoCustom;
	
	
	public void insert(Bill bi) {
		repoCustom.add_editBill(bi);
	}
	public List<Bill> getAll() {
		return bill.findAll();
	}
	public void edit(Bill bi) {
		repoCustom.add_editBill(bi);
	}

	public void delete(int id) {
		bill.deleteById(id);
	}
	public Bill getId(int id) {
		return bill.getOne(id);
	}
}
