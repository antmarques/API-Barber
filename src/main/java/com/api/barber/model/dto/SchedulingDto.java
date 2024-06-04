package com.api.barber.model.dto;

import com.api.barber.model.entities.SchedulingEntity;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SchedulingDto {

    private Long id;

    private String description;

    private Date date;

    private String dateFormat;

    private UserDto user;

    private ProductDto product;

    private Boolean enable;

    public SchedulingDto(SchedulingEntity entity) {
        id = entity.getId();
        description = entity.getDescription();
        date = entity.getDate();
        user = new UserDto(entity.getUser());
        product = new ProductDto(entity.getProduct());
        enable = entity.getEnable();
    }

    public String getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat = sdf.format(date);
        return dateFormat;
    }
}
