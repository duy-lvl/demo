package com.fpt.lab3.repository;

import com.fpt.lab3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(name = "findAllByName", value = "select p from Product p where p.name like %?1%")
    public List<Product> findAllByName(String name);
    @Query("select p from Product p where p.price <= :price")
    public List<Product> findAllByPriceIsLessThan(Double price);
}
