package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;

import java.util.List;

public interface OrderApplication {
    void createOrder(OrderRequestDto orderRequestDto) throws Exception;

    List<OrderResponseDto> getAllOrders();

    List<OrderResponseDto> getAllOrdersByOperator(Long operatorId);
}
