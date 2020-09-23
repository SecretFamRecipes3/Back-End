package com.lambdaschool.secretfamilyrecipe;

import com.lambdaschool.secretfamilyrecipe.models.*;
import com.lambdaschool.secretfamilyrecipe.services.*;
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

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    CategoryService categoryService;

    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
// Roles //
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

// Ingredient //

        Ingredient i1 = new Ingredient("Unsalted Butter", "1 tablespoon");
        Ingredient i2 = new Ingredient("All purpose flour", "2 cups");
        Ingredient i3 = new Ingredient("Egg", "1 large egg");
        Ingredient i4 = new Ingredient("Mozzarella cheese", "4 ounces");
        Ingredient i5 = new Ingredient("Oil", "4 cups");
        Ingredient i6 = new Ingredient("Water", "1/3 cups");
        Ingredient i7 = new Ingredient("Marinara sauce", "1 cups");

        Ingredient i8 = new Ingredient("Graham Cracker", "12 large rectangles"); //
        Ingredient i9 = new Ingredient("Ground Pecans", "2 tablespoons");
        Ingredient i10 = new Ingredient("Granulated Sugar", "1/3 cup");
        Ingredient i11 = new Ingredient("Cinnamon Powder", "1/4 teaspoon");
        Ingredient i12 = new Ingredient("Unsalted Butter", "1 stick (1/2 cup)"); //
        Ingredient i13 = new Ingredient("Egg Yolks", "3 room temperature");
        Ingredient i14 = new Ingredient("Sweetened Condensed Milk", "1 can (14 oz)");
        Ingredient i15 = new Ingredient("Lime Zest", "4 teaspoons (about 3 limes)");
        Ingredient i16 = new Ingredient("Key Lime Juice", "1/2 cup (regular limes are fine too)");

        Ingredient i17 = new Ingredient("Sugar", "3 tablespoons");
        Ingredient i18 = new Ingredient("Cream Cheese", "24 ounces");
        Ingredient i19 = new Ingredient("Granulated Sugar", "1 cup");
        Ingredient i20 = new Ingredient("All Purpose Flour", "3 tablespoons");
        Ingredient i21 = new Ingredient("Sour Cream", "3/4 cup");
        Ingredient i22 = new Ingredient("Sweetened Condensed Milk", "1 can (14 oz)");
        Ingredient i23 = new Ingredient("Vanilla Extract", "1 1/2 tablespoons");
        Ingredient i24 = new Ingredient("Eggs", "3 large");
        Ingredient i25 = new Ingredient("Sugar", "1 1/2 cups");
        Ingredient i26 = new Ingredient("Cornstarch", "2 tablespoons");
        Ingredient i27 = new Ingredient("Strawberries Pureed", "1 1/2 cups");
        Ingredient i28 = new Ingredient("Vanilla Extract", "1/2 teaspoon");
        Ingredient i29 = new Ingredient("Strawberries Chopped", "2 cups");
        Ingredient i30 = new Ingredient("Heavy Whipping Cream", "1/2 cup (cold)");
        Ingredient i31 = new Ingredient("Powdered Sugar", "1/4 cup");

        Ingredient i32 = new Ingredient("Eggs", "2 Large");
        Ingredient i33 = new Ingredient("Unsweetened Cocoa Powder", "1/3 cup");
        Ingredient i34 = new Ingredient("All Purpose Flour", "1/2 cup");
        Ingredient i35 = new Ingredient("Baking Powder", "1/4 teaspoon");
        Ingredient i36 = new Ingredient("Butter", "3 Tablespoons");
        Ingredient i37 = new Ingredient("Unsweetened Cocoa Powder", "3 tablespoons");
        Ingredient i38 = new Ingredient("Honey", "1 tablespoon");
        Ingredient i39 = new Ingredient("Confectioners' Sugar", "1 cup");

        Ingredient i40 = new Ingredient("Olive Oil", "2 tablespoons");
        Ingredient i41 = new Ingredient("Boneless, Skinless Chicken Thighs Sliced", "4");
        Ingredient i42 = new Ingredient("Salt and Pepper", "to taste");
        Ingredient i43 = new Ingredient("Green Beans", "1lb");
        Ingredient i44 = new Ingredient("Cherry Tomato (Halved)", "2 cups");
        Ingredient i45 = new Ingredient("Basil Pesto", "1/2 cup");

        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);
        i3 = ingredientService.save(i3);
        i4 = ingredientService.save(i4);
        i5 = ingredientService.save(i5);
        i6 = ingredientService.save(i6);
        i7 = ingredientService.save(i7);

        i8 = ingredientService.save(i8);
        i9 = ingredientService.save(i9);
        i10 = ingredientService.save(i10);
        i11 = ingredientService.save(i11);
        i12 = ingredientService.save(i12);
        i13 = ingredientService.save(i13);
        i14 = ingredientService.save(i14);
        i15 = ingredientService.save(i15);
        i16 = ingredientService.save(i16);

        i17 = ingredientService.save(i17);
        i18 = ingredientService.save(i18);
        i19 = ingredientService.save(i19);
        i20 = ingredientService.save(i20);
        i21 = ingredientService.save(i21);
        i22 = ingredientService.save(i22);
        i23 = ingredientService.save(i23);
        i24 = ingredientService.save(i24);
        i25 = ingredientService.save(i25);
        i26 = ingredientService.save(i26);
        i27 = ingredientService.save(i27);
        i28 = ingredientService.save(i28);
        i29 = ingredientService.save(i29);
        i30 = ingredientService.save(i30);
        i31 = ingredientService.save(i31);

        i32 = ingredientService.save(i32);
        i33 = ingredientService.save(i33);
        i34 = ingredientService.save(i34);
        i35 = ingredientService.save(i35);
        i36 = ingredientService.save(i36);
        i37 = ingredientService.save(i37);
        i38 = ingredientService.save(i38);
        i39 = ingredientService.save(i39);

        i40 = ingredientService.save(i40);
        i41 = ingredientService.save(i41);
        i42 = ingredientService.save(i42);
        i43 = ingredientService.save(i43);
        i44 = ingredientService.save(i44);
        i45 = ingredientService.save(i45);
