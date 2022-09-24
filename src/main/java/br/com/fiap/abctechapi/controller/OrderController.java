package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception{
        orderApplication.createOrder(orderRequestDto);
        return ResponseEntity.ok().build();
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
