package com.lambdaschool.secretfamilyrecipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @Column(nullable = false)
    private String title;

    private String source;

    private String preptime;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Set<RecipeIngredients> ingredients = new HashSet<>();


    @Column(length = 10000)
    private String instruction;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("recipes")
    private User user;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Set<RecipeCategory> categories = new HashSet<>();

//    @ManyToMany()
//    @JoinTable(name = "recipecategories",
//            joinColumns = @JoinColumn(name = "recipeid"),
//            inverseJoinColumns = @JoinColumn(name = "categoryid"))
//    public Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String title, String source, String preptime, String instruction, User user) {
        this.title = title;
        this.source = source;
        this.preptime = preptime;
        this.instruction = instruction;
        this.user = user;
    }


    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Set<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<RecipeCategory> categories) {
        this.categories = categories;
    }


//    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }
}