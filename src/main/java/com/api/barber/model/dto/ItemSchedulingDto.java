package com.api.barber.model.dto;

import com.api.barber.model.entities.ItemSchedulingEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ItemSchedulingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private ProductDto product;

    private SchedulingDto scheduling;

    private Integer quantity;

    private Double price;

    private String priceFormat;

    private Boolean enable;

    public ItemSchedulingDto(ItemSchedulingEntity entity) {
        id = entity.getId();
        product = new ProductDto(entity.getProduct());
        scheduling = new SchedulingDto(entity.getScheduling());
        quantity = entity.getQuantity();
        price = entity.getPrice();
        priceFormat = getPriceFormat();
        enable = entity.getEnable();
    }

    public String getPriceFormat() {
        return NumberFormat.getCurrencyInstance(Locale.of("pt", "BR")).format(price);
    }
}
