package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Bill_Detail;

public interface BillDetailRopo extends JpaRepository<Bill_Detail, Integer> {

}
