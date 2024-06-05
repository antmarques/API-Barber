package com.api.barber.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_product")
@Data
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<ItemSchedulingEntity> items = new ArrayList<>();

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
