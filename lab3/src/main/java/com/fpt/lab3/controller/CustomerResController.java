package com.fpt.lab3.controller;


import com.fpt.lab3.model.Customer;
import com.fpt.lab3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerResController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> findAll() {
        return customerService.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer detail(@PathVariable Integer id) {
        return customerService.detail(id);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Boolean create(@RequestBody Customer customer) {
        customerService.create(customer);
        return true;
    }
}
