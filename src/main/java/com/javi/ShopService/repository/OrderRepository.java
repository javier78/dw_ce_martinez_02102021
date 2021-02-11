package com.javi.ShopService.repository;

import com.javi.ShopService.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findByCustomerId(Integer customerId);
}
