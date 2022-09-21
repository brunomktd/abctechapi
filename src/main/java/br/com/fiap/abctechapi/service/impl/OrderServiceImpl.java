package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AssistanceRepository assistanceRepository) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }


    @Override
    public void saveOrder(Order order, List<Long> listAssistances) throws Exception {
        ArrayList<Assistance> assistances = new ArrayList<>();
        listAssistances.forEach(i -> {
            Assistance assistance = assistanceRepository.findById(i).orElseThrow();
            assistances.add(assistance);
        });
        order.setServices(assistances);

        if (!order.hasMinAssists() || order.exceedsMaxAssists()){
            throw new Exception();
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return null;
    }
}
