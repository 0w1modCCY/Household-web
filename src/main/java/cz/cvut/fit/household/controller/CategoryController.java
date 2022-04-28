package cz.cvut.fit.household.controller;

import cz.cvut.fit.household.service.interfaces.CategoryService;
import cz.cvut.fit.household.service.interfaces.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;

@Controller
public class CategoryController {
    private final HouseHoldService householdService;
    private final CategoryService categoryService;
    private final EntityManager entityManager;

    @Autowired
    public CategoryController(HouseHoldService householdService, CategoryService categoryService, EntityManager entityManager) {
        this.householdService = householdService;
        this.categoryService = categoryService;
        this.entityManager = entityManager;
    }

    @GetMapping("/household/{id}/category")
    public String renderHouseholdMembersPage(Model model, @PathVariable Long id) {
        return null;
    }
}
