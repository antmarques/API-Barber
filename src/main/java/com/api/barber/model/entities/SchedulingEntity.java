package com.api.barber.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_scheduling")
@Data
public class SchedulingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduling_seq")
    @SequenceGenerator(name = "scheduling_seq_gen", sequenceName = "scheduling_id_seq")
    private Long id;

    private String description;

    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    private Boolean enable;

    public SchedulingEntity() {
    }

    public SchedulingEntity(Long id, String description, Date date, ProductEntity product, UserEntity user, Boolean enable) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.product = product;
        this.user = user;
        this.enable = enable;
    }
}
