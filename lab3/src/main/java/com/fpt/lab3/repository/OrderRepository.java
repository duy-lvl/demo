package com.fpt.lab3.repository;

import com.fpt.lab3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
