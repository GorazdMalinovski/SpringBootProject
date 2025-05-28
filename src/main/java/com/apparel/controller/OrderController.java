package com.apparel.controller;

import com.apparel.model.Order;
import com.apparel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;



    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order,@RequestParam Long designId)
    {
        return orderService.createOrder(order,designId).map(createdOrder->{

                    URI location = URI.create("/orders/"+createdOrder.getId());

                    return ResponseEntity.created(location).body(createdOrder);
                }).orElse(ResponseEntity.badRequest().build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id)
    {
        return orderService.getOrderById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder)
    {
        return orderService.updateOrder(id, updatedOrder).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id)
    {
        if (orderService.getOrderById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders()
    {
        List<Order> orders=orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }



    @GetMapping("/type/{type}")
    public ResponseEntity<List<Order>> getOrdersByType(@PathVariable String type)
    {
        List<Order> orders=orderService.getOrdersByType(type);

        return orders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orders);
    }


    @GetMapping("/size/{size}")
    public ResponseEntity<List<Order>> getOrdersBySize(@PathVariable String size)
    {
        List<Order> orders=orderService.getOrdersBySize(size);

        return orders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orders);
    }


    @GetMapping("/after/{date}")
    public ResponseEntity<List<Order>> getOrdersAfterDate(@PathVariable String date)
    {
        LocalDate parsedDate=LocalDate.parse(date);

        List<Order> orders=orderService.getOrdersAfterDate(parsedDate);

        return orders.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(orders);
    }

}
