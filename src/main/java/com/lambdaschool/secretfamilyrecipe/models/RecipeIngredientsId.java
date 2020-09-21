package com.lambdaschool.secretfamilyrecipe.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeIngredientsId implements Serializable {

    private long recipe;

    private long ingredient;


    public RecipeIngredientsId() {
    }

    public long getRecipe() {
        return recipe;
    }

    public void setRecipe(long recipe) {
        this.recipe = recipe;
    }

    public long getIngredient() {
        return ingredient;
    }

    public void setIngredient(long ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientsId that = (RecipeIngredientsId) o;
        return recipe == that.recipe &&
                ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}

