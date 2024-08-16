package com.fpt.lab3.service;

import com.fpt.lab3.model.ProductCategories;

import java.util.List;

public interface ProductCategoriesService {
    public List<ProductCategories> getAll();
    public void create(ProductCategories productCategories);
    public void update(ProductCategories productCategories);
    public void delete(int id);
    public ProductCategories detail(int id);
}
