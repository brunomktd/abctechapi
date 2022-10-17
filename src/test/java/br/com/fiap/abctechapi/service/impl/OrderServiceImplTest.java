package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.enums.StatusEnum;
import br.com.fiap.abctechapi.handler.exception.AssistanceNotFoundException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechapi.handler.exception.StatusOrderException;
import br.com.fiap.abctechapi.model.*;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.ClientService;
import br.com.fiap.abctechapi.service.OperatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistanceRepository;
    @Mock
    private OperatorService operatorService;
    @Mock
    private ClientService clientService;

    @Captor
    private ArgumentCaptor<Order> captor;

    @Test
    public void shouldCreateOrderWhenMethodCalled(){
        Order orderMock = Order.builder()
                .client(Client.builder().id(1L).build())
                .build();

        Mockito.when(operatorService.getOperatorById(Mockito.any())).thenReturn(Mockito.mock(Operator.class));
        Mockito.when(clientService.getClientById(Mockito.any())).thenReturn(Mockito.mock(Client.class));
        Mockito.when(assistanceRepository.findById(Mockito.any())).thenReturn(Optional.of(Mockito.mock(Assistance.class)));
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(Mockito.mock(Order.class));

        service.saveOrder(orderMock, 1L, Collections.singletonList(1L));

        Mockito.verify(orderRepository, times(1)).save(Mockito.any());
    }

    @Test
    public void shouldThrowAssistanceNotFoundWhenAssistanceDoesNotExists(){
        Order orderMock = Order.builder()
                .client(Client.builder().id(1L).build())
                .build();

        Mockito.when(operatorService.getOperatorById(Mockito.any())).thenReturn(Mockito.mock(Operator.class));
        Mockito.when(clientService.getClientById(Mockito.any())).thenReturn(Mockito.mock(Client.class));
        Mockito.when(assistanceRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(AssistanceNotFoundException.class, () -> service.saveOrder(orderMock, 1L, Collections.singletonList(1L)));
    }

    @Test
    public void shouldThrowMinimumAssistRequiredWhenTotalAssistsIsZero(){
        Order orderMock = Order.builder()
                .client(Client.builder().id(1L).build())
                .build();

        Mockito.when(operatorService.getOperatorById(Mockito.any())).thenReturn(Mockito.mock(Operator.class));
        Mockito.when(clientService.getClientById(Mockito.any())).thenReturn(Mockito.mock(Client.class));

        Assertions.assertThrows(MinimumAssistsRequiredException.class, () -> service.saveOrder(orderMock, 1L, Collections.emptyList()));
    }

    @Test
    public void shouldThrowMaxAssistsExceptionWhenTotalAssistancesIsMoreThanFifteen(){
        Order orderMock = Order.builder()
                .client(Client.builder().id(1L).build())
                .build();

        ArrayList<Long> listAssistances = new ArrayList<>();

        for (long i = 0; i < 16; i++) {
            listAssistances.add(i);
        }

        Mockito.when(operatorService.getOperatorById(Mockito.any())).thenReturn(Mockito.mock(Operator.class));
        Mockito.when(clientService.getClientById(Mockito.any())).thenReturn(Mockito.mock(Client.class));
        Mockito.when(assistanceRepository.findById(Mockito.any())).thenReturn(Optional.of(Mockito.mock(Assistance.class)));

        Assertions.assertThrows(MaxAssistsException.class, () -> service.saveOrder(orderMock, 1L, listAssistances));
    }

    @Test
    public void shouldGetAllOrders() {
        Mockito.when(orderRepository.findAll()).thenReturn(Collections.singletonList(Mockito.mock(Order.class)));
        List<Order> allOrders = service.getAllOrders();
        Assertions.assertEquals(1, allOrders.size());
    }

    @Test
    public void shouldGetAllOrdersFromFilter() {
        Mockito.when(orderRepository.findAllOrder(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Collections.singletonList(Mockito.mock(Order.class)));

        List<Order> allOrders = service.getAllOrdersByFilter(StatusEnum.ANDAMENTO.ordinal(), 1L);
        Assertions.assertEquals(1, allOrders.size());
    }

    @Test
    public void shouldThrowErrorWhenStatusOrderHasStatusConcluido(){
        Order orderMock = Order.builder()
                .status(StatusEnum.CONCLUIDO)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));
        Assertions.assertThrows(StatusOrderException.class, () -> service.updateOrder(1L, (long) StatusEnum.PENDENTE.ordinal(), OrderLocation.builder().build()));
    }

    @Test
    public void shouldThrowErrorWhenStatusOrderHasStatusCancelado(){
        Order orderMock = Order.builder()
                .status(StatusEnum.CANCELADO)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));
        Assertions.assertThrows(StatusOrderException.class, () -> service.updateOrder(1L, (long) StatusEnum.PENDENTE.ordinal(), OrderLocation.builder().build()));
    }

    @Test
    public void shouldUpdateStatusToCanceladoWhenStatusOrderToBePendente(){
        Order orderMock = Order.builder()
                .status(StatusEnum.PENDENTE)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));

        service.updateOrder(1L, (long) StatusEnum.CANCELADO.ordinal(), OrderLocation.builder().build());

        Mockito.verify(orderRepository).save(captor.capture());

        Order captorValue = captor.getValue();

        Assertions.assertEquals(StatusEnum.CANCELADO, captorValue.getStatus());
    }

    @Test
    public void shouldUpdateStatusToCanceladoWhenStatusOrderToBeAndamento(){
        Order orderMock = Order.builder()
                .status(StatusEnum.ANDAMENTO)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));

        service.updateOrder(1L, (long) StatusEnum.CANCELADO.ordinal(), OrderLocation.builder().build());

        Mockito.verify(orderRepository).save(captor.capture());

        Order captorValue = captor.getValue();

        Assertions.assertEquals(StatusEnum.CANCELADO, captorValue.getStatus());
    }

    @Test
    public void shouldUpdateStatusToAndamentoWhenStatusOrderToBePendente(){
        Order orderMock = Order.builder()
                .status(StatusEnum.PENDENTE)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));

        service.updateOrder(1L, (long) StatusEnum.ANDAMENTO.ordinal(), OrderLocation.builder().build());

        Mockito.verify(orderRepository).save(captor.capture());

        Order captorValue = captor.getValue();

        Assertions.assertEquals(StatusEnum.ANDAMENTO, captorValue.getStatus());
    }

    @Test
    public void shouldUpdateStatusToConcluidoWhenStatusOrderToBeAndamento(){
        Order orderMock = Order.builder()
                .status(StatusEnum.ANDAMENTO)
                .build();
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(orderMock));

        service.updateOrder(1L, (long) StatusEnum.CONCLUIDO.ordinal(), OrderLocation.builder().build());

        Mockito.verify(orderRepository).save(captor.capture());

        Order captorValue = captor.getValue();

        Assertions.assertEquals(StatusEnum.CONCLUIDO, captorValue.getStatus());
    }

}