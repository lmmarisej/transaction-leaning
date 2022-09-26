package org.lmmarise.transaction.learning.springcloud.user.order.feign;

import org.lmmarise.transaction.learning.springcloud.user.service.IOrderService;
import org.lmmarise.transaction.learning.springcloud.user.service.dto.OrderDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "order", path = "/order/api/order")
public interface OrderClient extends IOrderService {

    @GetMapping("/{id}")
    OrderDTO getMyOrder(@PathVariable(name = "id") Long id);

    @PostMapping("")
    OrderDTO create(@RequestBody OrderDTO dto);
    
}
