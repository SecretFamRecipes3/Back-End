package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@JsonIgnoreProperties(value = "recipes")
public class Ingredient extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    private String name;

    private String amount;

    @OneToMany(mappedBy = "ingredients", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Set<RecipeIngredients> recipes = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Set<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }
}

