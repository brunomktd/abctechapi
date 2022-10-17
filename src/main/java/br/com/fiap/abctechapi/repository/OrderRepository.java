package br.com.fiap.abctechapi.repository;

import br.com.fiap.abctechapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o.*\n" +
            "from orders as o\n" +
            "where if(?1 is not null, o.status = ?1, o.status >= 0)\n" +
            "and if(?2 is not null, o.operator_id = ?2, o.operator_id >= 0);", nativeQuery = true)
    List<Order> findAllOrder(int status, Long operatorId);
}
