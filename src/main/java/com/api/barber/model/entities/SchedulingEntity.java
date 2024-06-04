package com.api.barber.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "scheduling", cascade = CascadeType.ALL)
    private List<ItemSchedulingEntity> items = new ArrayList<>();

    private Boolean enable;

    public SchedulingEntity() {
    }

    public SchedulingEntity(Long id, String description, Date date, UserEntity user, Boolean enable) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.user = user;
        this.enable = enable;
    }
}
