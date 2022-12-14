package org.lmmarise.transaction.springtx.web;

import org.lmmarise.transaction.springtx.dao.CustomerRepository;
import org.lmmarise.transaction.springtx.domain.Customer;
import org.lmmarise.transaction.springtx.service.CustomerServiceTxInAnnotation;
import org.lmmarise.transaction.springtx.service.CustomerServiceTxInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private CustomerServiceTxInAnnotation customerService;
    @Autowired
    private CustomerServiceTxInCode customerServiceInCode;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer) {
        return customerServiceInCode.create(customer);
    }

    @Transactional
    @PostMapping("/message/annotation")
    public void createMsgWithListener(@RequestParam String userName) {
        jmsTemplate.convertAndSend("customer:msg:new", userName);
    }

    @Transactional
    @PostMapping("/message/code")
    public void createMsgDirect(@RequestParam String userName) {
        jmsTemplate.convertAndSend("customer:msg2:new", userName);
    }

    @GetMapping("")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

}