// Category //

        Category cat1 = new Category("Italian");
        Category cat2 = new Category("Breakfast");
        Category cat3 = new Category("Appetizer");
        Category cat4 = new Category("Dessert");
        Category cat5 = new Category("Bars");
        Category cat6 = new Category("Cheesecake");
        Category cat7 = new Category("Chocolate");
        Category cat8 = new Category("Brownie");
        Category cat9 = new Category("Easy");
        Category cat10 = new Category("Dinner");
;
        cat1 = categoryService.save(cat1);
        cat2 = categoryService.save(cat2);
        cat3 = categoryService.save(cat3);
        cat4 = categoryService.save(cat4);
        cat5 = categoryService.save(cat5);
        cat6 = categoryService.save(cat6);
        cat7 = categoryService.save(cat7);
        cat8 = categoryService.save(cat8);
        cat9 = categoryService.save(cat9);
        cat10 = categoryService.save(cat10);

// Creating Users //

        User u1 = new User("admin", "admin@email.com", "password");
        u1.getRoles()
                .add(new UserRoles(u1, r1));

        User u2 = new User("chef boyardee" ,"chef@email.com", "password");
        u2.getRoles()
                .add(new UserRoles(u2, r2));

        User u3 = new User("Liz Drumm", "drum@email.com", "password");
        u3.getRoles()
                .add(new UserRoles(u3, r2));

        User u4 = new User("Jennifer Kramer", "JKramer@email.com", "password");
        u4.getRoles()
                .add(new UserRoles(u4, r2));

        User u5 = new User("John", "john@email.com", "password");
        u5.getRoles()
                .add(new UserRoles(u5, r2));

        User u6 = new User("Kae Benton", "benton@email.com", "password");
        u6.getRoles()
                .add(new UserRoles(u6, r2));

        u1 = userService.save(u1);
        u2 = userService.save(u2);
        u3 = userService.save(u3);
        u4 = userService.save(u4);
        u5 = userService.save(u5);
        u6 = userService.save(u6);


