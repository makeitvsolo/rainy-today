# rainy-today

A web application for viewing the current weather.

[Technical requirements](https://zhukovsd.github.io/java-backend-learning-course/Projects/WeatherViewer/).

![rainytoday.jpg](img/rainytoday.jpg)


## Technologies / tools:
- Spring Boot
- Spring Data JPA
- Spring Security
- Docker
- Gradle
- OpenAPI


## API overview

### access

#### POST `/access/sign-up`

Sign up new account.

Request body:
```json
{
  "name": "account",
  "password": "passwd"
}
```

Ok response (HttpStatus 201 Created):
```json
{}
```

Possible error responses:
- HttpStatus 409 Conflict
- HttpStatus 500 InternalServerError


#### POST `/access/sign-in`

Sign in account.

Request body:
```json
{
  "name": "account",
  "password": "passwd"
}
```

Ok response (HttpStatus 200 Ok):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQSflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

Possible error responses:
- HttpStatus 401 Unauthorized
- HttpStatus 404 Not Found
- HttpStatus 500 InternalServerError


### favourites

#### POST `/favourites/add`

Adds given location to favourites.

Request body:
```json
{
  "name": "Ontario",
  "latitude": 34.065,
  "longitude": -117.648
}
```

Ok response (HttpStatus 201 Created):
```json
{}
```

Possible error responses:
- HttpStatus 409 Conflict
- HttpStatus 401 Unauthorized
- HttpStatus 500 InternalServerError

#### DELETE `/favourites/{id}/remove`

Removes given location from favourites.

Request body:
```json
{}
```

Ok response (HttpStatus 204 No Content):
```json
{}
```

Possible error responses:
- HttpStatus 409 Conflict
- HttpStatus 401 Unauthorized
- HttpStatus 500 InternalServerError

### locations
#### GET `/locations/name?=:location`

Searches location by given name.

Ok response (HttpStatus 200 Ok)
```json
[
  {
    "name": "Ontario",
    "latitude": 34.065846,
    "longitude": -117.6484304
  },
  {
    "name": "Ontario",
    "latitude": 44.0265525,
    "longitude": -116.9629378
  },
  {
    "name": "Town of Ontario",
    "latitude": 43.2208968,
    "longitude": -77.2830421
  },
  {
    "name": "Ontario",
    "latitude": 40.7595418,
    "longitude": -82.5901658
  },
  {
    "name": "Ontario",
    "latitude": 43.7258037,
    "longitude": -90.59152
  }
]
```

Possible error responses:
- HttpStatus 401 Unauthorized
- HttpStatus 500 InternalServerError

### weather

#### GET `/weather`

Gives weather on all favourite locations.

Ok response (HttpStatus 200 Ok):
```json
[
  {
    "summary": "Haze",
    "description": "haze",
    "location": {
      "id": 1,
      "location": "Ontario",
      "latitude": 34.07,
      "longitude": -117.65
    },
    "dateTime": "2023-09-25T15:05:04Z",
    "temperature": {
      "average": 17,
      "min": 12.99,
      "max": 22.98
    },
    "pressure": {
      "seaLevel": 0,
      "groundLevel": 0
    },
    "humidity": 79,
    "cloudiness": 0,
    "wind": {
      "speed": 3.09,
      "direction": 250,
      "gust": 0
    }
  }
]
```

Possible error responses:
- HttpStatus 401 Unauthorized
- HttpStatus 500 InternalServerError

## Build and run

You need to have [git](https://git-scm.com/) and [Docker](https://www.docker.com/) to run the application.

```shell
git clone https://github.com/makeitvsolo/rainy-today.git

cd ./rainy-today

docker-compose build

docker-compose up
```
