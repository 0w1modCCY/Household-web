package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.Category;
import cz.cvut.fit.household.datamodel.entity.Copy;
import cz.cvut.fit.household.datamodel.entity.Item;
import cz.cvut.fit.household.exception.NonExistentEntityException;
import cz.cvut.fit.household.repository.CategoryRepository;
import cz.cvut.fit.household.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void addItem(Long categoryId, Item item) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new NonExistentEntityException("category doesn't exist")).addItem(item);
    }
    public void removeItem(Long categoryId, Item item) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new NonExistentEntityException("category doesn't exist")).deleteItem(item);
    }
}
