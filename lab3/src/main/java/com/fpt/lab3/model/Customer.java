package com.fpt.lab3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String phone;
    @JsonIgnore
    private LocalDateTime createAt;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @JsonIgnore
    private Date birthday;
    private Boolean isActive;
    private String createBy;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Order> orderList;
}
