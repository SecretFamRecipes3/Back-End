package com.lambdaschool.secretfamilyrecipe.repository;

import com.lambdaschool.secretfamilyrecipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
