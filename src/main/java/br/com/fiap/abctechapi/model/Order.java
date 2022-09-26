package br.com.fiap.abctechapi.model;

import br.com.fiap.abctechapi.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany
    private List<Assistance> services;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "start_order_location_id", foreignKey = @ForeignKey(name = "FK_start_order_id"))
    private OrderLocation start;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "end_order_location_id", foreignKey = @ForeignKey(name = "FK_end_order_id"))
    private OrderLocation end;
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false, foreignKey = @ForeignKey(name = "FK_operator_id"))
    private Operator operator;

    public boolean hasMinAssists(){
        return !this.services.isEmpty();
    }

    public boolean exceedsMaxAssists(){
        return this.services.size() > 15;
    }
}
