package com.api.barber.model.dto;

import com.api.barber.model.entities.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;

@Data
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @JsonIgnore
    private Double price;

    private String priceFormat;

    private Boolean enable;

    public ProductDto() {
    }

    public ProductDto(ProductEntity entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        priceFormat = entity.getPriceFormat();
        enable = entity.getEnable();
    }
}
