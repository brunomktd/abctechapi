package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order, List<Long> listAssistances) throws Exception;

    List<Order> listOrderByOperator(Long operatorId);
}
