package com.lambdaschool.secretfamilyrecipe;

import com.lambdaschool.secretfamilyrecipe.models.*;
import com.lambdaschool.secretfamilyrecipe.services.IngredientService;
import com.lambdaschool.secretfamilyrecipe.services.RecipeService;
import com.lambdaschool.secretfamilyrecipe.services.RoleService;
import com.lambdaschool.secretfamilyrecipe.services.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeService recipeService;

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

        Ingredient i1 = new Ingredient("Unsalted Butter");
        Ingredient i2 = new Ingredient("All purpose flour");
        Ingredient i3 = new Ingredient("Egg");
        Ingredient i4 = new Ingredient("Mozzarella cheese");
        Ingredient i5 = new Ingredient("Oil");

        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);
        i3 = ingredientService.save(i3);
        i4 = ingredientService.save(i4);
        i5 = ingredientService.save(i5);

        User u1 = new User("admin", "admin@email.com", "password");
        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1 = userService.save(u1);

        Recipe re1 = new Recipe("Fried Mozzarella Puffs",
                "Mom",
                "Combine water, butter, and salt in a saucepan over medium-high heat. Bring to a simmer; pour in " +
                        "flour all at once and reduce heat to medium. Stir with a wooden spoon or spatula until a dough starts " +
                        "coming together. Cook, scraping up and stirring the dough, for 2 to 3 minutes.",
                u1);

        Set<RecipeIngredients> recipeIngr1 = new HashSet<>();
        recipeIngr1.add(new RecipeIngredients(re1, i1, "2 Tablespoon"));
        recipeIngr1.add(new RecipeIngredients(re1, i2, "1 cup"));
        recipeIngr1.add(new RecipeIngredients(re1, i3,"1 large egg"));
        recipeIngr1.add(new RecipeIngredients(re1, i4,"4 ounces"));
        recipeIngr1.add(new RecipeIngredients(re1, i5,"4 cups"));

        re1.setIngredients(recipeIngr1);
        re1 = recipeService.save(re1);




        User u2 = new User("chef boyardee" ,"chef@email.com", "password");
        u2.getRoles()
                .add(new UserRoles(u2, r2));
        userService.save(u2);
    }
}
