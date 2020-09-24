package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipe.models.Category;
import com.lambdaschool.secretfamilyrecipe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository catrepos;

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        catrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Category findCategoryById(long id) {
        return catrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (catrepos.findById(id).isPresent()) {
            catrepos.deleteById(id);
        }
    }

    @Transactional
    @Override
    public Category save(Category category) {
        if (category.getRecipes().size() > 0) {
            throw new ResourceNotFoundException("Recipes are not added through Categories");
        }
//        Category newCat = new Category();
//        newCat.setCategoryname(category.getCategoryname());
//        return catrepos.save(newCat);
        return catrepos.save(category);
    }

    @Transactional
    @Override
    public Category update(Category category, long id) {
        Category updatedCat = findCategoryById(id);
        if (category.getRecipes().size() > 0) {
            throw new ResourceNotFoundException("Recipes are not added through Categories");
        }
        if (category.getCategoryname() != null) {
            updatedCat.setCategoryname(category.getCategoryname());
        }
        return catrepos.save(updatedCat);
    }
}
