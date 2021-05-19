package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.RepoCustom;
import com.example.demo.repository.UserRoleRepo;
@Service
public class UserRoleService {
	@Autowired
	UserRoleRepo urole;
	@Autowired
	RepoCustom repoCustom;
	public void insert(UserRole userrole) {
		repoCustom.add_editUserRole(userrole);
	}
    public void edit(UserRole userrole) {
    	repoCustom.add_editUserRole(userrole);
    }
    public void delete(int id) {
    	urole.deleteById(id);
    }
    public UserRole getId(int id) {
    	return urole.getOne(id);
    }
    public List<UserRole> getAll() {
		return urole.findAll();
	}
    public List<UserRole> getAllUserRole(){
    	return urole.findAll();
    }
}
