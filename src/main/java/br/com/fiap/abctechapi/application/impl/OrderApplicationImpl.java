package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.*;
import br.com.fiap.abctechapi.enums.StatusEnum;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Client;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AssistanceApplication assistanceApplication;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .client(Client.builder().id(orderRequestDto.getClientId()).build())
                .build();
        orderService.saveOrder(order, orderRequestDto.getOperatorId(), orderRequestDto.getServices());
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return allOrders.stream().map(this::convertOrderToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByFilter(String status, Long operatorId) {
        StatusEnum statusEnum = StatusEnum.getStatusEnumByCode(status);
        List<Order> allOrders = orderService.getAllOrdersByFilter(statusEnum.ordinal(), operatorId);
        return allOrders.stream().map(this::convertOrderToOrderResponseDto).collect(Collectors.toList());
    }

    @Override
    public void updateOrder(Long orderId, Long status, OrderLocationDto locationDto) {
        OrderLocation location = OrderLocation.builder()
                .longitude(locationDto.getLongitude())
                .latitude(locationDto.getLatitude())
                .date(new Date())
                .build();
        orderService.updateOrder(orderId, status, location);
    }

    private OrderResponseDto convertOrderToOrderResponseDto(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .operatorId(order.getOperator().getId())
                .client(ClientResponseDto.builder()
                        .id(order.getClient().getId())
                        .name(order.getClient().getName())
                        .build())
                .services(getServices(order.getServices()))
                .status(order.getStatus())
                .start(convertOrderLocationToOrderLocationDto(order.getStart()))
                .end(convertOrderLocationToOrderLocationDto(order.getEnd()))
                .createdAt(order.getCreatedAt())
                .build();
    }

    private OrderLocationDto convertOrderLocationToOrderLocationDto(OrderLocation orderLocation) {
        if (Objects.isNull(orderLocation)) {
            return null;
        }

        return OrderLocationDto.builder()
                .latitude(orderLocation.getLatitude())
                .longitude(orderLocation.getLongitude())
                .dateTime(orderLocation.getDate())
                .build();
    }

    private List<AssistanceResponseDto> getServices(List<Assistance> services) {
        return services.stream().map(i -> this.assistanceApplication.convertEntityToAssistanceResponseDto(i)).collect(Collectors.toList());
    }

}
