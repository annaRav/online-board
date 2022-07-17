package com.api.onlineboard.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
}
