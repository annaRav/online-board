package com.api.onlineboard.dao;

import com.api.onlineboard.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDao extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {
}
