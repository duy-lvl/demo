package com.fpt.lab3.service;

import com.fpt.lab3.model.Product;
import com.fpt.lab3.model.SearchForm;
import com.fpt.lab3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> search(SearchForm searchForm) {
        if ("ALL".equals(searchForm.getSearchType())) {
            return productRepository.findAll();
        } else if("PRICE".equals(searchForm.getSearchType())) {
            Double price = Double.valueOf(searchForm.getSearchText());
            return productRepository.findAllByPriceIsLessThan(price);
        } else {
            return productRepository.findAllByName(searchForm.getSearchText());
        }
    }



    @Override
    public Product detail(int id) {
        return productRepository.getById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(productRepository.getById(id));
    }
}
