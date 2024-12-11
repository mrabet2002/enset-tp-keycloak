package ma.enset.billingservice;

import ma.enset.billingservice.entities.Order;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.feign.ProductRestClient;
import ma.enset.billingservice.repos.ProductItemRepository;
import ma.enset.billingservice.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ProductItemRepository productItemRepository,
            ProductRestClient productRestClient,
            OrderService orderService
    ) {
        return args -> {
            List<Long> productIds = List.of(1L, 2L, 3L);

            for (int i = 0; i < 10; i++) {
                orderService.makeOrder(Order.builder()
                        .productItems(productIds.stream().map(productId -> ProductItem.builder()
                                .productId(productId)
                                .quantity((long) (Math.random() * 100))
                                .build()).collect(Collectors.toList()))
                        .build());
            }
        };
    }
}
