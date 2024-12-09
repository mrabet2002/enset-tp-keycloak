package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repos.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(
            ProductRepository productRepository
    ) {
        return args -> {
            List<String> productsNames = List.of("Ordinateur", "Imprimante", "Smartphone");

            productsNames.forEach(productName -> {
                productRepository.save(
                        Product.builder()
                                .name(productName)
                                .price(Math.random() * 10000)
                                .quantity(Math.random() * 100)
                                .build()
                );
            });
        };
    }
}
