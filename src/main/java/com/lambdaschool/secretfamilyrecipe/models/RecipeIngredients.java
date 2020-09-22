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
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Recipe recipes;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredientid")
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Ingredient ingredients;

    private String amount;

    public RecipeIngredients() {
    }

    public RecipeIngredients(Recipe recipes, Ingredient ingredients, String amount) {
        this.recipes = recipes;
        this.ingredients = ingredients;
        this.amount = amount;
    }

    public Recipe getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe recipe) {
        this.recipes = recipe;
    }

    public Ingredient getIngredient() {
        return ingredients;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredients = ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
