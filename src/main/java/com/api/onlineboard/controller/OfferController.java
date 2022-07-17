package com.api.onlineboard.controller;

import com.api.onlineboard.dto.OfferRequestDto;
import com.api.onlineboard.dto.OfferResponseDto;
import com.api.onlineboard.model.Category;
import com.api.onlineboard.model.Offer;
import com.api.onlineboard.service.CategoryService;
import com.api.onlineboard.service.OfferService;
import com.api.onlineboard.service.mapper.OfferMapper;
import com.api.onlineboard.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/offers")
public class OfferController {
    private final OfferMapper offerMapper;
    private final OfferService offerService;
    private final RequestUtil requestUtil;
    private final CategoryService categoryService; //temp

    @GetMapping("/inject")
    public void inject() {
        for (int i = 0; i < 200; i++) {
            Category category = new Category();
            category.setName("name" + i);
            categoryService.add(category);

            Offer offer = new Offer();
            offer.setLabel("label " + i);
            offer.setPrice(new BigDecimal(i * 100));
            offer.setCategory(category);
            offerService.add(offer);
        }
    }

    @PostMapping
    public OfferResponseDto create(@RequestBody OfferRequestDto offerRequestDto) {
        Offer offer = offerMapper.toModel(offerRequestDto);
        offerService.add(offer);
        return offerMapper.toResponseDto(offer);
    }

    @PutMapping(value = "/{id}")
    public OfferResponseDto update(
        @RequestBody OfferRequestDto OfferRequestDto,
        @PathVariable Long id) {
        Offer Offer = offerMapper.toModel(OfferRequestDto);
        Offer.setId(id);
        offerService.update(Offer);
        return offerMapper.toResponseDto(Offer);
    }

    @GetMapping(value = "/{id}")
    public OfferResponseDto getById(@PathVariable Long id) {
        return offerMapper.toResponseDto(offerService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        Offer Offer = offerService.getById(id);
        offerService.delete(Offer);
    }

    @GetMapping
    public List<OfferResponseDto> getAll(@RequestParam Map<String, String> params) {
        int page = Integer.parseInt(params.getOrDefault("page", "0"));
        int size = Integer.parseInt(params.getOrDefault("size", "20"));
        String sortBy = params.getOrDefault("sortBy", "id");

        PageRequest pageRequest = requestUtil.getSortBySortByRequestParam(page, size, sortBy);
        Page<Offer> offers = offerService.getAll(params, pageRequest);
        return offers.stream()
            .map(offerMapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
