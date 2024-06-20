package com.api.barber.model.services.utils;

import com.api.barber.model.dto.ItemSchedulingDto;
import com.api.barber.model.dto.SchedulingDto;
import com.api.barber.model.entities.ItemSchedulingEntity;
import com.api.barber.model.entities.SchedulingEntity;
import java.util.ArrayList;
import java.util.List;

public class SchedulingUtil {

    public static SchedulingDto convertToDto(SchedulingEntity entity){
        SchedulingDto dto = new SchedulingDto();
        List<ItemSchedulingDto> itemListDto = new ArrayList<>();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setDateFormat(DateUtil.toStringDateTimeFormat(entity.getDate()));
        dto.setDescription(entity.getDescription());
        dto.setEnable(entity.getEnable());
        dto.setUser(UserUtil.convertToDto(entity.getUser()));
        for (ItemSchedulingEntity iEntity: entity.getItems()) {
            itemListDto.add(ItemSchedulingUtil.convertToDto(iEntity));
        }
        dto.setItems(itemListDto);
        return dto;
    }

}
