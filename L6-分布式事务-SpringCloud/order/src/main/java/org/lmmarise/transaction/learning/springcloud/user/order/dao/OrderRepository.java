package org.lmmarise.transaction.learning.springcloud.user.order.dao;

import org.lmmarise.transaction.learning.springcloud.user.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOneByTitle(String title);
    
}
