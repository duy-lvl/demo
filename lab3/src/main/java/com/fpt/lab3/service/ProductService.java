package com.fpt.lab3.service;

import com.fpt.lab3.model.Product;
import com.fpt.lab3.model.SearchForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public void create(Product product);

    public List<Product> getAll();
    public List<Product> search(SearchForm searchForm);

    public Product detail(int id);

    public void update(Product product);

    public void delete(int id);
}
