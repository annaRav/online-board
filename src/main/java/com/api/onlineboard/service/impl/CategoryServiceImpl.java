package com.api.onlineboard.service.impl;

import com.api.onlineboard.dao.CategoryDao;
import com.api.onlineboard.model.Category;
import com.api.onlineboard.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    @Override
    public Category add(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.getReferenceById(id);
    }
}
