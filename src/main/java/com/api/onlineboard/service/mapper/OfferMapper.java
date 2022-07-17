package com.api.onlineboard.service.mapper;

import com.api.onlineboard.dto.OfferRequestDto;
import com.api.onlineboard.dto.OfferResponseDto;
import com.api.onlineboard.model.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferMapper {
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    public Offer toModel(OfferRequestDto dto) {
        Offer offer = new Offer();
        offer.setLabel(dto.getLabel());
        offer.setDescription(dto.getDescription());
        offer.setOwner(userMapper.toModel(dto.getOwner()));
        offer.setCategory(categoryMapper.toModel(dto.getCategory()));
        offer.setPrice(dto.getPrice());
        return offer;
    }

    public OfferResponseDto toResponseDto(Offer offer) {
        OfferResponseDto dto = new OfferResponseDto();
        dto.setId(offer.getId());
        dto.setLabel(offer.getLabel());
        dto.setDescription(offer.getDescription());
        dto.setOwner(offer.getOwner());
        dto.setPrice(offer.getPrice());
        dto.setCreateDate(offer.getCreatedDate());
        dto.setCategory(categoryMapper.toResponseDto(offer.getCategory()));
        return dto;
    }
}
