package com.lambdaschool.secretfamilyrecipe.repository;

import com.lambdaschool.secretfamilyrecipe.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
