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

    @Override
    public Ingredient update(Ingredient ingredient, long id) {
        Ingredient updatedIngr = findIngredientById(id);
        if (ingredient.getName() != null) {
            updatedIngr.setName(ingredient.getName());
        }
        return ingredientRepo.save(updatedIngr);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        Ingredient newIngr = new Ingredient();

        if (ingredient.getIngredientid() != 0) {
            ingredientRepo.findById(ingredient.getIngredientid())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + ingredient.getIngredientid() + " not found!"));

        }
        newIngr.setName(ingredient.getName());
        return ingredientRepo.save(newIngr);
    }

    @Transactional
    @Override
    public void deleteAll() {
        ingredientRepo.deleteAll();

    }
}
