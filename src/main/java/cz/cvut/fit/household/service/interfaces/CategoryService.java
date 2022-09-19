package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Category createOrUpdate(Category category);
    void deleteCategoryById(Long id);
    Optional<Category> findById(Long id);
}
