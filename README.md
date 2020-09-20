
# Back-End  

## API:

### Table of Contents  
[Register and Login](#register-and-login)  
[User](#user)  
[Recipe](#Recipe)  
[Ingredients](#Ingredients)
[Category](#Category)

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
axios.post('https://{baseURL}/createnewuser', state.credentials)
````
#
#### The login axios request should look like

```
axios.post('http://{baseURL}/login', `grant_type=password&username=${credentials.username}&password=${credentials.password}`, {
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