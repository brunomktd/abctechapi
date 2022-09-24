package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
import br.com.fiap.abctechapi.application.dto.OrderRequestDto;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AssistanceApplication assistanceApplication;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) throws Exception {
        Order order = Order.builder()
                .operationId(Long.valueOf(UUID.randomUUID().toString()))
                .start(getOrderLocationFromLocationDto(orderRequestDto.getStart()))
                .end(getOrderLocationFromLocationDto(orderRequestDto.getEnd()))
                .build();
        orderService.saveOrder(order, orderRequestDto.getOperatorId(), orderRequestDto.getServices());
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return allOrders.stream().map(this::convertOrderToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByOperator(Long operatorId) {
        List<Order> allOrders = orderService.listOrderByOperator(operatorId);
        return allOrders.stream().map(this::convertOrderToOrderResponseDto).collect(Collectors.toList());
    }

    private OrderResponseDto convertOrderToOrderResponseDto(Order order) {
        return OrderResponseDto.builder()
                .operatorId(order.getOperationId())
                .services(getServices(order.getServices()))
                .start(convertOrderLocationToOrderLocationDto(order.getStart()))
                .end(convertOrderLocationToOrderLocationDto(order.getEnd()))
                .build();
    }

    private OrderLocationDto convertOrderLocationToOrderLocationDto(OrderLocation orderLocation) {
        return OrderLocationDto.builder()
                .latitude(orderLocation.getLatitude())
                .longitude(orderLocation.getLongitude())
                .dateTime(orderLocation.getDate())
                .build();
    }

    private List<AssistanceResponseDto> getServices(List<Assistance> services) {
        return services.stream().map(i -> this.assistanceApplication.convertEntityToAssistanceResponseDto(i)).collect(Collectors.toList());
    }

    private OrderLocation getOrderLocationFromLocationDto(OrderLocationDto orderLocationDto){
        return OrderLocation.builder()
                .latitude(orderLocationDto.getLatitude())
                .longitude(orderLocationDto.getLongitude())
                .date(orderLocationDto.getDateTime())
                .build();
    }
}
