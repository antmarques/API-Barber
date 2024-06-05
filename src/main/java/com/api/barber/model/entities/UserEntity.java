package com.api.barber.model.entities;

import com.api.barber.model.services.utils.DateUtil;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Date birthdate;

    @Transient
    private String birthdateFormat;

    private String password;

    private Boolean isAdm;

    private Boolean enable;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SchedulingEntity> shedulings = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String email, Date birthdate, String password, Boolean isAdm, Boolean enable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.password = password;
        this.isAdm = isAdm;
        this.enable = enable;
    }
}
