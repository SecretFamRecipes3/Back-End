
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
    "userid": 3,
    "username": "admin",
    "email": "admin@email.com",
    "password": "password",
    "recipe": [
            "ingredients:[]
             ]
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
 