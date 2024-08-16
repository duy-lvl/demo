package com.fpt.lab3.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double price;
    private LocalDateTime createAt;
    private String createBy;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private ProductCategories productCategories;
    private int fileId;
}
