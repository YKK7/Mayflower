package io.zipcoder.ykk7.repository;

import io.zipcoder.ykk7.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
