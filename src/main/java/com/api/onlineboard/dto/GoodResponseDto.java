package com.api.onlineboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GoodResponseDto {
    private Long id;
    private String name;
    private String description;
    private CategoryResponseDto category;
    private Date createdDate;
}
