package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "recipeingredients")
@IdClass(RecipeIngredientsId.class)
public class RecipeIngredients extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Recipe recipe;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredientid")
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Ingredient ingredient;


    public RecipeIngredients() {
    }

    public RecipeIngredients(Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeIngredients)) return false;
        RecipeIngredients that = (RecipeIngredients) o;
        return ((recipe == null) ? 0 : recipe.getRecipeid()) == ((that.recipe == null) ? 0 : that.recipe.getRecipeid()) &&
                ((ingredient == null) ? 0 : ingredient.getIngredientid()) == ((that.ingredient == null) ? 0 : that.ingredient.getIngredientid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
