package com.fpt.lab3.service;

import com.fpt.lab3.model.Customer;
import com.fpt.lab3.model.PaymentItem;
import com.fpt.lab3.model.PaymentRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAll();

    public void create(Customer customer);

    public void update(Customer customer);

    public void delete(int id);

    public Customer detail(int id);

    public List<PaymentItem> getPaymentItem(int customerId);
    public Boolean payment(PaymentRequest paymentRequest);
}
