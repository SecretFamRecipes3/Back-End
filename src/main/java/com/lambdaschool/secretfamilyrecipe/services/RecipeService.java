package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();

    Recipe findRecipeById(long recipeid);

    void delete(long id);

    public void deleteAll();

    Recipe update(Recipe updateRecipe, long recipeid);

    Recipe save(Recipe recipe);

}
