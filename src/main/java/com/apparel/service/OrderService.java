package com.apparel.service;

import com.apparel.model.Design;
import com.apparel.model.Order;
import com.apparel.repository.DesignRepository;
import com.apparel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DesignRepository designRepository;



    public Optional<Order> createOrder(Order order, Long designId)
    {
        return designRepository.findById(designId).map(design-> {
            order.setDesign(design);

            BigDecimal totalPrice=design.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));

            order.setTotalPrice(totalPrice);

            return orderRepository.save(order);
        });
    }

    public Optional<Order> getOrderById(Long id)
    {
        return orderRepository.findById(id);
    }

    public Optional<Order> updateOrder(Long id,Order updatedOrder)
    {
        return orderRepository.findById(id).map(order-> {
            order.setType(updatedOrder.getType());
            order.setSize(updatedOrder.getSize());
            order.setDate(updatedOrder.getDate());
            order.setQuantity(updatedOrder.getQuantity());

            Design design=order.getDesign();

            if(updatedOrder.getDesign()!=null) {
                design=updatedOrder.getDesign();
                order.setDesign(design);
            }

            BigDecimal totalPrice=design.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            order.setTotalPrice(totalPrice);

            return orderRepository.save(order);
        });
    }

    public void deleteOrder(Long id)
    {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }



    public List<Order> getOrdersByType(String type)
    {
        return orderRepository.findByType(type);
    }

    public List<Order> getOrdersBySize(String size)
    {
        return orderRepository.findBySize(size);
    }

    public List<Order> getOrdersAfterDate(LocalDate date)
    {
        return orderRepository.findByDateAfter(date);
    }
}
