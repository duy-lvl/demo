package com.fpt.lab3.controller;

import com.fpt.lab3.model.Customer;
import com.fpt.lab3.model.PaymentItem;
import com.fpt.lab3.model.Product;
import com.fpt.lab3.service.CustomerService;
import com.fpt.lab3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProduct(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "/customer/add-customer";
    }
    @RequestMapping(value = "/buy-product", method = RequestMethod.GET)
    public String addProduct(@RequestParam Integer customerId, Model model) {
        Customer customer = customerService.detail(customerId);
        List<Product> productList = productService.getAll();
        model.addAttribute("customer", customer);
        model.addAttribute("productList", productList);

        return "/customer/buy-product";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(@RequestParam Integer customerId, Model model) {
        Customer customer = customerService.detail(customerId);
        List<PaymentItem> paymentItemList = customerService.getPaymentItem(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("paymentItemList", paymentItemList);
        return "/customer/payment";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/customer/add-customer";
        } else {
            customer.setCreateAt(LocalDateTime.now());
            customerService.create(customer);
            return "redirect:/customer/list";
        }

    }
}
