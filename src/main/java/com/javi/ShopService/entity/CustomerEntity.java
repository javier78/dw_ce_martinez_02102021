package com.javi.ShopService.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String email;
}
