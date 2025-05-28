package com.apparel.repository;

import com.apparel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByType(String type);
    List<Order> findBySize(String size);
    List<Order> findByDateAfter(LocalDate date);
}
