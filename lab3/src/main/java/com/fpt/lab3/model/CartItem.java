package com.fpt.lab3.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItem {
    private int productId;
    private int quantity;
}
