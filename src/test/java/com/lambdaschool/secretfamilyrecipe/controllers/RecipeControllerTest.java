package com.lambdaschool.secretfamilyrecipe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.secretfamilyrecipe.SecretfamilyrecipeApplication;
import com.lambdaschool.secretfamilyrecipe.models.*;
import com.lambdaschool.secretfamilyrecipe.services.CategoryService;
import com.lambdaschool.secretfamilyrecipe.services.IngredientService;
import com.lambdaschool.secretfamilyrecipe.services.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretfamilyrecipeApplication.class)
@WithMockUser(username = "admin")
public class RecipeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
        private RecipeService recipeService;
    @MockBean
        private IngredientService ingredientService;
    @MockBean
        private CategoryService categoryService;




    List<Recipe> recipeList = new ArrayList<>();
    List<Ingredient> ingredientList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        recipeList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        categoryList = new ArrayList<>();

        //Roles//
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        r1.setRoleid(0);
        r2.setRoleid(1);

        //Users//
        User u1 = new User("admin", "admin@email.com", "password");
        u1.setUserid(1);
        u1.getRoles().add(new UserRoles(u1, r1));

        User u2 = new User("chef boyardee" ,"chef@email.com", "password");
        u2.setUserid(2);
        u2.getRoles().add(new UserRoles(u2, r2));

        //Ingredient//
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

        i1.setIngredientid(0);
        i2.setIngredientid(1);
        i3.setIngredientid(2);
        i4.setIngredientid(3);
        i5.setIngredientid(4);
        i6.setIngredientid(5);
        i7.setIngredientid(6);

        i8.setIngredientid(7);
        i9.setIngredientid(8);
        i10.setIngredientid(9);
        i11.setIngredientid(10);
        i12.setIngredientid(11);
        i13.setIngredientid(12);
        i14.setIngredientid(13);
        i15.setIngredientid(14);
        i16.setIngredientid(15);

        ingredientList.add(i1);
        ingredientList.add(i2);
        ingredientList.add(i3);
        ingredientList.add(i4);
        ingredientList.add(i5);
        ingredientList.add(i6);
        ingredientList.add(i7);
        ingredientList.add(i8);
        ingredientList.add(i9);
        ingredientList.add(i10);
        ingredientList.add(i11);
        ingredientList.add(i12);
        ingredientList.add(i13);
        ingredientList.add(i14);
        ingredientList.add(i15);
        ingredientList.add(i16);

        // Category //
        Category cat1 = new Category("Italian");
        Category cat2 = new Category("Appetizer");
        Category cat3 = new Category("Dessert");
        Category cat4 = new Category("Bars");

        cat1.setCategoryid(0);
        cat2.setCategoryid(1);
        cat3.setCategoryid(2);
        cat4.setCategoryid(3);

        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);
        categoryList.add(cat4);

        // Recipe //
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
        re1.setRecipeid(1);

        re1.getIngredients().add(new RecipeIngredients(re1, i1));
        re1.getIngredients().add(new RecipeIngredients(re1, i2));
        re1.getIngredients().add(new RecipeIngredients(re1, i3));
        re1.getIngredients().add(new RecipeIngredients(re1, i4));
        re1.getIngredients().add(new RecipeIngredients(re1, i5));
        re1.getIngredients().add(new RecipeIngredients(re1, i6));
        re1.getIngredients().add(new RecipeIngredients(re1, i7));

        re1.getCategories().add(new RecipeCategory(re1, cat1));
        re1.getCategories().add(new RecipeCategory(re1, cat2));

        recipeList.add(re1);

        Recipe re2 = new Recipe("Key Lime Pie Bars", "Grandmother", "15 minutes", "1. Position a rack in the center of the oven and preheat the oven to 350. Spray an 8x8 square baking pan with cooking spray and line with parchment paper, set aside.\n" +
                "2. In a small bowl, stir together the graham cracker crumbs, ground pecans, sugar, and cinnamon. Add the melted butter and stir to combine. Make sure all the crumbs are moistened. Press the crumbs into the bottom of the baking pan and bake for 10 minutes. Remove from oven, leave oven on.\n" +
                "3. In the bowl of an electric mixer fitted with the whisk attachment, whisk the yolks for 2 minutes. Add the condensed milk and continue to whisk for another 2 minutes. Stop the mixer, scrape down the sides, add the lime zest. With the mixer on low, drizzle in the lime juice, let mixer run for 2 minutes once added. Pie mixture will be slightly thicker when done. Pour the filling into crust and bake for 15 minutes.\n" +
                "4. Cool completely on rack. Filling will set as it cools. Refrigerate for at least 2 hours. Slice and serve chilled.", u2);
        re2.setRecipeid(2);

        re2.getIngredients().add(new RecipeIngredients(re2, i8));
        re2.getIngredients().add(new RecipeIngredients(re2, i9));
        re2.getIngredients().add(new RecipeIngredients(re2, i10));
        re2.getIngredients().add(new RecipeIngredients(re2, i11));
        re2.getIngredients().add(new RecipeIngredients(re2, i12));
        re2.getIngredients().add(new RecipeIngredients(re2, i13));
        re2.getIngredients().add(new RecipeIngredients(re2, i14));
        re2.getIngredients().add(new RecipeIngredients(re2, i15));
        re2.getIngredients().add(new RecipeIngredients(re2, i16));

        re2.getCategories().add(new RecipeCategory(re2, cat3));
        re2.getCategories().add(new RecipeCategory(re2, cat4));

        recipeList.add(re2);

    }
    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void listAllRecipes() throws Exception{
        String apiUrl = "/recipes/recipes";
        Mockito.when(recipeService.findAll()).thenReturn(recipeList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(recipeList);

        assertEquals(er, tr);
    }

    @Test
    public void getRecipeById() throws Exception{
        String apiUrl = "/recipes/recipe/64";

        Mockito.when(recipeService.findRecipeById(64)).thenReturn(recipeList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(recipeList.get(0));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getNoRecipeById() throws Exception{
        String apiUrl = "/recipes/recipe/24";
        Mockito.when(recipeService.findRecipeById(24)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = "";

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void deleteRecipeByID() throws Exception{
        String apiUrl = "/recipes/recipe/{id}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateFullRecipe() throws Exception{
        String apiUrl = "/recipes/recipe/65";
        Recipe r1 = new Recipe();
        r1.setTitle("SOMETHING");
        r1.setSource("INTERNET");
        r1.setPreptime("60 min");
        r1.setInstruction("Google it");
        r1.setIngredients(r1.getIngredients());
        r1.setCategories(r1.getCategories());
        r1.setUser(new User());

        ObjectMapper mapper = new ObjectMapper();
        String recipeString = mapper.writeValueAsString(r1);

        Mockito.when(recipeService.save(any(Recipe.class))).thenReturn(r1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recipeString);

        mockMvc.perform(rb).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNewRecipe() throws Exception {

        String apiUrl = "/recipes/recipe";
        Recipe r1 = new Recipe();
        r1.setRecipeid(100);
        r1.setTitle("SOMETHING");
        r1.setSource("INTERNET");
        r1.setPreptime("60 min");
        r1.setInstruction("Google it");
        r1.setIngredients(r1.getIngredients());
        r1.setCategories(r1.getCategories());
        r1.setUser(new User());

        ObjectMapper mapper = new ObjectMapper();
        String recipeString = mapper.writeValueAsString(r1);

        Mockito.when(recipeService.save(any(Recipe.class))).thenReturn(r1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(recipeString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void listAllIngredients() throws Exception{
        String apiUrl = "/recipes/ingredients";
        Mockito.when(ingredientService.findAll()).thenReturn(ingredientList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(ingredientList);

        assertEquals(er, tr);
    }

    @Test
    public void getIngredientById() throws Exception{
        String apiUrl = "/recipes/ingredient/32";

        Mockito.when(ingredientService.findIngredientById(32)).thenReturn(ingredientList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(ingredientList.get(0));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getNoIngredientById() throws Exception{
        String apiUrl = "/recipes/ingredient/900";
        Mockito.when(ingredientService.findIngredientById(900)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = "";

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void deleteIngredientByID() throws Exception{
        String apiUrl = "/recipes/ingredient/{id}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateIng() throws Exception{
        String apiUrl = "/recipes/ingredient/65";
        Ingredient Ing1 = new Ingredient();

        Ing1.setName("TESTING BERRIES");
        Ing1.setAmount("HANDFUL");
        Ing1.setRecipes(Ing1.getRecipes());


        ObjectMapper mapper = new ObjectMapper();
        String ingredientString = mapper.writeValueAsString(Ing1);

        Mockito.when(ingredientService.save(any(Ingredient.class))).thenReturn(Ing1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ingredientString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNewIngr() throws Exception {

        String apiUrl = "/recipes/ingredient";
        Ingredient ingr = new Ingredient();
        ingr.setIngredientid(1000);
        ingr.setName("TESTING BALLS");
        ingr.setAmount("2 large");
        ingr.setRecipes(ingr.getRecipes());

        ObjectMapper mapper = new ObjectMapper();
        String ingredientString = mapper.writeValueAsString(ingr);

        Mockito.when(ingredientService.save(any(Ingredient.class))).thenReturn(ingr);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ingredientString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
        @Test
        public void listAllCategories() throws Exception{
            String apiUrl = "/recipes/categories";
            Mockito.when(categoryService.findAll()).thenReturn(categoryList);

            RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
            MvcResult r = mockMvc.perform(rb).andReturn();
            String tr = r.getResponse().getContentAsString();

            ObjectMapper mapper = new ObjectMapper();
            String er = mapper.writeValueAsString(categoryList);

            assertEquals(er, tr);
        }

        @Test
        public void getCategoryById() throws Exception{
            String apiUrl = "/recipes/category/82";

            Mockito.when(categoryService.findCategoryById(82)).thenReturn(categoryList.get(0));

            RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
            MvcResult r = mockMvc.perform(rb).andReturn();
            String tr = r.getResponse().getContentAsString();

            ObjectMapper mapper = new ObjectMapper();
            String er = mapper.writeValueAsString(categoryList.get(0));

            System.out.println("Expect: " + er);
            System.out.println("Actual: " + tr);

            assertEquals("Rest API Returns List", er, tr);
        }

        @Test
        public void getNoCategoryById() throws Exception{
            String apiUrl = "/recipes/category/9000";
            Mockito.when(categoryService.findCategoryById(9000)).thenReturn(null);

            RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
            MvcResult r = mockMvc.perform(rb).andReturn();
            String tr = r.getResponse().getContentAsString();

            ObjectMapper mapper = new ObjectMapper();
            String er = "";

            System.out.println("Expect: " + er);
            System.out.println("Actual: " + tr);

            assertEquals("Rest API Returns List", er, tr);
        }

        @Test
        public void deleteCategoryByID() throws Exception{
            String apiUrl = "/recipes/category/{id}";

            RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON);

            mockMvc.perform(rb).andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void updateCategories() throws Exception{
            String apiUrl = "/recipes/category/84";
            Category cat = new Category();

            cat.setCategoryname("TESTING");
            cat.setRecipes(cat.getRecipes());

            ObjectMapper mapper = new ObjectMapper();
            String catString = mapper.writeValueAsString(cat);

            Mockito.when(categoryService.save(any(Category.class))).thenReturn(cat);

            RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(catString);

            mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void addNewCat() throws Exception {

            String apiUrl = "/recipes/category";
            Category cat = new Category();

            cat.setCategoryid(1001);
            cat.setCategoryname("TESTING");
            cat.setRecipes(cat.getRecipes());

            ObjectMapper mapper = new ObjectMapper();
            String catString = mapper.writeValueAsString(cat);

            Mockito.when(categoryService.save(any(Category.class))).thenReturn(cat);

            RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(catString);

            mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        }
}
