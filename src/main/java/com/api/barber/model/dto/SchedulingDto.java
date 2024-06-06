package com.api.barber.model.dto;

import com.api.barber.model.entities.SchedulingEntity;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SchedulingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String description;

    private Date date;

    private String dateFormat;

    private UserDto user;

    private List<ItemSchedulingDto> list = new ArrayList<>();

    private Boolean enable;

    public SchedulingDto() {
    }

    public SchedulingDto(SchedulingEntity entity) {
        id = entity.getId();
        description = entity.getDescription();
        date = entity.getDate();
        user = new UserDto(entity.getUser());
        enable = entity.getEnable();
    }

    public String getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat = sdf.format(date);
        return dateFormat;
    }
}
