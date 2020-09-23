package com.lambdaschool.secretfamilyrecipe.repository;

import com.lambdaschool.secretfamilyrecipe.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
