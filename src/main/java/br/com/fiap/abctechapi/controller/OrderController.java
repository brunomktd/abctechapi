package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        orderApplication.createOrder(orderRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}")
    public void updateStatus(@PathVariable("orderId") Long orderId,
                             @RequestParam("status") Long status,
                             @RequestBody OrderLocationDto locationDto){
        orderApplication.updateOrder(orderId, status, locationDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> allOrders = orderApplication.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<List<OrderResponseDto>> getAllOrdersByOperator(@PathVariable("operatorId") Long operatorId) {
        List<OrderResponseDto> allOrders = orderApplication.getAllOrdersByOperator(operatorId);
        return ResponseEntity.ok(allOrders);
    }
}
