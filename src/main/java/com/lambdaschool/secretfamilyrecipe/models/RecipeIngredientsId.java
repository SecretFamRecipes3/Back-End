package com.lambdaschool.secretfamilyrecipe.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecipeIngredientsId implements Serializable {

    private long recipes;

    private long ingredients;


    public RecipeIngredientsId() {
    }

    public long getRecipes() {
        return recipes;
    }

    public void setRecipes(long recipe) {
        this.recipes = recipe;
    }

    public long getIngredients() {
        return ingredients;
    }

    public void setIngredients(long ingredient) {
        this.ingredients = ingredient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientsId that = (RecipeIngredientsId) o;
        return recipes == that.recipes &&
                ingredients == that.ingredients;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}

