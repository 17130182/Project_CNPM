package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles" , //
uniqueConstraints = { //
        @UniqueConstraint(name = "app_role_uk", columnNames = "name") })
public class AppRole {

	@Id
	@Column(name = "id", nullable = false)
	private int idRole;

	@Column(name = "name", length = 30, nullable = false)
	private String roleName;

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

}