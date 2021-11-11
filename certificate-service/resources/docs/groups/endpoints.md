# Endpoints


## /




> Example request:

```bash
curl -X GET \
    -G "http://localhost/" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json"
```

```javascript
const url = new URL(
    "http://localhost/"
);

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};


fetch(url, {
    method: "GET",
    headers: headers,
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json

Lumen (8.0.1) (Laravel Components ^8.0)
```

### Request
<small class="badge badge-green">GET</small>
 **`/`**



## Register as a new user




> Example request:

```bash
curl -X POST \
    "http://localhost/api/v1/register" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{"name":"hic","email":"id","password":"earum"}'

```

```javascript
const url = new URL(
    "http://localhost/api/v1/register"
);

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};

let body = {
    "name": "hic",
    "email": "id",
    "password": "earum"
}

fetch(url, {
    method: "POST",
    headers: headers,
    body: body
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json
{
    "success": true,
    "status": 201,
    "message": "User created successfully",
    "data": {
        "name": "Jesutomiwa Salam",
        "email": "tolak@gmail.com",
        "updated_at": "2020-09-22T06:53:20.000000Z",
        "created_at": "2020-09-22T06:53:20.000000Z",
        "id": 4
    }
}
```

### Request
<small class="badge badge-black">POST</small>
 **`api/v1/register`**

<h4 class="fancy-heading-panel"><b>Body Parameters</b></h4>
<code><b>name</b></code>&nbsp; <small>string</small>     <br>
    The name of the user

<code><b>email</b></code>&nbsp; <small>string</small>     <br>
    The email of the user

<code><b>password</b></code>&nbsp; <small>string</small>     <br>
    The password



## User login endpoint, returns an instance of the user and an authentication access token




> Example request:

```bash
curl -X POST \
    "http://localhost/api/v1/login" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{"email":"et","password":"qui"}'

```

```javascript
const url = new URL(
    "http://localhost/api/v1/login"
);

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};

let body = {
    "email": "et",
    "password": "qui"
}

fetch(url, {
    method: "POST",
    headers: headers,
    body: body
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json
{
    "success": true,
    "status": 201,
    "message": null,
    "data": {
        "user": {
            "id": 4,
            "name": "Jesutomiwa Salam",
            "email": "tolak@gmail.com",
            "created_at": "2020-09-22T06:53:20.000000Z",
            "updated_at": "2020-09-22T06:53:20.000000Z"
        },
        "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6ODAwMFwvYXBpXC92MVwvbG9naW4iLCJpYXQiOjE2MDA3NTc3ODMsImV4cCI6MTYwMDc2MTM4MywibmJmIjoxNjAwNzU3NzgzLCJqdGkiOiI1b0FjTDg1MkYxN1hZTVJWIiwic3ViIjo0LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.KoKXz3W-D0EhSA71ypI34RjfqCDRhLPlQzCgj5sOKm0",
        "token_type": "bearer",
        "expires_in": 3600
    }
}
```

### Request
<small class="badge badge-black">POST</small>
 **`api/v1/login`**

<h4 class="fancy-heading-panel"><b>Body Parameters</b></h4>
<code><b>email</b></code>&nbsp; <small>string</small>     <br>
    The email of the user

<code><b>password</b></code>&nbsp; <small>string</small>     <br>
    The password



## Get all users

<small class="badge badge-darkred">requires authentication</small>



> Example request:

```bash
curl -X GET \
    -G "http://localhost/api/v1/users?q=impedit&perPage=7&page=15" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json"
```

```javascript
const url = new URL(
    "http://localhost/api/v1/users"
);

let params = {
    "q": "impedit",
    "perPage": "7",
    "page": "15",
};
Object.keys(params)
    .forEach(key => url.searchParams.append(key, params[key]));

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};


fetch(url, {
    method: "GET",
    headers: headers,
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json

{
 "success": true,
 "status": 200,
 "message": null,
 "data": {
    "current_page": 1,
  "data": [
      {
          "id": 2,
          "name": "Adeosun Courtney",
          "email": "courtney@court.ng",
          "created_at": "2020-09-21T19:24:55.000000Z",
          "updated_at": "2020-09-21T19:24:55.000000Z"
      },
      {
          "id": 1,
          "name": "Jesutomiwa Salam",
          "email": "jsalam886@me.ng",
          "created_at": "2020-09-21T18:49:02.000000Z",
          "updated_at": "2020-09-21T18:49:02.000000Z"
      },
      {
          "id": 4,
          "name": "Jesutomiwa Salam",
          "email": "tolak@gmail.com",
          "created_at": "2020-09-22T06:53:20.000000Z",
          "updated_at": "2020-09-22T06:53:20.000000Z"
      },
      {
          "id": 3,
          "name": "Mr Kola",
          "email": "tola@yahoo.com",
          "created_at": "2020-09-21T19:36:41.000000Z",
          "updated_at": "2020-09-21T19:36:41.000000Z"
      }
  ],
  "first_page_url": "http://localhost:8000/api/v1/users?page=1",
  "from": 1,
  "next_page_url": null,
  "path": "http://localhost:8000/api/v1/users",
  "per_page": 20,
  "prev_page_url": null,
  "to": 4
          }
  }
}
```

### Request
<small class="badge badge-green">GET</small>
 **`api/v1/users`**

<h4 class="fancy-heading-panel"><b>Query Parameters</b></h4>
<code><b>q</b></code>&nbsp; <small>string</small>         <i>optional</i>    <br>
    A search query field

<code><b>perPage</b></code>&nbsp; <small>string</small>         <i>optional</i>    <br>
    Number of results to be returned per page. Defaults to 20

<code><b>page</b></code>&nbsp; <small>string</small>         <i>optional</i>    <br>
    The page to be returned. Defaults to 1



## Get a single user instance

<small class="badge badge-darkred">requires authentication</small>



> Example request:

```bash
curl -X GET \
    -G "http://localhost/api/v1/users/{id}" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json"
```

```javascript
const url = new URL(
    "http://localhost/api/v1/users/{id}"
);

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};


fetch(url, {
    method: "GET",
    headers: headers,
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json
{
    "success": true,
    "status": 200,
    "message": null,
    "data": {
        "name": "Jesutomiwa Salam",
        "email": "tolak@gmail.com",
        "updated_at": "2020-09-22T06:53:20.000000Z",
        "created_at": "2020-09-22T06:53:20.000000Z",
        "id": 4
    }
}
```

### Request
<small class="badge badge-green">GET</small>
 **`api/v1/users/{id}`**



## Get profile of current logged in user

<small class="badge badge-darkred">requires authentication</small>



> Example request:

```bash
curl -X GET \
    -G "http://localhost/api/v1/profile" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json"
```

```javascript
const url = new URL(
    "http://localhost/api/v1/profile"
);

let headers = {
    "Content-Type": "application/json",
    "Accept": "application/json",
};


fetch(url, {
    method: "GET",
    headers: headers,
})
    .then(response => response.json())
    .then(json => console.log(json));
```


> Example response (200):

```json
{
    "success": true,
    "status": 200,
    "message": null,
    "data": {
        "name": "Jesutomiwa Salam",
        "email": "tolak@gmail.com",
        "updated_at": "2020-09-22T06:53:20.000000Z",
        "created_at": "2020-09-22T06:53:20.000000Z",
        "id": 4
    }
}
```

### Request
<small class="badge badge-green">GET</small>
 **`api/v1/profile`**




