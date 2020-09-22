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

        Ingredient i1 = new Ingredient("Unsalted Butter", "1 tablespoon");
        Ingredient i2 = new Ingredient("All purpose flour", "2 cups");
        Ingredient i3 = new Ingredient("Egg", "1 large egg");
        Ingredient i4 = new Ingredient("Mozzarella cheese", "4 ounces");
        Ingredient i5 = new Ingredient("Oil", "4 cups");
        Ingredient i6 = new Ingredient("Water", "1/3 cups");
        Ingredient i7 = new Ingredient("Marinara sauce", "1 cups");

        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);
        i3 = ingredientService.save(i3);
        i4 = ingredientService.save(i4);
        i5 = ingredientService.save(i5);
        i6 = ingredientService.save(i6);
        i7 = ingredientService.save(i7);

        User u1 = new User("admin", "admin@email.com", "password");
        u1.getRoles()
                .add(new UserRoles(u1, r1));

        u1 = userService.save(u1);

        Recipe re1 = new Recipe("Fried Mozzarella Puffs",
                "Mom",
                "Combine water, butter, and salt in a saucepan over medium-high heat. Bring to a simmer; pour in " +
                        "flour all at once and reduce heat to medium. Stir with a wooden spoon or spatula until a dough starts " +
                        "coming together. Cook, scraping up and stirring the dough, for 2 to 3 minutes." + "Remove from heat; " +
                        "transfer dough to a mixing bowl. Let cool until no longer hot but still very warm, 5 to 10 minutes. " +
                        "Add egg and season with cayenne and freshly ground black pepper. Whisk vigorously until mixture combines " +
                        "into a very soft, sticky dough. Switch to a spatula and scrape dough into a ball." + "Seal dough and spatula " +
                        "with plastic wrap and refrigerate in the bowl until cool, about 1 hour." + "In the meantime, season marinara " +
                        "sauce with oregano, red pepper flakes, balsamic vinegar in a small pot over medium heat. Add anchovy fillet. " +
                        "Stir together and bring to a simmer. Let simmer for 10 minutes; turn off heat and let sit until ready to use." +
                        "Grate mozzarella cheese over the dough and stir to combine." + "Heat oil to 350 degrees F (175 degrees C) in a " +
                        "deep fryer or heavy-duty pan over medium heat. Preheat oven to 200 degrees F (93 degrees C) or any temperature" +
                        " for keeping warm." + "Scoop out about 2 tablespoons of dough per puff and form into a football shape using two " +
                        "spoons. Fry puffs, 5 or 6 at a time, in the hot oil until browned, 2 to 3 minutes. Drain on paper towels. " +
                        "Keep puffs warm in low oven while frying the rest. Serve with hot marinara sauce.",
                u1);
        re1.getIngredients().add(new RecipeIngredients(re1, i1));
        re1.getIngredients().add(new RecipeIngredients(re1, i2));
        re1.getIngredients().add(new RecipeIngredients(re1, i3));
        re1.getIngredients().add(new RecipeIngredients(re1,i4));
        re1.getIngredients().add(new RecipeIngredients(re1, i5));
        re1.getIngredients().add(new RecipeIngredients(re1, i6));
        re1.getIngredients().add(new RecipeIngredients(re1, i7));
        re1 = recipeService.save(re1);


        User u2 = new User("chef boyardee" ,"chef@email.com", "password");
        u2.getRoles()
                .add(new UserRoles(u2, r2));
        userService.save(u2);
    }
}
