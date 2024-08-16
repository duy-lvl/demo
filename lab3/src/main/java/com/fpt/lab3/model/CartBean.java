package com.fpt.lab3.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartBean {
    private int customerId;
    private List<CartItem> cartItemList;
}
