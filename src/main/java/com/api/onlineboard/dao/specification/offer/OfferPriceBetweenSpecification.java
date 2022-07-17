package com.api.onlineboard.dao.specification.offer;

import com.api.onlineboard.dao.specification.SpecificationProvider;
import com.api.onlineboard.model.Offer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OfferPriceBetweenSpecification implements SpecificationProvider<Offer> {
    private static final String FILTER_KEY = "priceBetween";
    private static final String FILTER_NAME = "price";
    private static final int PRICE_FROM_INDEX = 0;
    private static final int PRICE_TO_INDEX = 1;

    @Override
    public Specification<Offer> getSpecification(String[] prices) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(
            root.get(FILTER_NAME),
            new BigDecimal(prices[PRICE_FROM_INDEX]),
            new BigDecimal(prices[PRICE_TO_INDEX])));
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
