package com.api.onlineboard.dao.specification.offer;

import com.api.onlineboard.dao.specification.SpecificationProvider;
import com.api.onlineboard.model.Category;
import com.api.onlineboard.model.Offer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Component
public class OfferCategoryInSpecification implements SpecificationProvider<Offer> {
    private static final String FILTER_KEY = "categoryIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Offer> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> {
            Join<Offer, Category> join = root.join("category", JoinType.INNER);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(join.get(FIELD_NAME));
            for (String param: params) {
                predicate.value(param);
            }

            return criteriaBuilder.and(predicate, predicate);
        });
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
