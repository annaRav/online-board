package com.api.onlineboard.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferRequestDto {
    private UserRequestDto owner;
    private BigDecimal price;
    private String label;
    private String description;
    private CategoryRequestDto category;
}
