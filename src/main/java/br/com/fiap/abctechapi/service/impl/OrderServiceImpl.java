package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.AssistanceNotFoundException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void saveOrder(Order order, List<Long> listAssistances) {
        ArrayList<Assistance> assistances = new ArrayList<>();
        listAssistances.forEach(i -> {
            Assistance assistance = assistanceRepository.findById(i).orElseThrow(() -> new AssistanceNotFoundException(i));
            assistances.add(assistance);
        });

        order.setServices(assistances);

        if (!order.hasMinAssists()){
            throw new MinimumAssistsRequiredException("Invalid Assistance", "Necessário ao menos 1 assistência válida");
        }

        if (order.exceedsMaxAssists()){
            throw new MaxAssistsException("Invalid Assistance", "Número máximo de assistências permitidas são 15");
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return null;
    }
}
