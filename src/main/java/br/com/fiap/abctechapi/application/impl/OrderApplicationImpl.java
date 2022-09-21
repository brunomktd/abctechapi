package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderApplicationImpl implements OrderApplication {

    @Autowired
    private OrderService orderService;

    @Override
    public void createOrder(OrderDto orderDto) throws Exception {
        Order order = Order.builder()
                .operationId(orderDto.getOperatorId())
                .start(getOrderLocationFromLocationDto(orderDto.getStart()))
                .end(getOrderLocationFromLocationDto(orderDto.getEnd()))
                .build();
        orderService.saveOrder(order, orderDto.getServices());
    }

    private OrderLocation getOrderLocationFromLocationDto(OrderLocationDto orderLocationDto){
        return OrderLocation.builder()
                .latitude(orderLocationDto.getLatitude())
                .longitude(orderLocationDto.getLongitude())
                .date(orderLocationDto.getDateTime())
                .build();
    }
}
