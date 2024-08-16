package com.fpt.lab3.controller;


import com.fpt.lab3.model.FileStore;
import com.fpt.lab3.model.Product;
import com.fpt.lab3.model.SearchForm;
import com.fpt.lab3.service.FileStoreService;
import com.fpt.lab3.service.ProductCategoriesService;
import com.fpt.lab3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileStoreService fileStoreService;
    @Autowired
    private ProductCategoriesService productCategoriesService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("listProductCategories", productCategoriesService.getAll());

        return "/product/add-product";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProduct(@ModelAttribute SearchForm searchForm,Model model) {

        if (searchForm.getSearchType() == null) {
            searchForm = new SearchForm("","ALL");
        }
        List<Product> productList = productService.search(searchForm);
        model.addAttribute("productList", productList);
        model.addAttribute("searchForm", searchForm);
        return "/product/list-product";
    }
    @RequestMapping(value = "/display/{id}", method = RequestMethod.GET)
    public void displayImage(@PathVariable Integer id, HttpServletResponse httpServletResponse) throws IOException {
        FileStore fileStore = fileStoreService.detail(id);
        httpServletResponse.setContentType(fileStore.getContentType());
        httpServletResponse.getOutputStream().write(fileStore.getData());
        httpServletResponse.getOutputStream().close();

    }
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        FileStore fileStore = fileStoreService.detail(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ fileStore.getName()).body(fileStore.getData());
    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String searchProduct(@ModelAttribute SearchForm searchForm, Model model) {
//        List<Product> productList = productService.search(searchForm);
//        System.out.println("searchForm:" + searchForm);
//        model.addAttribute("productList", productList);
//        model.addAttribute("searchForm", searchForm);
//        return "/product/list-product";
//    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute Product product, @RequestParam("image") MultipartFile file, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            return "/product/add-product";
        } else {


            FileStore fileStore = new FileStore();
            fileStore.setName(file.getOriginalFilename());
            fileStore.setContentType(file.getContentType());
            fileStore.setData(file.getBytes());
            fileStoreService.addFile(fileStore);

            product.setCreateAt(LocalDateTime.now());
            product.setFileId(fileStore.getId());
            productService.create(product);

            return "redirect:/product/list";
        }

    }
}
