package io.zipcoder.ykk7.service;

import io.zipcoder.ykk7.entity.Order;
import io.zipcoder.ykk7.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private OrderRepository orderRepository;
    
    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(long id) {
        return orderRepository.findOne(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(long id) {
        orderRepository.delete(id);
    }
}
