package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderApplication application;

    @Test
    void createOrder() {
        Mockito.doNothing().when(application).createOrder(Mockito.any());
        ResponseEntity<Object> response = controller.createOrder(Mockito.mock(OrderRequestDto.class));
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void updateStatus() {
        Mockito.doNothing().when(application).updateOrder(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());
        ResponseEntity<Object> response = controller.updateStatus(1L, 1L, Mockito.mock(OrderLocationDto.class));
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getAllOrdersWithParams() {
        Mockito.when(application.getAllOrdersByFilter(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Collections.singletonList(OrderResponseDto.builder().orderId(1L).build()));

        ResponseEntity<List<OrderResponseDto>> response = controller.getAllOrders("1", 1L);

        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).get(0).getOrderId());
    }

    @Test
    void getAllOrdersWithoutParams() {
        Mockito.when(application.getAllOrders())
                .thenReturn(Collections.singletonList(OrderResponseDto.builder().orderId(1L).build()));

        ResponseEntity<List<OrderResponseDto>> response = controller.getAllOrders(null, null);

        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).get(0).getOrderId());
    }
}