
# Back-End  

## API: http://hsmm-secretfamilyrecipe.herokuapp.com

### Table of Contents  
[Register and Login](#register-and-login)  
[User](#user)  
[Recipe](#recipe)  
[Ingredients](#ingredients)
[Category](#category)

## REGISTER and LOGIN

#### When registering a user the minimum required is
```
{
    "username": "someusername",
    "password": "somepassword",
    "email": "someEmail@email.local"
}

```
#
#### Axios call for register / create new user
````
axios.post('http://hsmm-secretfamilyrecipe.herokuapp.com/createnewuser', state.credentials)
````
#
#### The login axios request should look like

```
axios.post('http://hsmm-secretfamilyrecipe.herokuapp.com/login', `grant_type=password&username=${credentials.username}&password=${credentials.password}`, {
      headers: {
        // btoa is converting our client id/client secret into base64
        Authorization: `Basic ${btoa('XXXXXXX:XXXXXXXX')}`,
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
```
| Type  | Endpoint       | What it does                   | required                                  |
| :--:  | :-------:      | :----------------------------: | :---------------------------------------: |
| POST  | /createnewuser | register user and return token | **username**, **email**, and **password** |
| POST  | /login         | logs in user and returns token | **username** and **password**             |
| GET   | /logout        | removes user token from store  |   

## USER  
In order to change any user information the request must come from an admin or  
the corresponding user to the id provided in the endpoint. 

```
{
    [
        {
            "userid": 10,
            "username": "admin",
            "email": "admin@email.com",
            "roles": [
                {
                    "role": {
                        "roleid": 1,
                        "name": "ADMIN"
                    }
                }
            ],
            "recipes": [
                {
                    "recipeid": 11,
                    "title": "Fried Mozzarella Puffs",
                    "source": "Mom",
                    "ingredients": [
                        {
                            "ingredient": {
                                "ingredientid": 3,
                                "name": "Unsalted Butter",
                                "amount": "1 tablespoon"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 4,
                                "name": "All purpose flour",
                                "amount": "2 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 5,
                                "name": "Egg",
                                "amount": "1 large egg"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 6,
                                "name": "Mozzarella cheese",
                                "amount": "4 ounces"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 7,
                                "name": "Oil",
                                "amount": "4 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 8,
                                "name": "Water",
                                "amount": "1/3 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 9,
                                "name": "Marinara sauce",
                                "amount": "1 cups"
                            }
                        }
                    ],
                    "instruction": "Combine water, butter, and salt in a saucepan over medium-high heat. Bring to a simmer; pour in flour all at once and reduce heat to medium. Stir with a wooden spoon or spatula until a dough starts coming together. Cook, scraping up and stirring the dough, for 2 to 3 minutes.Remove from heat; transfer dough to a mixing bowl. Let cool until no longer hot but still very warm, 5 to 10 minutes. Add egg and season with cayenne and freshly ground black pepper. Whisk vigorously until mixture combines into a very soft, sticky dough. Switch to a spatula and scrape dough into a ball.Seal dough and spatula with plastic wrap and refrigerate in the bowl until cool, about 1 hour.In the meantime, season marinara sauce with oregano, red pepper flakes, balsamic vinegar in a small pot over medium heat. Add anchovy fillet. Stir together and bring to a simmer. Let simmer for 10 minutes; turn off heat and let sit until ready to use.Grate mozzarella cheese over the dough and stir to combine.Heat oil to 350 degrees F (175 degrees C) in a deep fryer or heavy-duty pan over medium heat. Preheat oven to 200 degrees F (93 degrees C) or any temperature for keeping warm.Scoop out about 2 tablespoons of dough per puff and form into a football shape using two spoons. Fry puffs, 5 or 6 at a time, in the hot oil until browned, 2 to 3 minutes. Drain on paper towels. Keep puffs warm in low oven while frying the rest. Serve with hot marinara sauce."
                }
            ]
        },
}
```

| Type  | Endpoint          | What it does                                              | required                                  |
| :--:  | :----------:      | :----------------------------:                            | :---------------------------------------: |
| GET   | /users/users      | Returns full list of users                                | Token and Admin role                      |
| GET   | /users/user/{id}  | Returns specific user by id                               | Token and Admin role                      |
| GET   | /users/userinfo   | Returns current user's object                             | Token                                     |
| POST  | /users/user       | Adds new user to database and returns status of CREATED   | Token and Admin role                      |
| PUT   | /users/user/{id}  | Replaces entire user by id and returns status of OK       | Token, Admin role, and User object        |
| PATCH | /users/user/{id}  | Replaces part of user object and returns status of OK     | Token and User object                     |
|DELETE | /users/user/{id}  | Deletes user by id and returns status of OK               | Token                                     |
 
 
 ##Recipe
 In order to change any recipe information the request must come from an authenticated user 
```
"recipes": [
                {
                    "recipeid": 11,
                    "title": "Fried Mozzarella Puffs",
                    "source": "Mom",
                    "ingredients": [
                        {
                            "ingredient": {
                                "ingredientid": 3,
                                "name": "Unsalted Butter",
                                "amount": "1 tablespoon"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 4,
                                "name": "All purpose flour",
                                "amount": "2 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 5,
                                "name": "Egg",
                                "amount": "1 large egg"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 6,
                                "name": "Mozzarella cheese",
                                "amount": "4 ounces"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 7,
                                "name": "Oil",
                                "amount": "4 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 8,
                                "name": "Water",
                                "amount": "1/3 cups"
                            }
                        },
                        {
                            "ingredient": {
                                "ingredientid": 9,
                                "name": "Marinara sauce",
                                "amount": "1 cups"
                            }
                        }
                    ],
                    "instruction": "Combine water, butter, and salt in a saucepan over medium-high heat. Bring to a simmer; pour in flour all at once and reduce heat to medium. Stir with a wooden spoon or spatula until a dough starts coming together. Cook, scraping up and stirring the dough, for 2 to 3 minutes.Remove from heat; transfer dough to a mixing bowl. Let cool until no longer hot but still very warm, 5 to 10 minutes. Add egg and season with cayenne and freshly ground black pepper. Whisk vigorously until mixture combines into a very soft, sticky dough. Switch to a spatula and scrape dough into a ball.Seal dough and spatula with plastic wrap and refrigerate in the bowl until cool, about 1 hour.In the meantime, season marinara sauce with oregano, red pepper flakes, balsamic vinegar in a small pot over medium heat. Add anchovy fillet. Stir together and bring to a simmer. Let simmer for 10 minutes; turn off heat and let sit until ready to use.Grate mozzarella cheese over the dough and stir to combine.Heat oil to 350 degrees F (175 degrees C) in a deep fryer or heavy-duty pan over medium heat. Preheat oven to 200 degrees F (93 degrees C) or any temperature for keeping warm.Scoop out about 2 tablespoons of dough per puff and form into a football shape using two spoons. Fry puffs, 5 or 6 at a time, in the hot oil until browned, 2 to 3 minutes. Drain on paper towels. Keep puffs warm in low oven while frying the rest. Serve with hot marinara sauce."
                }
            ]
```

| Type  | Endpoint                | What it does                                               | required                                  |
| :--:  | :----------:            | :----------------------------:                             | :---------------------------------------: |
| GET   | /recipes/recipes        | Returns full list of recipes                               | No Token Required                         |
| GET   |/recipes/recipe/{id}     | Returns specific recipe by id                              | Token                                     |
| POST  | /recipes/recipe         | Adds new recipe with status of CREATED                     | Token                                     |
| PUT   | recipes/recipe/{id}     | Edit a recipe with status of OK                            | Token                                     |
| DELETE|/recipes/recipe/{id}     | Deletes a recipe of the id provided                        | Token                                     |
| GET   |/recipes/ingredients     | Returns full list of ingredients                           | Token                                     |
| GET   |/recipes/ingredient/{id} | Returns ingredient by id                                   | Token                                     |
| POST  |/recipes/ingredient      | Creates new ingredient with status of CREATED              | Token                                     |
| PUT   |/recipes/ingredient/{id} | Updated ingredient by Id                                   | Token                                     |
| DELETE|/recipes/ingredient/{id} | Deletes specific ingredient by id                          | Token                                     | 
                                  
| GET   |/recipes/categories      | Returns full list of categories                            | Token                                     |
| GET   |/recipes/category/{id}   | Returns category by id                                     | Token                                     |
| POST  |/recipes/category        | Creates new category with status of CREATED                | Token                                     |
| PUT   |/recipes/category/{id}   | Updated category by Id                                     | Token                                     |
| DELETE|/recipes/category/{id}   | Deletes specific category by id                            | Token                                     |
