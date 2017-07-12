package io.zipcoder.ykk7.controller;

import io.zipcoder.ykk7.entity.Order;
import io.zipcoder.ykk7.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8100", "file://"})
    public ResponseEntity<List<Order>> getAll() {
        LOG.info("getting all orders");
        List<Order> orders = new ArrayList<>();

        orderService.findAll().forEach(orders::add);

        if (orders == null || orders.isEmpty()) {
            LOG.info("no orders found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> get(@PathVariable("id") long id) {
        LOG.info("getting order with id: {}", id);
        Order order = orderService.findOne(id);

        if (order == null) {
            LOG.info("order with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        LOG.info("creating new order: {}", order);

        orderService.save(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> update(@PathVariable long id, @RequestBody Order order) {
        LOG.info("updating order: {}", order);
        Order currentOrder = orderService.findOne(id);

        if (currentOrder == null) {
            LOG.info("Order with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentOrder.setId(order.getId());
        currentOrder.setTimeIn(order.getTimeIn());
        currentOrder.setLocation(order.getLocation());

        orderService.save(order);
        return new ResponseEntity<>(currentOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        LOG.info("deleting order with id: {}", id);
        Order order = orderService.findOne(id);

        if (order == null) {
            LOG.info("Unable to delete. Order with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}