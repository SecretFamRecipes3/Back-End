package com.lambdaschool.secretfamilyrecipe;

import com.lambdaschool.secretfamilyrecipe.models.*;
import com.lambdaschool.secretfamilyrecipe.services.RoleService;
import com.lambdaschool.secretfamilyrecipe.services.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        Ingredient i1 = new Ingredient("Butter");

        Ingredient i2 = new Ingredient("All purpose flour");
        Ingredient i3 = new Ingredient("Egg");
        Ingredient i4 = new Ingredient("Mozzarella cheese");

        User u1 = new User("admin", "admin@email.com", "password");
        u1.getRoles()
                .add(new UserRoles(u1, r1));

        userService.save(u1);

        User u2 = new User("chef boyardee" ,"chef@email.com", "password");
        u2.getRoles()
                .add(new UserRoles(u2, r2));
        userService.save(u2);
    }
}
