package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.enums.StatusEnum;
import br.com.fiap.abctechapi.handler.exception.*;
import br.com.fiap.abctechapi.model.*;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.ClientService;
import br.com.fiap.abctechapi.service.OperatorService;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    private final OperatorService operatorService;
    private final ClientService clientService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AssistanceRepository assistanceRepository, OperatorService operatorService, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
        this.operatorService = operatorService;
        this.clientService = clientService;
    }


    @Override
    public void saveOrder(Order order, Long operatorId, List<Long> listAssistances) {
        verifyOperatorId(order, operatorId);
        verifyClient(order);
        verifyAndCreateAssistances(order, listAssistances);
        order.setStatus(StatusEnum.PENDENTE);
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    private void verifyClient(Order order) {
        Client client = clientService.getClientById(order.getClient().getId());
        order.setClient(client);
    }

    private void verifyOperatorId(Order order, Long operatorId) {
        Operator operatorById = operatorService.getOperatorById(operatorId);
        order.setOperator(operatorById);
    }

    private void verifyAndCreateAssistances(Order order, List<Long> listAssistances) throws MinimumAssistsRequiredException, MaxAssistsException {
        ArrayList<Assistance> assistances = new ArrayList<>();
        listAssistances.forEach(i -> {
            Assistance assistance = assistanceRepository.findById(i).orElseThrow(() -> new AssistanceNotFoundException(i));
            assistances.add(assistance);
        });

        order.setServices(assistances);

        if (!order.hasMinAssists()) {
            throw new MinimumAssistsRequiredException("Invalid Assistance", "Necessário ao menos 1 assistência válida");
        }

        if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException("Invalid Assistance", "Número máximo de assistências permitidas são 15");
        }
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return this.orderRepository.findAllByOperatorId(operatorId);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public void updateOrder(Long orderId, Long status, OrderLocation location) {
        Order order = getOrderById(orderId);

        if (order.getStatus().equals(StatusEnum.CONCLUIDO) || order.getStatus().equals(StatusEnum.CANCELADO)){
            throw new StatusOrderException(HttpStatus.BAD_REQUEST, "Order ID is already finalized");
        }

        if (status == StatusEnum.CANCELADO.ordinal() && !order.getStatus().equals(StatusEnum.ANDAMENTO)) {
            order.setStatus(StatusEnum.CANCELADO);
        }

        if (status == StatusEnum.ANDAMENTO.ordinal() && !order.getStatus().equals(StatusEnum.ANDAMENTO)) {
            order.setStatus(StatusEnum.ANDAMENTO);
            order.setStart(location);
        }

        if (status == StatusEnum.CONCLUIDO.ordinal() && order.getStatus().equals(StatusEnum.ANDAMENTO)) {
            order.setStatus(StatusEnum.CONCLUIDO);
            order.setEnd(location);
        }

        orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order Id doesn't exists", "Order id não existe");
        }
        return orderOptional.get();
    }
}
