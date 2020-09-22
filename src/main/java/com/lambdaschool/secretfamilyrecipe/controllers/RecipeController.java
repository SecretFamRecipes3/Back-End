package com.lambdaschool.secretfamilyrecipe.controllers;

import com.lambdaschool.secretfamilyrecipe.models.Ingredient;
import com.lambdaschool.secretfamilyrecipe.models.Recipe;
import com.lambdaschool.secretfamilyrecipe.services.IngredientService;
import com.lambdaschool.secretfamilyrecipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;

    //http://localhost:2019/recipes/recipes
    @GetMapping(value = "/recipes", produces = "application/json")
    public ResponseEntity<?> listAllRecipes() {
        List<Recipe> allRecipes = recipeService.findAll();
        return new ResponseEntity<>(allRecipes, HttpStatus.OK);
    }

    //http://localhost:2019/recipes/recipe/{id}
    @GetMapping(value = "/recipe/{id}", produces = "application/json")
    public ResponseEntity<?> getRecipeById(
            @PathVariable Long id) {
        Recipe r = recipeService.findRecipeById(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    //http://localhost:2019/recipes/recipe/{id}
    @DeleteMapping(value = "/recipe/{id}")
    public ResponseEntity<?> deleteRecipeByID(@PathVariable long id) {
        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2019/recipes/recipe/{id}
    @PutMapping(value = "/recipe/{id}/", consumes = "application/json")
    public ResponseEntity<?> updateRecipe(@Valid @RequestBody Recipe updateRecipe, @PathVariable long id) {
        updateRecipe.setRecipeid(id);
        recipeService.save(updateRecipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2019/recipes/recipe
    @PostMapping(value = "/recipe", consumes = "application/json")
    public ResponseEntity<?> addNewRecipe(@Valid @RequestBody Recipe newRecipe) throws
            URISyntaxException {
        newRecipe.setRecipeid(0);
        newRecipe = recipeService.save(newRecipe);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeid}")
                .buildAndExpand(newRecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //endpoints for ingredients

    //http://localhost:2019/recipes/ingredients
    @GetMapping(value = "/ingredients", produces = "application/json")
    public ResponseEntity<?> listAllIngredients() {
        List<Ingredient> allIngredients = ingredientService.findAll();
        return new ResponseEntity<>(allIngredients, HttpStatus.OK);
    }

    //http://localhost:2019/recipes/ingredient/{id}
    @GetMapping(value = "/ingredient/{id}", produces = "application/json")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
        Ingredient i = ingredientService.findIngredientById(id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    //http://localhost:2019/recipes/ingredient/{id}
    @DeleteMapping(value = "/ingredient/{id}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable long id) {
        ingredientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2019/recipes/ingredient
    @PutMapping(value = "/ingredient/{id}", consumes = "application/json")
    public ResponseEntity<?> updateIng(@Valid @RequestBody Ingredient ing, @PathVariable long id) {
        ing.setIngredientid(id);
        ingredientService.save(ing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2019/recipes/ingredient
    @PostMapping(value = "/ingredient", consumes = "application/json")
    public ResponseEntity<?> addNewIngr(@Valid @RequestBody Ingredient ingr) {
        ingredientService.save(ingr);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
