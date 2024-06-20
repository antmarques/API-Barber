package com.api.barber.model.services.utils;

import com.api.barber.model.dto.ItemSchedulingDto;
import com.api.barber.model.entities.ItemSchedulingEntity;

public class ItemSchedulingUtil {

    public static ItemSchedulingDto convertToDto(ItemSchedulingEntity entity) {
        ItemSchedulingDto dto = new ItemSchedulingDto();
        dto.setId(entity.getId());
        dto.setProduct(ProductUtil.convertToDto(entity.getProduct()));
        dto.setPrice(entity.getPrice());
        dto.setPriceFormat(NumberUtil.numberFormatBr(entity.getPrice()));
        dto.setQuantity(entity.getQuantity());
        dto.setEnable(entity.getEnable());
        return dto;
    }

}
