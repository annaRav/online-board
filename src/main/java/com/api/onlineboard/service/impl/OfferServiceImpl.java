package com.api.onlineboard.service.impl;

import com.api.onlineboard.dao.OfferDao;
import com.api.onlineboard.dao.specification.SpecificationManager;
import com.api.onlineboard.model.Offer;
import com.api.onlineboard.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private static final List<String> pageableParam = List.of("page", "size", "sortBy");
    private final OfferDao offerDao;
    private final SpecificationManager<Offer> offerSpecificationProvider;

    @Override
    public Offer add(Offer offer) {
        return offerDao.save(offer);
    }

    @Override
    public Offer update(Offer offer) {
        return offerDao.save(offer);
    }

    @Override
    public void delete(Offer offer) {
        offerDao.delete(offer);
    }

    @Override
    public Offer getById(Long id) {
        return offerDao.getReferenceById(id); //todo
    }

    @Override
    public Page<Offer> getAll(Map<String, String> params, Pageable pageable) {
        Specification<Offer> specification = null;
        Set<Map.Entry<String, String>> filters = params.entrySet()
            .stream()
            .filter(pair -> !pageableParam.contains(pair.getKey()))
            .collect(Collectors.toSet());
        for (Map.Entry<String, String> pair : filters) {
            Specification<Offer> sp = offerSpecificationProvider.get(
                pair.getKey(),
                pair.getValue().split(","));
            specification = specification == null
                ? Specification.where(sp) : specification.and(sp);
        }

        return offerDao.findAll(specification, pageable);
    }
}
