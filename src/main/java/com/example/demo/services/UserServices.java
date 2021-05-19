package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.AppRoleCustom;
import com.example.demo.repository.AppUserDAO;
import com.example.demo.repository.RepoCustom;
import com.example.demo.repository.RoleDao;
import com.example.demo.repository.UserRepo;
import com.example.demo.utils.EncrytedPasswordUtils;

@Service
public class UserServices {
	@Autowired
	UserRepo ur;
	@Autowired
	AppRoleCustom appRoleCustom;
	@Autowired
	RepoCustom repoCustom;

	@Autowired
	AppUserDAO appDao;
	@Autowired
	RoleDao roleDao;
	public void createNewUser(User user) {
		UserRole userRole = new UserRole();
		userRole.setAppRole(roleDao.getOne(2));
		userRole.setUser(user);
		repoCustom.addUserRole(userRole);
	}
	

	public String getDefaultRole(String username) {
		User user = ur.findAllByUsername(username);
		List<String> list = appRoleCustom.getRoleNames(user.getId());
		if (list.contains("ROLE_ADMIN"))
			return "Admin";
		else if (list.contains("ROLE_USER"))
			return "User";
		return "NONE";
	}

	public User getUser(String username) {
		return ur.findAllByUsername(username);
	}

	public void insert(User user) {
		user.setPassword(EncrytedPasswordUtils.encrytePassword(user.getPassword()));
		repoCustom.add_editUser(user);
		;
	}

	public List<User> getAll() {
		return ur.findAll();
	}

	public void edit(User user) {
		user.setPassword(EncrytedPasswordUtils.encrytePassword(user.getPassword()));
		repoCustom.add_editUser(user);
		;
	}

	public void delete(int id) {
		ur.deleteById(id);
	}

	public User getId(int id) {
		return ur.getOne(id);
	}

}
