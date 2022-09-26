package org.lmmarise.transaction.learning.springcloud.user.order.web;

import org.lmmarise.transaction.learning.springcloud.user.service.IOrderService;
import org.lmmarise.transaction.learning.springcloud.user.service.dto.OrderDTO;
import org.lmmarise.transaction.learning.springcloud.user.order.dao.OrderRepository;
import org.lmmarise.transaction.learning.springcloud.user.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderResource implements IOrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @PostConstruct
    public void init() {
        Order order = new Order();
        order.setAmount(100);
        order.setTitle("MyOrder");
        order.setDetail("jntm");
        orderRepository.save(order);
    }
    
    @GetMapping("/{id}")
    public OrderDTO getMyOrder(@PathVariable Long id) {
        Order order = orderRepository.findOne(id);
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setTitle(order.getTitle());
        dto.setDetail(order.getDetail());
        return dto;
    }


    @PostMapping("")
    public OrderDTO create(@RequestBody OrderDTO dto) {
        Order order = new Order();
        order.setAmount(dto.getAmount());
        order.setTitle(dto.getTitle());
        order.setDetail(dto.getDetail());
        order = orderRepository.save(order);
        dto.setId(order.getId());
        return dto;
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
