package com.api.barber.model.dto;

import com.api.barber.model.entities.ItemSchedulingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class ItemSchedulingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private ProductDto product;

    @JsonIgnore
    private SchedulingDto scheduling;

    private Integer quantity;

    @JsonIgnore
    private Double price;

    private String priceFormat;

    private Boolean enable;

    public ItemSchedulingDto() {
    }

    public ItemSchedulingDto(ItemSchedulingEntity entity) {
        id = entity.getId();
        product = new ProductDto(entity.getProduct());
        scheduling = new SchedulingDto(entity.getScheduling());
        quantity = entity.getQuantity();
        price = product.getPrice();
        priceFormat = entity.getPriceFormat();
        enable = entity.getEnable();
    }
}
