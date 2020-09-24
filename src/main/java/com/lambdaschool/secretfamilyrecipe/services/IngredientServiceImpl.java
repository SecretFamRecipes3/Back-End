package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipe.models.Ingredient;
import com.lambdaschool.secretfamilyrecipe.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository ingredientRepo;

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> list = new ArrayList<>();
        ingredientRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Ingredient findIngredientById(long id) {
        return ingredientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (ingredientRepo.findById(id).isPresent()) {
            ingredientRepo.deleteById(id);
        }
    }

    @Transactional
    @Override
    public Ingredient update(Ingredient ingredient, long id) {
        Ingredient updatedIngr = findIngredientById(id);
        if (ingredient.getRecipes().size() > 0) {
            throw new ResourceNotFoundException("Recipes are not updated through ingredients");
        }
        if (ingredient.getName() != null) {
            updatedIngr.setName(ingredient.getName());
        }
        if (ingredient.getAmount() != null) {
            updatedIngr.setAmount(ingredient.getAmount());
        }
        return ingredientRepo.save(updatedIngr);
    }

    @Transactional
    @Override
    public Ingredient save(Ingredient ingredient) {
        if (ingredient.getRecipes().size() > 0) {
            throw new ResourceNotFoundException("Recipes are not added through Ingredients");
        }
//        Ingredient newIngr = new Ingredient();
//        newIngr.setName(ingredient.getName());
//        newIngr.setAmount(ingredient.getAmount());

//        return ingredientRepo.save(newIngr);
        return ingredientRepo.save(ingredient);
    }

    @Transactional
    @Override
    public void deleteAll() {
        ingredientRepo.deleteAll();

    }
}
