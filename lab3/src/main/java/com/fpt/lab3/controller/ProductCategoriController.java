package com.fpt.lab3.controller;


import com.fpt.lab3.model.Product;
import com.fpt.lab3.model.ProductCategories;
import com.fpt.lab3.model.SearchForm;
import com.fpt.lab3.service.ProductCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class ProductCategoriController {
    @Autowired
    private ProductCategoriesService productCategoriesService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProduct(Model model) {
        ProductCategories categories = new ProductCategories();
        model.addAttribute("categories", categories);

        return "/product-categories/add-category";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProduct(Model model) {
        List<ProductCategories> productCategories = productCategoriesService.getAll();
        model.addAttribute("productCategories", productCategories);
        return "/product-categories/list-categories";
    }
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailProduct(@PathVariable("id") Integer id, Model model) {
        ProductCategories productCategory = productCategoriesService.detail(id);
        model.addAttribute("productCategory", productCategory);
        return "/product-categories/detail-category";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute ProductCategories categories, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/product/add-product";
        } else {
            categories.setCreateAt(LocalDateTime.now());
            productCategoriesService.create(categories);

            return "redirect:/product/list";
        }

    }
}
