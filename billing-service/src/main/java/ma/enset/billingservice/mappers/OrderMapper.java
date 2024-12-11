package ma.enset.billingservice.mappers;

import ma.enset.billingservice.dtos.OrderDto;
import ma.enset.billingservice.dtos.OrderResponseDto;
import ma.enset.billingservice.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponseDto toDto(Order bill) {
        return OrderResponseDto.builder()
                .id(bill.getId())
                .createdAt(bill.getCreatedAt())
                .status(bill.getStatus())
                .build();
    }

    public static List<OrderResponseDto> toDto(List<Order> bills) {
        return bills.stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    public static OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .status(order.getStatus())
//                .productItems(order.getProductItems().stream().map(ProductItemMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public static List<OrderDto> toOrderDto(List<Order> orders) {
        return orders.stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
    }
}
