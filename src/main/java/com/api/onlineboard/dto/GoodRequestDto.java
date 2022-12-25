package com.api.onlineboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GoodRequestDto {
    private String name;
    private String description;
    private CategoryRequestDto category;
    private Date createdDate;
}
