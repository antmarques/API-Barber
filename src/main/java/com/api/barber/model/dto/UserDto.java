package com.api.barber.model.dto;

import com.api.barber.model.entities.UserEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private Date birthdate;

    private String birthdateFormat;

    private String password;

    private Boolean isAdm;

    private Boolean enable;

    public UserDto(UserEntity entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        birthdate = entity.getBirthdate();
        birthdateFormat = getBirthdateFormat();
        password = entity.getPassword();
        isAdm = entity.getIsAdm();
        enable = entity.getEnable();
    }

    public String getBirthdateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        birthdateFormat = sdf.format(birthdate);
        return birthdateFormat;
    }
}
