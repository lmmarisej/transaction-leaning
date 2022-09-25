package org.lmmarise.transaction.springdtx.web;

import org.lmmarise.transaction.springdtx.dao.CustomerRepository;
import org.lmmarise.transaction.springdtx.domain.Customer;
import org.lmmarise.transaction.springdtx.service.CustomerServiceTxInAnnotation;
import org.lmmarise.transaction.springdtx.service.CustomerServiceTxInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CustomerServiceTxInAnnotation customerService;
    
    @Autowired
    private CustomerServiceTxInCode customerServiceInCode;

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("")
    @Secured("ROLE_ADMIN")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
    
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer) {
        return customerService.create(customer);
    }
    
    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer) {
        return customerServiceInCode.create(customer);
    }
    

}
