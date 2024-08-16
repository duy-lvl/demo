package com.fpt.lab3.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tblOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId",unique = true)
    private int id;
    private double totalAmount;
    private LocalDateTime createdAt;
    private int status;
    private String address;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;
    @OneToMany
    private List<OrderItem> orderItemList;

}
