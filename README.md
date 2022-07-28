
# Social API

Social App API like Twitter in java (Spring Boot, H2)

## Requirements

* Java 11
* Gradle (to compile and run test)
## Installation

Install with gradle

```bash
  ./gradlew bootRun
```
    
## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```


## API Reference

### Create new user

Create new user.

```http
  POST /api/v1/user/
```

#### *Request*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. User name|

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | User Id|
| `name`      | `string` | User name|

### Get user by ID

Get user details by the user id.

```http
  GET /api/v1/user/{Id}
```

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | User Id|
| `name`      | `string` | User name|

### Get user wall 

Get posts added by the user.

```http
  GET /api/v1/user/{Id}/wall
```

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `List` | List of posts|
| `id`      | `int` | Post Id|
| `message`      | `string` | Post Message|
| `created`      | `string` | Post created|
| `owner`      | `User` | Post owner|

### Get user timeline

Get posts added by the users follow by user id.

```http
  GET /api/v1/user/{Id}/timeline
```

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `List` | List of posts|
| `id`      | `int` | Post Id|
| `message`      | `string` | Post Message|
| `created`      | `string` | Post created|
| `owner`      | `User` | Post owner|

### Add new follow

Add new follow user by the user.

```http
  POST /api/v1/user/{Id}/follow
```

#### *Request*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Follow user|


### Add new post

Add new post.

*If the owner does not exist, a new user/owner will be created with the given data.*

```http
  POST /api/v1/post
```

#### *Request*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `message`      | `string` | **Required**. Post message|
| `owner`      | `User` | Post owner|

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Post Id|
| `message`      | `string` | Post Message|
| `created`      | `string` | Post created|
| `owner`      | `User` | Post owner|

### Get post

Get posts details by the post id

```http
  GET /api/v1/post/{Id}
```

#### *Response*
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Post Id|
| `message`      | `string` | Post Message|
| `created`      | `string` | Post created|
| `owner`      | `User` | Post owner|



## Postman collection

File *SocialApi.postman_collection.json* contains examples of API usage