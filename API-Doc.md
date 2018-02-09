# API Documentation

## Restaurant

### 1. GET http://localhost:8080/tummytruck/api/restaurants/
This api fetches all the restaurants records available in the database

Example Output:
```JSON
[
    {
        "address": "Boulder",
        "contact": "7548542471",
        "id": 1,
        "name": "Rickys Joint",
        "rating": "5.0"
    },
    {
        "address": "Boulder",
        "contact": "8748542471",
        "id": 2,
        "name": "Harrys Joint",
        "rating": "4.5"
    },
    {
        "address": "Superior",
        "contact": "7845542471",
        "id": 4,
        "name": "Superior Shack",
        "rating": "3.5"
    },
    {
        "address": "Denver",
        "contact": "7548542371",
        "id": 101,
        "name": "Adam Shakes",
        "rating": "4.0"
    }
]
```

### 2. GET http://localhost:8080/tummytruck/api/restaurants/{id}
This api fetches the restaurant with given specific id from the database

Example Output:
```JSON
{
    "address": "Denver",
    "contact": "7548542371",
    "id": 101,
    "name": "Adam Shakes",
    "rating": "4.0"
}
```

### 3. POST http://localhost:8080/tummytruck/api/restaurants/
This api posts (create) new restaurant with given specific values in the database and returns the specific message with if object is created.

Example Input:
```JSON
{
    "address": "400 Stewart Ave, Las Vegas",
    "contact": "(800) 927-7671",
    "name": "Healthy Shakes",
    "rating": "4.0"
}
```

Example Output: 
```JSON
Restaurant information saved successfully with id 10
```

### 4. DELETE http://localhost:8080/tummytruck/api/restaurants/{id}
This api delete existing restaurant with given specific key (id) in the database and returns nothing wih correct return code.

Example Input:
```Json
http://localhost:8080/tummytruck/api/restaurants/101
```


### 5. GET http://localhost:8080/tummytruck/api/restaurants/find/{nameText}
This api fetches all the restaurants records available in the database matching with the input name text.

Example Input:
```Json
http://localhost:8080/tummytruck/api/restaurants/find/sha
```

Example Output:
```JSON
[
    {
        "address": "Superior",
        "contact": "7845542471",
        "id": 4,
        "name": "Superior Shack",
        "rating": "3.5"
    },
    {
        "address": "Denver",
        "contact": "7548542371",
        "id": 10,
        "name": "Good Shakes",
        "rating": "4.0"
    },
    {
        "address": "Denver",
        "contact": "7548542371",
        "id": 17,
        "name": "Adam Shacks",
        "rating": "4.0"
    },
    {
        "address": "400 Stewart Ave, Las Vegas",
        "contact": "8009277671",
        "id": 12,
        "name": "Healthy Shakes",
        "rating": "4.0"
    }
]
```
