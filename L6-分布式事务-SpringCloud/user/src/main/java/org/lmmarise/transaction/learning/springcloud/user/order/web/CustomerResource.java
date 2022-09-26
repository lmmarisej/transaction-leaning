package org.lmmarise.transaction.learning.springcloud.user.order.web;

import org.lmmarise.transaction.learning.springcloud.user.service.dto.OrderDTO;
import org.lmmarise.transaction.learning.springcloud.user.order.dao.CustomerRepository;
import org.lmmarise.transaction.learning.springcloud.user.order.domain.Customer;
import org.lmmarise.transaction.learning.springcloud.user.order.feign.OrderClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    @PostConstruct
    public void init() {
        Customer customer = new Customer();
        customer.setUsername("cxk");
        customer.setPassword("111111");
        customer.setRole("User");
        customerRepository.save(customer);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("")
    public Customer create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("")
    @HystrixCommand
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/my")
    @HystrixCommand
    public Map getMyInfo() {
        Customer customer = customerRepository.findOneByUsername("cxk");
        OrderDTO order = orderClient.getMyOrder(1L);
        Map result = new HashMap();
        result.put("customer", customer);
        result.put("order", order);
        return result;
    }

}
