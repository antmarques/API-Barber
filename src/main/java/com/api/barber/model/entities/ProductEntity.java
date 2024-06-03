package com.api.barber.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_product")
@Data
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_id_seq")
    private Long id;

    private String name;

    private Double price;

    private Boolean enable;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Double price, Boolean enable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.enable = enable;
    }
}
