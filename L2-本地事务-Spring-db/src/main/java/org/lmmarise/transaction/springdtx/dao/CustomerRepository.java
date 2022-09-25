package org.lmmarise.transaction.springdtx.dao;

import org.lmmarise.transaction.springdtx.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
    
}
