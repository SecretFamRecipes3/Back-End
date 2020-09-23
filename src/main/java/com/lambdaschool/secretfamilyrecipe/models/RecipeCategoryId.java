package com.lambdaschool.secretfamilyrecipe.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecipeCategoryId implements Serializable {
    private long recipes;
    private long categories;


    public RecipeCategoryId() {
    }

    public long getRecipes() {
        return recipes;
    }

    public void setRecipes(long recipes) {
        this.recipes = recipes;
    }

    public long getCategories() {
        return categories;
    }

    public void setCategories(long categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategoryId that = (RecipeCategoryId) o;
        return recipes == that.recipes &&
                categories == that.categories;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
