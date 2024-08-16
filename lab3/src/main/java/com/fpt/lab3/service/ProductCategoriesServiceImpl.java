package com.fpt.lab3.service;


import com.fpt.lab3.model.ProductCategories;
import com.fpt.lab3.repository.ProductCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoriesServiceImpl implements ProductCategoriesService {
    @Autowired
    private ProductCategoriesRepository productCategoriesRepository;

    @Override
    public List<ProductCategories> getAll() {
        return productCategoriesRepository.findAll();
    }

    @Override
    public void create(ProductCategories productCategories) {
        productCategoriesRepository.save(productCategories);

    }

    @Override
    public void update(ProductCategories productCategories) {
        productCategoriesRepository.save(productCategories);
    }

    @Override
    public void delete(int id) {
        productCategoriesRepository.deleteById(id);
    }

    @Override
    public ProductCategories detail(int id) {
        return productCategoriesRepository.getById(id);
    }
}
