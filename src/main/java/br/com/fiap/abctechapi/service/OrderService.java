package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.enums.StatusEnum;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order, Long operatorId, List<Long> listAssistances);

    List<Order> getAllOrders();

    void updateOrder(Long orderId, Long status, OrderLocation location);

    List<Order> getAllOrdersByFilter(StatusEnum statusEnum, Long operatorId);
}
