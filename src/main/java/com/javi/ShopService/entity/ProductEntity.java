package com.javi.ShopService.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String sku;
}
