package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.SecretfamilyrecipeApplication;
import com.lambdaschool.secretfamilyrecipe.config.DataSourceConfig;
import com.lambdaschool.secretfamilyrecipe.controllers.RolesController;
import com.lambdaschool.secretfamilyrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipe.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretfamilyrecipeApplication.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipeServiceImplTest {

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        List<Recipe> myRecipe = recipeService.findAll();
        for (Recipe r : myRecipe) {
            System.out.println(r.getRecipeid() + " " + r.getTitle());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void b_findAll() {
        assertEquals(5, recipeService.findAll().size());
    }

    @Test
    public void a_findRecipeById() {
        assertEquals("Key Lime Pie Bars", recipeService.findRecipeById(65).getTitle());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void aa_findRecipeByIdNotFound() {
        assertEquals("Key Lime Pie Bars", recipeService.findRecipeById(1000).getTitle());
    }

    @Test
    public void c_delete() {
        recipeService.delete(68);
        assertEquals(4, recipeService.findAll().size());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void cc_deleteFailed() {
        recipeService.delete(365);
        assertEquals(4, recipeService.findAll().size());
    }

    @Test
    public void update() {
    }

    @Test
    public void e_save() {

//        Role r1 = new Role("user");
//
//        r1 = roleService.save(r1);

        Ingredient i1 = new Ingredient("Unsalted Butter", "1 tablespoon");
        Ingredient i2 = new Ingredient("All purpose flour", "2 cups");
        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);

        Category cat2 = new Category("Breakfast");
        cat2 = categoryService.save(cat2);

        User u1 = new User("admin", "admin@email.com", "password");
//        u1.getRoles()
//                .add(new UserRoles(u1, roleService.findAll().get(1)));
        u1 = userService.save(u1);

        Recipe re1 = new Recipe("Fried Mozzarella Puffs", "Mom", "30 minutes",
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
        re1 = recipeService.save(re1);

        assertNotNull(re1);
        assertEquals("Fried Mozzarella Puffs", re1.getTitle());

    }

    @Test
    public void ee_saveput() {
        Role r2 = new Role("user");
        r2 = roleService.save(r2);

        Ingredient i1 = new Ingredient("Eggs", "2 large");
        i1 = ingredientService.save(i1);

        Category cat2 = new Category("Breakfast");
        cat2 = categoryService.save(cat2);

        User u2 = new User("User", "user@email.com", "password");
        u2.getRoles()
                .add(new UserRoles(u2, r2));
        u2 = userService.save(u2);

        Recipe re1 = new Recipe("Scrambled Eggs", "Mom", "5 minutes",
                "This recipe makes 2 servings, 2 eggs each (4 total), in a medium-sized nonstick skillet. " +
                        "In my experience 4 eggs tends to be the easiest to deal with in a standard medium-sized " +
                        "skillet and scrambles the most evenly. For a small skillet, try 2 eggs at a time. If you have" +
                        " a very large skillet (over 11\"), try 6 at a time. Always use a skillet with a nonstick coating" +
                        " or a well-seasoned cast iron pan for best results.",
                u2);
        re1.getIngredients().add(new RecipeIngredients(re1, i1));
        re1 = recipeService.save(re1);

        assertNotNull(re1);
        assertEquals("Scrambled Eggs", re1.getTitle());
    }
}