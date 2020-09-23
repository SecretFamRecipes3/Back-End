package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "recipecategories")
@IdClass(RecipeCategoryId.class)
public class RecipeCategory extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties("categories")
    private Recipe recipes;

    @Id
    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties("recipes")
    private Category categories;

    public RecipeCategory() {
    }

    public RecipeCategory(Recipe recipes, Category categories) {
        this.recipes = recipes;
        this.categories = categories;
    }

    public Recipe getRecipe() {
        return recipes;
    }

    public void setRecipe(Recipe recipe) {
        this.recipes = recipe;
    }

    public Category getCategory() {
        return categories;
    }

    public void setCategory(Category category) {
        this.categories = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } if (!(o instanceof RecipeCategory)) {
            return false;
        }
        RecipeCategory that = (RecipeCategory) o;
        return ((recipes == null) ? 0 : recipes.getRecipeid()) == ((that.recipes == null) ? 0 : that.recipes.getRecipeid()) &&
                ((categories == null) ? 0 : categories.getCategoryid()) == ((that.categories == null) ? 0 : that.categories.getCategoryid());
    }

    @Override
    public int hashCode() {
        return 37;
    }

}
