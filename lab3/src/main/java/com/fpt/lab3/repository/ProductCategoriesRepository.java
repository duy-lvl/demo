package com.fpt.lab3.repository;

import com.fpt.lab3.model.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories,Integer> {
}
