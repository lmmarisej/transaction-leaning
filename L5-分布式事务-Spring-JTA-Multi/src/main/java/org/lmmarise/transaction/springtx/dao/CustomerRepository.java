package org.lmmarise.transaction.springtx.dao;

import org.lmmarise.transaction.springtx.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}
