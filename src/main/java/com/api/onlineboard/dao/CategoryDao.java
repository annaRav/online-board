package com.api.onlineboard.dao;

import com.api.onlineboard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
