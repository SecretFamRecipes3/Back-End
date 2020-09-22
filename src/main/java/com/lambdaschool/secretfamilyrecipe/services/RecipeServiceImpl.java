package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipe.models.Ingredient;
import com.lambdaschool.secretfamilyrecipe.models.Recipe;
import com.lambdaschool.secretfamilyrecipe.models.RecipeIngredients;
import com.lambdaschool.secretfamilyrecipe.repository.IngredientRepository;
import com.lambdaschool.secretfamilyrecipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    UserAuditing userAuditing;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    HelperFunctions helperFunctions;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> findAll() {
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Recipe findRecipeById(long recipeid) {
        return recipeRepository.findById(recipeid)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (recipeRepository.findById(id).isPresent()) {
            recipeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Recipe with id " + " Not Found");
        }

    }

    @Transactional
    @Override
    public Recipe update(Recipe recipe, long recipeid) {

        Recipe currentRecipe = findRecipeById(recipeid);
            if (recipe.getTitle() != null) {
                currentRecipe.setTitle((recipe.getTitle().toLowerCase()));
            }
            if (recipe.getSource() != null) {
                currentRecipe.setSource(recipe.getSource());
            }
            if (recipe.getIngredients().size() > 0) {
                currentRecipe.getIngredients().clear();
                for (RecipeIngredients ri : recipe.getIngredients()) {
                    Ingredient addIngredient = ingredientRepository.findById(ri.getIngredient().getIngredientid())
                            .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + ri.getIngredient().getIngredientid() + " not found!"));

                    currentRecipe.getIngredients()
                            .add(new RecipeIngredients(currentRecipe, addIngredient));
                }
            }
            if (recipe.getInstruction() != null) {
                currentRecipe.setInstruction(recipe.getInstruction());
            }
            if (recipe.getUser() != null) {
                currentRecipe.setUser(recipe.getUser());
            }
            return recipeRepository.save(currentRecipe);

    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        Recipe newRecipe = new Recipe();

        if (recipe.getRecipeid() != 0) {
            recipeRepository.findById(recipe.getRecipeid())
                    .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + recipe.getRecipeid() + " not found!"));
        }
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setInstruction(recipe.getInstruction());

        newRecipe.getIngredients()
                .clear();
        for (RecipeIngredients ri : recipe.getIngredients()) {
            Ingredient addIngredient = ingredientRepository.findById(ri.getIngredient().getIngredientid())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + ri.getIngredient().getIngredientid()));

            newRecipe.getIngredients().add(new RecipeIngredients(newRecipe, addIngredient));

        }
        newRecipe.setUser(recipe.getUser());
        return recipeRepository.save(newRecipe);
    }

}
