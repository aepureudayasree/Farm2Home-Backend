package com.farm2home.farm2home.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm2home.farm2home.model.Order;
import com.farm2home.farm2home.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5178",
        "http://localhost:5182"
})
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {

        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PLACED");
        }

        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(
            @PathVariable Long id,
            @RequestBody Order order
    ) {

        Order existingOrder =
                orderRepository.findById(id).orElse(null);

        if (existingOrder == null) {
            return null;
        }

        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setPhone(order.getPhone());
        existingOrder.setAddress(order.getAddress());
        existingOrder.setCity(order.getCity());
        existingOrder.setPincode(order.getPincode());
        existingOrder.setPaymentMethod(order.getPaymentMethod());
        existingOrder.setProductName(order.getProductName());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {

        if (!orderRepository.existsById(id)) {
            return "Order not found";
        }

        orderRepository.deleteById(id);

        return "Order deleted successfully";
    }
}