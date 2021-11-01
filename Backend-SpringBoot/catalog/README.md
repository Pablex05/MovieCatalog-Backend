![madewith](https://img.shields.io/badge/made%20with-SpringBoot-green?logo=spring&style=for-the-badge)

# Connect to Data Base

**Set up application.properties**

Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)

    spring.datasource.url
    
    spring.datasource.username
    
    spring.datasource.password

The SQL dialect makes Hibernate generate better SQL for the chosen database

    spring.jpa.properties.hibernate.dialect
    
    logging.level.org.hibernate.SQL

Hibernate ddl auto (create, create-drop, validate, update)

    spring.jpa.hibernate.ddl-auto

App Security Properties

    app.jwtSecret= SecretKey

    app.jwtExpirationMs= 86400000

    app.jwtRefreshExpirationMs= 86400000

# HOW TO USE?

## First add some rows into roles table before assigning any role to User

**Run following SQL insert statements:**

    INSERT INTO roles(name) VALUES('ROLE_USER');

    INSERT INTO roles(name) VALUES('ROLE_ADMIN');

## User Authentication

### Register

POST localhost:8080/api/auth/register

    {
    "username": "Username",
    "email": "Email",
    "password":"Password",
    "role":["admin", "user"] or ["user"] or ["admin"]
    }

### Login

POST localhost:8080/api/auth/login

    {
    "username": "Username",
    "password":"Password",
    }

### Refresh token

POST localhost:8080/api/auth/refreshtoken

    {
    "refreshToken":"refresh-token"
    }

### Refresh token

POST localhost:8080/api/auth/logout

    {
    "userId":id
    }

## User Test

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/api/auth/test/all

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/api/auth/test/user

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/api/auth/test/admin

## ***Once logged in, use the token obtained in the Authorization field of postman with the type "Bearer token"***

## Movies

JSON FORMAT:

    {
    "Title": "MovieTitle",
    "Genre": "HORROR",
    "Release Date": "2021-06-05",
    "Duration": "01:30:00",
    "Trailer": "url",
    "Language": "ENGLISH",
    "Subtitle": "SPANISH",
    "Cast": [
        "actor1"
        ],
    "Director": "director1"
}

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/movie/getAll

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/movie/getById/{id}

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/movie/getByTitle/{title}

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    localhost:8080/movie/add

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/movie/edit/{id}

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    http://localhost:8080/movie/delete/{id}

## Actors

JSON FORMAT:

    {   
    "Name": "name",
    }

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg) 

    http://localhost:8080/actor/getAll

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/actor/getById/{id}

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/actor/getByName/{name}

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    http://localhost:8080/actor/add

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/actor/edit/{id}

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    localhost:8080/actor/delete/{id}

## Directors

JSON FORMAT:

    {   
    "Name": "name",
    }

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg) 

    http://localhost:8080/director/getAll

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/movie/getById/{id}

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    http://localhost:8080/director/getByName/{name}

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    localhost:8080/director/add

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/director/edit/{id}

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    localhost:8080/director/delete/{id}
