package com.api.onlineboard.service;

import com.api.onlineboard.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OfferService {
    Offer add(Offer product);

    Offer update(Offer product);

    void delete(Offer product);

    Offer getById(Long id);

    Page<Offer> getAll(Map<String, String> params, Pageable pageable);
}
