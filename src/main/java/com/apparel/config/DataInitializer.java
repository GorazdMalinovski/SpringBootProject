package com.apparel.config;

import com.apparel.model.Design;
import com.apparel.model.Order;
import com.apparel.service.DesignService;
import com.apparel.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;


@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeDatabase(DesignService designService, OrderService orderService) {
        return new CommandLineRunner() {
            @Override
            public void run(String[] args) {

                Design design1=designService.createDesign(new Design("Summer Tee", "Red", "Nike", "Cotton", new BigDecimal("20.00"), null));
                Design design2=designService.createDesign(new Design("Winter Collection", "Blue", "Adidas", "Wool", new BigDecimal("40.00"), null));
                Design design3=designService.createDesign(new Design("Classic Polo", "White", "Puma", "Polyester", new BigDecimal("30.00"), null));


                orderService.createOrder(new Order("T-Shirt", "M", LocalDate.now().minusDays(1), 3, null), design1.getId());
                orderService.createOrder(new Order("Hoodie", "XL", LocalDate.now().minusDays(2), 5, null), design2.getId());
                orderService.createOrder(new Order("Polo", "S", LocalDate.now().minusDays(3), 7, null), design3.getId());

                System.out.println("Database seeded successfully!");
            }
        };
    }
}