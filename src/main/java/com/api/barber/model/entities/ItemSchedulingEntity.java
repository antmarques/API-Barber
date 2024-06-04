package com.api.barber.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_item_scheduling")
@Data
public class ItemSchedulingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_scheduling_seq")
    @SequenceGenerator(name = "item_scheduling_seq_gen", sequenceName = "item_scheduling_id_seq")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_scheduling")
    private SchedulingEntity scheduling;

    private Integer quantity;

    private Double price;

    private Boolean enable;

    public ItemSchedulingEntity() {
    }

    public ItemSchedulingEntity(Long id, ProductEntity product, SchedulingEntity scheduling, Integer quantity, Double price, Boolean enable) {
        this.id = id;
        this.product = product;
        this.scheduling = scheduling;
        this.quantity = quantity;
        this.price = price;
        this.enable = enable;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
