package com.api.barber.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_scheduling")
@Data
public class SchedulingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @JsonIgnore
    private Date date;

    @Transient
    private String dateFormat;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "scheduling", cascade = CascadeType.ALL)
    private List<ItemSchedulingEntity> items;

    private Boolean enable;

    public SchedulingEntity() {
    }

    public SchedulingEntity(Long id, String description, Date date, String dateFormat, UserEntity user, Boolean enable) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.dateFormat = dateFormat;
        this.user = user;
        this.enable = enable;
    }
}
