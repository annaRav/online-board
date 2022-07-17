package com.api.onlineboard.service;

import com.api.onlineboard.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category update(Category category);

    void delete(Category category);

    Category getById(Long id);
}
