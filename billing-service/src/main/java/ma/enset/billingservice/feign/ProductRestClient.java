package ma.enset.billingservice.feign;

import ma.enset.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE", url = "http://localhost:8081")
public interface ProductRestClient {
    @GetMapping("/api/products")
    List<Product> getProducts(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    );

    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable Long id);
}
