package com.ecom.order;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> status = new HashMap<>();
        // Fixed: Use .put() instead of .setStatus()/.setComponent()
        status.put("status", "UP");
        status.put("component", "order-service");
        return status;
    }

    @PostMapping
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> orderData) {
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", "ORD-" + System.currentTimeMillis());
        response.put("status", "PLACED");
        response.put("details", orderData);
        return response;
    }
}
