package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipeingredients")
@IdClass(RecipeIngredientsId.class)
public class RecipeIngredients extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties("ingredients")
    private Recipe recipes;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredientid")
    @JsonIgnoreProperties("recipes")
    private Ingredient ingredients;


    public RecipeIngredients() {
    }

    public RecipeIngredients(Recipe recipes, Ingredient ingredients) {
        this.recipes = recipes;
        this.ingredients = ingredients;
    }

    public Recipe getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe recipes) {
        this.recipes = recipes;
    }

    public Ingredient getIngredient() {
        return ingredients;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredients = ingredient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeIngredients)) return false;
        RecipeIngredients that = (RecipeIngredients) o;
        return ((recipes == null) ? 0 : recipes.getRecipeid()) == ((that.recipes == null) ? 0 : that.recipes.getRecipeid()) &&
                ((ingredients == null) ? 0 : ingredients.getIngredientid()) == ((that.ingredients == null) ? 0 : that.ingredients.getIngredientid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
