package com.api.onlineboard.dto;

import com.api.onlineboard.model.User;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OfferResponseDto {
    private Long id;
    private User owner;
    private BigDecimal price;
    private String label;
    private String description;
    private Date createDate;
    private CategoryResponseDto category;
}
