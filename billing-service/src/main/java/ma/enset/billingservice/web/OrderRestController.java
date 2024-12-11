package ma.enset.billingservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.billingservice.dtos.OrderDto;
import ma.enset.billingservice.entities.Order;
import ma.enset.billingservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @PostMapping("/customer")
    public ResponseEntity<Order> save(
            @RequestBody Order bill
    ){
        return ResponseEntity.ok(orderService.makeOrder(bill));
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<?> getBillsByCustomerId(@PathVariable Long customerId){
//        return ResponseEntity.ok(orderService.findBillsByCustomerId(customerId));
//    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getBillProductItems(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
}
