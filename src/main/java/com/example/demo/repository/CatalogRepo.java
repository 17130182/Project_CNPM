package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Catalog;

public interface CatalogRepo extends JpaRepository<Catalog, Integer> {

}
