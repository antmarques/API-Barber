package com.api.barber.model.dto;

import com.api.barber.model.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private Date birthdate;

    private String birthdateFormat;

    private String password;

    @JsonIgnore
    private List<SchedulingDto> schedulingList;

    private Boolean isAdm;

    private Boolean enable;

    public UserDto() {
    }

    public UserDto(UserEntity entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        password = entity.getPassword();
        birthdate = entity.getBirthdate();
        birthdateFormat = entity.getBirthdateFormat();
        isAdm = entity.getIsAdm();
        enable = entity.getEnable();
    }
}
