package br.com.fiap.abctechapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String uf;
    private String city;
    private String neighborhood;
}
