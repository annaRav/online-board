package com.api.onlineboard.service.mapper;

import com.api.onlineboard.dto.CategoryRequestDto;
import com.api.onlineboard.dto.CategoryResponseDto;
import com.api.onlineboard.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toModel(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public CategoryResponseDto toResponseDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setCreatedDate(category.getCreatedDate());
        return dto;
    }
}
