package com.api.onlineboard.dao.specification;

import com.api.onlineboard.model.Offer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OfferSpecificationManager implements SpecificationManager<Offer> {
    private final Map<String, SpecificationProvider<Offer>> providerMap;

    public OfferSpecificationManager(List<SpecificationProvider<Offer>> specificationProviders) {
        this.providerMap = specificationProviders
            .stream()
            .collect(Collectors.toMap(SpecificationProvider::getFilterKey, Function.identity()));
    }

    @Override
    public Specification<Offer> get(String filterKey, String[] params) {
        if (!providerMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported for data filtering");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
