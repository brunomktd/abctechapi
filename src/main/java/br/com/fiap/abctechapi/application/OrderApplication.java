package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;

import java.util.List;

public interface OrderApplication {
    void createOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getAllOrders();

    void updateOrder(Long orderId, Long status, OrderLocationDto locationDto);

    List<OrderResponseDto> getAllOrdersByFilter(String status, Long operatorId);
}
