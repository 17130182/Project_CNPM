package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserRole;

@Repository
@Transactional
public class AppRoleCustom {

	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(int userId) {
		String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
				+ " where ur.user.id = :userId ";

		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}