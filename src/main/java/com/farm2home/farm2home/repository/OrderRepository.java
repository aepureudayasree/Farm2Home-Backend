package com.farm2home.farm2home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm2home.farm2home.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}