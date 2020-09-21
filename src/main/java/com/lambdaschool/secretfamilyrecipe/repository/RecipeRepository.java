package com.lambdaschool.secretfamilyrecipe.repository;

import com.lambdaschool.secretfamilyrecipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
