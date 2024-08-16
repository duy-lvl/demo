package com.fpt.lab3.service;


import com.fpt.lab3.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartComponent{
    Map<Integer, List<CartItem>> storeCart;

    public CartComponent() {
        storeCart = new HashMap<>();
    }
    public void addCart(Integer customerId, List<CartItem> cartItemList) {
        storeCart.put(customerId, cartItemList);
    }

    public List<CartItem> getCart(int customerId) {
        return storeCart.get(customerId);
    }

}
