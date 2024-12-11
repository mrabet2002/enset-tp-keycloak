package ma.enset.billingservice.services;

import lombok.RequiredArgsConstructor;
import ma.enset.billingservice.dtos.OrderDto;
import ma.enset.billingservice.dtos.ProductItemDto;
import ma.enset.billingservice.entities.Order;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.enums.OrderStatus;
import ma.enset.billingservice.feign.ProductRestClient;
import ma.enset.billingservice.mappers.OrderMapper;
import ma.enset.billingservice.mappers.ProductItemMapper;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.repos.OrderRepository;
import ma.enset.billingservice.repos.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductRestClient productRestClient;


    @Override
    public Order makeOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);

        order.getProductItems().forEach(productItem -> {
            Product product = productRestClient.getProductById(productItem.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found");
            }
            productItem.setProductId(product.getId());
            productItem.setQuantity(productItem.getQuantity());
            productItem.setPrice(product.getPrice());
            productItem.setOrder(savedOrder);

            productItemRepository.save(productItem);
        });

        return orderRepository.save(order);
    }

//    @Override
//    public List<OrderResponseDto> findBillsByCustomerId(Long customerId) {
//        List<Order> orders = orderRepository.findByCustomerId(customerId);
//        return OrderMapper.toDto(orders);
//    }

    @Override
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDto orderDto = OrderMapper.toOrderDto(order);
//        List<ProductItem> productItems = productItemRepository.findByOrderId(orderId);
        List<ProductItem> items = order.getProductItems();
        List<ProductItemDto> itemsDto = items.stream().map(productItem -> {
            Product product = productRestClient.getProductById(productItem.getProductId());
            productItem.setProduct(product);
            return ProductItemMapper.toDto(productItem);
        }).toList();
        orderDto.setProductItems(itemsDto);
        return orderDto;
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return OrderMapper.toOrderDto(orderRepository.findAll());
    }


}
