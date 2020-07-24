# Who-am-I-task

Simple Java based Spring Boot application that exposes the PUT REST endpoint ‘/whoami’.

This endpoint returns a guess of the users age and gender based on supplied name and country code in the following format:

```
{
    "first_name":"James",
    "country_code":"GB"
}
```

The service then sends requests using this data to the agify and genderize apis and then returns the results together in the following format:

```
{
    "first_name": "James",
    "country_code": "GB",
    "age": 62,
    "gender": "male"
}
```

Both name and country code are required in the request, if no name or country code is supplied the endpoint will return HTTP 400.
