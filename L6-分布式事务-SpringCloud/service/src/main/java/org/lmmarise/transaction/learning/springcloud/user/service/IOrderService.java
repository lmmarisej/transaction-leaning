package org.lmmarise.transaction.learning.springcloud.user.service;

import org.lmmarise.transaction.learning.springcloud.user.service.dto.OrderDTO;


public interface IOrderService {

    OrderDTO create(OrderDTO dto);
    
    OrderDTO getMyOrder(Long id);
    
}