// Creating Recipes //

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
        re1.getIngredients().add(new RecipeIngredients(re1, i2));
        re1.getIngredients().add(new RecipeIngredients(re1, i3));
        re1.getIngredients().add(new RecipeIngredients(re1, i4));
        re1.getIngredients().add(new RecipeIngredients(re1, i5));
        re1.getIngredients().add(new RecipeIngredients(re1, i6));
        re1.getIngredients().add(new RecipeIngredients(re1, i7));

        re1.getCategories().add(new RecipeCategory(re1, cat1));
        re1.getCategories().add(new RecipeCategory(re1, cat3));

        Recipe re2 = new Recipe("Key Lime Pie Bars", "Grandmother", "15 minutes", "1. Position a rack in the center of the oven and preheat the oven to 350ºF. Spray an 8x8 square baking pan with cooking spray and line with parchment paper, set aside.\n" +
                "2. In a small bowl, stir together the graham cracker crumbs, ground pecans, sugar, and cinnamon. Add the melted butter and stir to combine. Make sure all the crumbs are moistened. Press the crumbs into the bottom of the baking pan and bake for 10 minutes. Remove from oven, leave oven on.\n" +
                "3. In the bowl of an electric mixer fitted with the whisk attachment, whisk the yolks for 2 minutes. Add the condensed milk and continue to whisk for another 2 minutes. Stop the mixer, scrape down the sides, add the lime zest. With the mixer on low, drizzle in the lime juice, let mixer run for 2 minutes once added. Pie mixture will be slightly thicker when done. Pour the filling into crust and bake for 15 minutes.\n" +
                "4. Cool completely on rack. Filling will set as it cools. Refrigerate for at least 2 hours. Slice and serve chilled.", u3);
        re2.getIngredients().add(new RecipeIngredients(re2, i8));
        re2.getIngredients().add(new RecipeIngredients(re2, i9));
        re2.getIngredients().add(new RecipeIngredients(re2, i10));
        re2.getIngredients().add(new RecipeIngredients(re2, i11));
        re2.getIngredients().add(new RecipeIngredients(re2, i12));
        re2.getIngredients().add(new RecipeIngredients(re2, i13));
        re2.getIngredients().add(new RecipeIngredients(re2, i14));
        re2.getIngredients().add(new RecipeIngredients(re2, i15));
        re2.getIngredients().add(new RecipeIngredients(re2, i16));

        re2.getCategories().add(new RecipeCategory(re2, cat4));
        re2.getCategories().add(new RecipeCategory(re2, cat5));

        Recipe re3 = new Recipe("Strawberry Cheesecake", "Mom", "25 minutes", "1. Preheat oven to 325°F (163°C). Grease a 9×13 pan.\n" +
                "2. Combine the crust ingredients in a small bowl. Press the mixture into the bottom of the prepared pan.\n" +
                "3. Bake the crust for 10 minutes, then set aside to cool.\n" +
                "4. Reduce the oven temperature to 300°F (148°C).\n" +
                "5. In a large bowl, blend the cream cheese, sugar and flour on low speed until well completely combined and smooth. Be sure to use low speed to reduce the amount of air added to the batter, which can cause cracks. Scrape down the sides of the bowl.\n" +
                "6. Add the sour cream and vanilla extract mix on low speed until well combined.\n" +
                "7. Add eggs one at a time, mixing slowly to combine. Scrape down the sides of the bowl as needed to make sure everything is well combined.\n" +
                "8. Pour the batter into the pan with the crust and spread evenly.\n" +
                "9. Bake the cheesecake for 30 minutes.\n" +
                "10. Turn off the oven and leave the cheesecake in the oven with the door closed for 20 minutes.\n" +
                "11. Crack the door of the oven, with the cheesecake inside, for 15 minutes. This slow cooling process helps prevent the cheesecake from cracking.\n" +
                "12. Put the cheesecake in the fridge to cool completely, 3-4 hours.\n" +
                "13. To make the strawberry topping, combine the sugar and cornstarch in a large saucepan. Stir in the strawberry puree.\n" +
                "14. Cook over medium heat, stir constantly until mixture thickens and come to a boil, about 15 to 20 minutes.\n" +
                "15. Allow to boil for 1 1/2 minutes, then remove from heat.\n" +
                "16. Stir in vanilla extract and chopped strawberries and allow to cool for about 15-20 minutes.\n" +
                "17. Pour the topping over the cheesecake and allow to cool completely, 3-4 hours or overnight. You could serve the sauce immediately, but it will thicken as it cools.\n" +
                "18. To make the whipped cream, add everything to a large mixer bowl and whip on high speed until stiff peaks form. Serve slices of the cheesecake with the whipped cream, if desired.", u4);
        re3.getIngredients().add(new RecipeIngredients(re3, i8));
        re3.getIngredients().add(new RecipeIngredients(re3, i12));
        re3.getIngredients().add(new RecipeIngredients(re3, i17));
        re3.getIngredients().add(new RecipeIngredients(re3, i18));
        re3.getIngredients().add(new RecipeIngredients(re3, i19));
        re3.getIngredients().add(new RecipeIngredients(re3, i20));
        re3.getIngredients().add(new RecipeIngredients(re3, i21));
        re3.getIngredients().add(new RecipeIngredients(re3, i22));
        re3.getIngredients().add(new RecipeIngredients(re3, i23));
        re3.getIngredients().add(new RecipeIngredients(re3, i24));
        re3.getIngredients().add(new RecipeIngredients(re3, i25));
        re3.getIngredients().add(new RecipeIngredients(re3, i26));
        re3.getIngredients().add(new RecipeIngredients(re3, i27));
        re3.getIngredients().add(new RecipeIngredients(re3, i28));
        re3.getIngredients().add(new RecipeIngredients(re3, i29));
        re3.getIngredients().add(new RecipeIngredients(re3, i30));
        re3.getIngredients().add(new RecipeIngredients(re3, i31));


        re3.getCategories().add(new RecipeCategory(re3, cat4));
        re3.getCategories().add(new RecipeCategory(re3, cat5));
        re3.getCategories().add(new RecipeCategory(re3, cat6));

        Recipe re4 = new Recipe("Best Brownies", "Aunt", "25 minutes", "1. Preheat oven to 350 degrees F (175 degrees C). Grease and flour an 8-inch square pan.\n" +
                "2. In a large saucepan, melt 1/2 cup butter. Remove from heat, and stir in sugar, eggs, and 1 teaspoon vanilla. Beat in 1/3 cup cocoa, 1/2 cup flour, salt, and baking powder. Spread batter into prepared pan.\n" +
                "3. Bake in preheated oven for 25 to 30 minutes. Do not overcook.\n" +
                "4. To Make Frosting: Combine 3 tablespoons softened butter, 3 tablespoons cocoa, honey, 1 teaspoon vanilla extract, and 1 cup confectioners' sugar. Stir until smooth. Frost brownies while they are still warm.", u5);
        re4.getIngredients().add(new RecipeIngredients(re4, i12));
        re4.getIngredients().add(new RecipeIngredients(re4, i19));
        re4.getIngredients().add(new RecipeIngredients(re4, i28));
        re4.getIngredients().add(new RecipeIngredients(re4, i32));
        re4.getIngredients().add(new RecipeIngredients(re4, i33));
        re4.getIngredients().add(new RecipeIngredients(re4, i34));
        re4.getIngredients().add(new RecipeIngredients(re4, i35));
        re4.getIngredients().add(new RecipeIngredients(re4, i36));
        re4.getIngredients().add(new RecipeIngredients(re4, i37));
        re4.getIngredients().add(new RecipeIngredients(re4, i38));
        re4.getIngredients().add(new RecipeIngredients(re4, i39));

        re4.getCategories().add(new RecipeCategory(re4, cat4));
        re4.getCategories().add(new RecipeCategory(re4, cat5));
        re4.getCategories().add(new RecipeCategory(re4, cat7));
        re4.getCategories().add(new RecipeCategory(re4, cat8));

        Recipe re5 = new Recipe ("Weekday Meal-prep Pesto Chicken & Veggies", "Internet", "10 minutes", "1. In a large pan, heat olive oil and add chicken thighs\n" +
                "2. Season with salt and pepper. When the chicken is completely cooked through, remove from pan.\n" +
                "3. Slice into strips, and set aside.\n" + "4. Add green beans and cook until crisp tender.\n" +
                "5. Return the chicken strips to the pan, then add tomatoes and pesto. Stir until fully incorporated.\n" +
                "6. Serve immediately or divide into 4 food storage containers and store in the refrigerator. Can be kept refrigerated for up to 4 days.\n" +
                "7. Enjoy!", u6);

        re5.getIngredients().add(new RecipeIngredients(re5, i40));
        re5.getIngredients().add(new RecipeIngredients(re5, i41));
        re5.getIngredients().add(new RecipeIngredients(re5, i42));
        re5.getIngredients().add(new RecipeIngredients(re5, i43));
        re5.getIngredients().add(new RecipeIngredients(re5, i44));
        re5.getIngredients().add(new RecipeIngredients(re5, i45));

        re5.getCategories().add(new RecipeCategory(re5, cat9));
        re5.getCategories().add(new RecipeCategory(re5, cat10));

        re1 = recipeService.save(re1);
        re2 = recipeService.save(re2);
        re3 = recipeService.save(re3);
        re4 = recipeService.save(re4);
        re5 = recipeService.save(re5);

    }
}
