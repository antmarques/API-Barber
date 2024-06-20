package com.api.barber.model.dto;

import com.api.barber.model.entities.SchedulingEntity;
import com.api.barber.model.services.utils.UserUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
public class SchedulingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String description;

    @JsonIgnore
    private Date date;

    private String dateFormat;

    private UserDto user;

    private List<ItemSchedulingDto> items;

    private Boolean enable;

    public SchedulingDto() {
    }

    public SchedulingDto(SchedulingEntity entity) {
        id = entity.getId();
        description = entity.getDescription();
        date = entity.getDate();
        dateFormat = entity.getDateFormat();
        user = UserUtil.convertToDto(entity.getUser());
        enable = entity.getEnable();
    }
}
