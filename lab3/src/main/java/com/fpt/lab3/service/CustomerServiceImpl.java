package com.fpt.lab3.service;

import com.fpt.lab3.model.*;
import com.fpt.lab3.repository.CustomerRepository;
import com.fpt.lab3.repository.OrderRepository;
import com.fpt.lab3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartComponent cartComponent;
    @Autowired
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer detail(int id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<PaymentItem> getPaymentItem(int customerId) {
        List<CartItem> cartItemList = cartComponent.getCart(customerId);
        List<PaymentItem> result = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            Product product = productRepository.findById(cartItem.getProductId()).get();
            PaymentItem paymentItem = new PaymentItem(product, cartItem.getQuantity(), product.getPrice() * cartItem.getQuantity());
            result.add(paymentItem);
        }
        return result;
    }

    @Override
    public Boolean payment(PaymentRequest paymentRequest) {
        Customer customer = customerRepository.findById(paymentRequest.getCustomerId()).get();
        List<CartItem> cartItemList = cartComponent.getCart(paymentRequest.getCustomerId());
        List<OrderItem> orderItemList = new ArrayList<>();
        AtomicReference<Double> totalOrder = new AtomicReference<>(0.0);
        cartItemList.forEach(cartItem -> {
            Product product = productRepository.findById(cartItem.getProductId()).get();
            Double totalItemAmount = product.getPrice()* cartItem.getQuantity();
            OrderItem item = OrderItem.builder()
                    .amount(totalItemAmount)
                    .quantity(cartItem.getQuantity())
                    .discount(0)
                    .build();
            totalOrder.updateAndGet(v -> v + totalItemAmount);
            orderItemList.add(item);
        });
        Order order = Order.builder()
                .address(paymentRequest.getAddress())
                .customer(customer)
                .orderItemList(orderItemList)
                .totalAmount(totalOrder.get())
                .build();
        orderRepository.save(order);
        return true;
    }
}
