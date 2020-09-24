package com.lambdaschool.secretfamilyrecipe.controllers;

import com.lambdaschool.secretfamilyrecipe.models.Category;
import com.lambdaschool.secretfamilyrecipe.models.Ingredient;
import com.lambdaschool.secretfamilyrecipe.models.Recipe;
import com.lambdaschool.secretfamilyrecipe.services.CategoryService;
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

    @Autowired
    CategoryService categoryService;

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
    @PutMapping(value = "/recipe/{id}", consumes = "application/json")
    public ResponseEntity<?> updateFullRecipe(@Valid @RequestBody Recipe recipe, @PathVariable long id) {
        recipe.setRecipeid(id);
        recipeService.save(recipe);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
        return new ResponseEntity<>(newRecipe,responseHeaders, HttpStatus.CREATED);
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


    // categories endpoints
    //http://localhost:2019/recipes/categories
    @GetMapping(value = "/categories", produces = "application/json")
    public ResponseEntity<?> listAllCategories() {
        List<Category> allCategories = categoryService.findAll();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
    //http://localhost:2019/recipes/category/{id}
    @GetMapping(value = "/category/{id}", produces = "application/json")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category cat = categoryService.findCategoryById(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    //http://localhost:2019/recipes/category/{id}
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //http://localhost:2019/recipes/category/{id}
    @PutMapping(value = "/category/{id}", consumes = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody Category cat, @PathVariable long id) {
        cat.setCategoryid(id);
        categoryService.update(cat, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //http://localhost:2019/recipes/category
    @PostMapping(value = "/category", consumes = "application/json")
    public ResponseEntity<?> addNewCat(@Valid @RequestBody Category cat) {
        categoryService.save(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
