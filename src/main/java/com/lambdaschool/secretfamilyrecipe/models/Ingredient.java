package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    private String name;

    @OneToMany(mappedBy = "ingredients", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Set<RecipeIngredients> recipes = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }
}

