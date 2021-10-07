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

# HOW TO USE?

## First add some rows into roles table before assigning any role to User

**Run following SQL insert statements:**

    INSERT INTO roles(name) VALUES('ROLE_USER');

    INSERT INTO roles(name) VALUES('ROLE_ADMIN');

## User Authentication

### Signup

POST localhost:8080/api/auth/signup

    {
    "username": "Username",
    "email": "Email",
    "password":"Password",
    "role":["admin", "user"] or ["user"] or ["admin"]
    }

### Signin

POST localhost:8080/api/auth/signin

    {
    "username": "Username",
    "email": "Email",
    "password":"Password",
    "role":["admin", "user"] or ["user"] or ["admin"]
    }

## User Test

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    localhost:8080/api/all

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    localhost:8080/api/user

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    localhost:8080/api/admin

## ***Once logged in, use the token obtained in the Authorization field of postman with the type "Bearer token"***

## Movies

JSON FORMAT:

    {
    "Title":"title",
    "Trailer":"url",
    "Language":"ENGLISH",
    "Subtitle":"SPANISH",
    "Genre":"HORROR",
    "Duration":"01:30:00",
    "Release Date":"2021-06-05",
    "Cast":   
        [
        "actor1",
        "actor2"
        ],
    "Director": ["director1"]
    }

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg)

    localhost:8080/movies

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    localhost:8080/movie

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/movie/id

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    localhost:8080/movie/id

## Actors

JSON FORMAT:

    {   
    "Name": "name",
    "movies": ["title1", "title2"] 
    }

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg) 

    localhost:8080/actors

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    localhost:8080/actor

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/actor/id

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    localhost:8080/actor/id

## Directors

JSON FORMAT:

    {   
    "Name": "name",
    "movies": ["title1", "title2"] 
    }

![GET](https://img.shields.io/badge/method-GET-%3CCOLOR%3E.svg) 

    localhost:8080/directors

![GET](https://img.shields.io/badge/method-POST-yellow.svg)

    localhost:8080/director

![GET](https://img.shields.io/badge/method-PUT-blueviolet.svg)

    localhost:8080/director/id

![GET](https://img.shields.io/badge/method-DELETE-red.svg)

    localhost:8080/director/id
