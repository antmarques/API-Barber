package com.api.barber.model.services.utils;

import com.api.barber.model.dto.ProductDto;
import com.api.barber.model.entities.ProductEntity;

public class ProductUtil {

    public static ProductDto convertToDto(ProductEntity entity){
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setPriceFormat(NumberUtil.numberFormatBr(entity.getPrice()));
        dto.setEnable(entity.getEnable());
        return dto;
    }

}
