package org.lmmarise.transaction.learning.springcloud.user.order.dao;

import org.lmmarise.transaction.learning.springcloud.user.order.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}
