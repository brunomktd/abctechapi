package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderApplication.createOrder(orderRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}")
    public void updateStatus(@PathVariable("orderId") Long orderId,
                             @RequestParam("status") Long status,
                             @Valid @RequestBody OrderLocationDto locationDto) {
        orderApplication.updateOrder(orderId, status, locationDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@RequestParam(name = "status", required = false) String status,
                                                               @RequestParam(name = "operatorId", required = false) Long operatorId) {
        List<OrderResponseDto> allOrders;

        if (status != null || operatorId != null) {
            allOrders = orderApplication.getAllOrdersByFilter(status, operatorId);
        } else {
            allOrders = orderApplication.getAllOrders();
        }
        return ResponseEntity.ok(allOrders);
    }

}
