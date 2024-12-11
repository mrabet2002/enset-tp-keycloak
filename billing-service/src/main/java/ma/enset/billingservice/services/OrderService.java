package ma.enset.billingservice.services;

import ma.enset.billingservice.dtos.OrderDto;
import ma.enset.billingservice.entities.Order;

import java.util.List;

public interface OrderService {
    Order makeOrder(Order bill);

//    List<OrderResponseDto> findBillsByCustomerId(Long customerId);

    OrderDto getOrder(Long id);


    List<OrderDto> findAllOrders();
}
