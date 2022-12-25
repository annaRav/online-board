package com.api.onlineboard.service.mapper;

import com.api.onlineboard.dto.GoodRequestDto;
import com.api.onlineboard.dto.GoodResponseDto;
import com.api.onlineboard.model.Good;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoodMapper {
    private final CategoryMapper categoryMapper;

    public Good toModel(GoodRequestDto dto) {
        Good good = new Good();
        good.setName(dto.getName());
        good.setDescription(dto.getDescription());
        good.setCategory(categoryMapper.toModel(dto.getCategory()));
        good.setCreatedDate(dto.getCreatedDate());
        return good;
    }

    public GoodResponseDto toResponseDto(Good good) {
        GoodResponseDto dto = new GoodResponseDto();
        dto.setId(good.getId());
        dto.setName(good.getName());
        dto.setDescription(good.getDescription());
        dto.setCategory(categoryMapper.toResponseDto(good.getCategory()));
        dto.setCreatedDate(good.getCreatedDate());
        return dto;
    }
}
