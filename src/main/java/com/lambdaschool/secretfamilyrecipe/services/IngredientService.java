package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();

    Ingredient findIngredientById(long id);

    void delete(long id);

    Ingredient update(Ingredient ingredient, long id);

    Ingredient save(Ingredient ingredient);

    void deleteAll();
}
