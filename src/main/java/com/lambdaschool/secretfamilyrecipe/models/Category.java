package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@JsonIgnoreProperties(value = "recipes")
public class Category extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    private String categoryname;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Set<RecipeCategory> recipes = new HashSet<>();

//    @ManyToMany(mappedBy = "categories")
//    @JsonIgnoreProperties(value = "categories")
//    private List<Recipe> recipes = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryname) {
        this.categoryname = categoryname;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Set<RecipeCategory> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeCategory> recipes) {
        this.recipes = recipes;
    }

//    public List<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    public void setRecipes(List<Recipe> recipes) {
//        this.recipes = recipes;
//    }
}
