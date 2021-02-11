package com.javi.ShopService.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer customerId;

    @Column
    private String sku;

    @Column
    private BigDecimal total;

    @Column(columnDefinition = "created_at")
    private LocalDateTime createdAt;
}
