Base URLs:

# Authentication

- HTTP Authentication, scheme: bearer

# Auth

## POST Register

POST /auth/register

> Body Parameters

```json
{
  "name": "string",
  "password": "string",
  "confirmPassword": "string",
  "email": "string",
  "phone": "string",
  "gender": "string"
}
```

### Params

| Name              | Location | Type   | Required | Description |
|-------------------|----------|--------|----------|-------------|
| body              | body     | object | no       | none        |
| » name            | body     | string | yes      | none        |
| » password        | body     | string | yes      | none        |
| » confirmPassword | body     | string | yes      | none        |
| » email           | body     | string | yes      | none        |
| » phone           | body     | string | yes      | none        |
| » gender          | body     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "User registered successfully",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIxMkBtYWluLmNvbSIsImlhdCI6MTc0NTMzMjIzNiwiZXhwIjoxNzQ1NDE4NjM2fQ.jw_q3wolM2gMAGqx1Qt5ABWbjO-eg5RROADqTikI4wQ"
  },
  "error": null
}
```

> 400 Response

```json
{
  "status": false,
  "message": "Failed to register user",
  "data": null,
  "error": "Email Already Register "
}
```

### Responses

| HTTP Status Code | Meaning                                                          | Description | Data schema |
|------------------|------------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | none        | Inline      |
| 400              | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | object  | true     | none         |       | none        |
| »» token  | string  | true     | none         |       | none        |
| » error   | null    | true     | none         |       | none        |

HTTP Status Code **400**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | null    | true     | none         |       | none        |
| » error   | string  | true     | none         |       | none        |

## POST Login

POST /auth/login

> Body Parameters

```json
{
  "email": "string",
  "password": "string"
}
```

### Params

| Name       | Location | Type   | Required | Description |
|------------|----------|--------|----------|-------------|
| body       | body     | object | no       | none        |
| » email    | body     | string | yes      | none        |
| » password | body     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXJAbWFpbi5jb20iLCJpYXQiOjE3NDUzMzI1MjUsImV4cCI6MTc0NTQxODkyNX0.PEuPbE8mUG9KcRY_a-swvf4rodccDse8rbsQd_yvDCc"
  },
  "error": null
}
```

> 400 Response

```json
{
  "status": false,
  "message": "Internal server error",
  "data": null,
  "error": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name"
}
```

### Responses

| HTTP Status Code | Meaning                                                          | Description | Data schema |
|------------------|------------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | none        | Inline      |
| 400              | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | object  | true     | none         |       | none        |
| »» token  | string  | true     | none         |       | none        |
| » error   | null    | true     | none         |       | none        |

HTTP Status Code **400**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | null    | true     | none         |       | none        |
| » error   | string  | true     | none         |       | none        |

## GET Status User

GET /users/me

See User Data

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Current user data",
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "tester@main.com",
    "password": "$2a$10$rd9QzNUB.pOJhON21t2geuXmWTVBJmBVDM9hM73cyHpGuN6.F6qP6",
    "isAdmin": false,
    "createdAt": "2025-04-18T20:22:48.411905",
    "updatedAt": null,
    "enabled": true,
    "authorities": [],
    "username": "tester@main.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name                     | Type     | Required | Restrictions | Title | description                                                                               |
|--------------------------|----------|----------|--------------|-------|-------------------------------------------------------------------------------------------|
| » status                 | boolean  | true     | none         |       | Indicates whether the API call was successful (`true`) or failed (`false`).               |
| » message                | string   | true     | none         |       | A message from the server, providing information about the request result.                |
| » data                   | object   | true     | none         |       | The response data object returned by the API.                                             |
| »» id                    | integer  | true     | none         |       | The unique identifier of the user.                                                        |
| »» name                  | string   | true     | none         |       | The full name of the user.                                                                |
| »» email                 | string   | true     | none         |       | The registered email address of the user.                                                 |
| »» password              | string   | true     | none         |       | The hashed password of the user.                                                          |
| »» isAdmin               | boolean  | true     | none         |       | If `true`, the user is an admin; if `false`, the user is a regular customer.              |
| »» createdAt             | string   | true     | none         |       | The date and time when the user account was created.                                      |
| »» updatedAt             | null     | true     | none         |       | The date and time when the user account was last updated. May be `null` if never updated. |
| »» enabled               | boolean  | true     | none         |       | Indicates whether the user account is enabled.                                            |
| »» authorities           | [string] | true     | none         |       | List of roles or permissions granted to the user.                                         |
| »» username              | string   | true     | none         |       | The username used for authentication, typically the email.                                |
| »» accountNonExpired     | boolean  | true     | none         |       | Indicates whether the user's account is not expired.                                      |
| »» accountNonLocked      | boolean  | true     | none         |       | Indicates whether the user's account is not locked.                                       |
| »» credentialsNonExpired | boolean  | true     | none         |       | Indicates whether the user's credentials (password) are still valid (not expired).        |
| » error                  | null     | true     | none         |       | Detailed error information if the API call fails; `null` if successful.                   |

## POST Register Admin

POST /auth/register-admin

Adding Admin

> Body Parameters

```json
{
  "email": "string",
  "password": "string",
  "name": "string"
}
```

### Params

| Name       | Location | Type   | Required | Description |
|------------|----------|--------|----------|-------------|
| body       | body     | object | no       | none        |
| » email    | body     | string | yes      | none        |
| » password | body     | string | yes      | none        |
| » name     | body     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Admin registered successfully",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTc0NTMzMjM1NiwiZXhwIjoxNzQ1NDE4NzU2fQ.sYLFYnszUqlC9Tk5Ig9YMD0mptQN7hpEkD25iQ2C0l0"
  },
  "error": null
}
```

> 400 Response

```json
{
  "status": false,
  "message": "Internal server error",
  "data": null,
  "error": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name"
}
```

### Responses

| HTTP Status Code | Meaning                                                          | Description | Data schema |
|------------------|------------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | none        | Inline      |
| 400              | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name      | Type    | Required | Restrictions | Title | description                         |
|-----------|---------|----------|--------------|-------|-------------------------------------|
| » status  | boolean | true     | none         |       | none                                |
| » message | string  | true     | none         |       | Status to knowing Success or Failed |
| » data    | object  | true     | none         |       | none                                |
| »» token  | string  | true     | none         |       | Token For Header Auth Bearer Token  |
| » error   | null    | true     | none         |       | none                                |

# Book

## GET Get All Book

GET /book

See Book

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Books fetched successfully",
  "data": [
    {
      "id": 1,
      "title": "Jacker",
      "basePrice": 10000,
      "type": "NEW_RELEASE",
      "createdAt": "2025-04-19T23:24:50.661225",
      "updatedAt": "2025-04-19T23:27:22.546514"
    },
    {
      "id": 3,
      "title": "LORD OF THE RINGS",
      "basePrice": 10000,
      "type": "NEW_RELEASE",
      "createdAt": "2025-04-19T23:35:49.559762",
      "updatedAt": null
    },
    {
      "id": 4,
      "title": "SHAPIENS",
      "basePrice": 10000,
      "type": "NEW_RELEASE",
      "createdAt": "2025-04-22T15:44:55.994089",
      "updatedAt": null
    },
    {
      "id": 5,
      "title": "LOGIKA MISTIKA",
      "basePrice": 10000,
      "type": "OLD_EDITION",
      "createdAt": "2025-04-22T15:47:04.965603",
      "updatedAt": null
    },
    {
      "id": 6,
      "title": "Marmut Merah Jambu",
      "basePrice": 10000,
      "type": "OLD_EDITION",
      "createdAt": "2025-04-22T15:47:27.476488",
      "updatedAt": null
    },
    {
      "id": 7,
      "title": "Men Are from Mars, Women Are from Venus",
      "basePrice": 10000,
      "type": "REGULAR",
      "createdAt": "2025-04-22T15:48:06.79947",
      "updatedAt": null
    }
  ],
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name         | Type        | Required | Restrictions | Title | description |
|--------------|-------------|----------|--------------|-------|-------------|
| » status     | boolean     | true     | none         |       | none        |
| » message    | string      | true     | none         |       | none        |
| » data       | [object]    | true     | none         |       | none        |
| »» id        | integer     | true     | none         |       | none        |
| »» title     | string      | true     | none         |       | none        |
| »» basePrice | integer     | true     | none         |       | none        |
| »» type      | string      | true     | none         |       | none        |
| »» createdAt | string      | true     | none         |       | none        |
| »» updatedAt | string¦null | true     | none         |       | none        |
| » error      | null        | true     | none         |       | none        |

## POST Add Book

POST /books

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Book created successfully",
  "data": {
    "id": 8,
    "title": "Jojo Bizare Advanture",
    "basePrice": 10000,
    "type": "OLD_EDITION",
    "createdAt": "2025-04-22T21:59:44.346715602",
    "updatedAt": null
  },
  "error": null
}
```

> 400 Response

```json
{
  "status": false,
  "message": "Internal server error",
  "data": null,
  "error": "JSON parse error: Unexpected character ('}' (code 125)): was expecting double-quote to start field name"
}
```

### Responses

| HTTP Status Code | Meaning                                                          | Description | Data schema |
|------------------|------------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | none        | Inline      |
| 400              | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name         | Type    | Required | Restrictions | Title | description |
|--------------|---------|----------|--------------|-------|-------------|
| » status     | boolean | true     | none         |       | none        |
| » message    | string  | true     | none         |       | none        |
| » data       | object  | true     | none         |       | none        |
| »» id        | integer | true     | none         |       | none        |
| »» title     | string  | true     | none         |       | none        |
| »» basePrice | integer | true     | none         |       | none        |
| »» type      | string  | true     | none         |       | none        |
| »» createdAt | string  | true     | none         |       | none        |
| »» updatedAt | null    | true     | none         |       | none        |
| » error      | null    | true     | none         |       | none        |

HTTP Status Code **400**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | null    | true     | none         |       | none        |
| » error   | string  | true     | none         |       | none        |

## GET Find By Id

GET /books/{id}

### Params

| Name | Location | Type   | Required | Description |
|------|----------|--------|----------|-------------|
| id   | path     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Book fetched successfully",
  "data": {
    "id": 1,
    "title": "Jacker",
    "basePrice": 10000,
    "type": "NEW_RELEASE",
    "createdAt": "2025-04-19T23:24:50.661225",
    "updatedAt": "2025-04-19T23:27:22.546514"
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name         | Type    | Required | Restrictions | Title | description |
|--------------|---------|----------|--------------|-------|-------------|
| » status     | boolean | true     | none         |       | none        |
| » message    | string  | true     | none         |       | none        |
| » data       | object  | true     | none         |       | none        |
| »» id        | integer | true     | none         |       | none        |
| »» title     | string  | true     | none         |       | none        |
| »» basePrice | integer | true     | none         |       | none        |
| »» type      | string  | true     | none         |       | none        |
| »» createdAt | string  | true     | none         |       | none        |
| »» updatedAt | string  | true     | none         |       | none        |
| » error      | null    | true     | none         |       | none        |

## PUT Update

PUT /books/{id}

### Params

| Name | Location | Type   | Required | Description |
|------|----------|--------|----------|-------------|
| id   | path     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Book updated successfully",
  "data": {
    "id": 1,
    "title": "Jacker",
    "basePrice": 10000,
    "type": "NEW_RELEASE",
    "createdAt": "2025-04-19T23:24:50.661225",
    "updatedAt": "2025-04-19T23:27:22.546514"
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

## DELETE Delete Book

DELETE /book/{id}

### Params

| Name | Location | Type   | Required | Description |
|------|----------|--------|----------|-------------|
| id   | path     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Book deleted successfully",
  "data": null,
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

# Purchase

## POST Purchasing Book

POST /api/purchases

> Body Parameters

```json
{
  "email": "string",
  "bookId": 0,
  "quantity": 0
}
```

### Params

| Name       | Location | Type    | Required | Description |
|------------|----------|---------|----------|-------------|
| body       | body     | object  | no       | none        |
| » email    | body     | string  | yes      | none        |
| » bookId   | body     | integer | yes      | none        |
| » quantity | body     | integer | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Purchase successful",
  "data": {
    "id": 4,
    "bookTitle": "LOGIKA MISTIKA",
    "bookType": "OLD_EDITION",
    "quantity": 6,
    "totalPrice": 60000,
    "purchasedAt": "2025-04-22T22:04:37.210910039"
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type    | Required | Restrictions | Title | description |
|----------------|---------|----------|--------------|-------|-------------|
| » status       | boolean | true     | none         |       | none        |
| » message      | string  | true     | none         |       | none        |
| » data         | object  | true     | none         |       | none        |
| »» id          | integer | true     | none         |       | none        |
| »» bookTitle   | string  | true     | none         |       | none        |
| »» bookType    | string  | true     | none         |       | none        |
| »» quantity    | integer | true     | none         |       | none        |
| »» totalPrice  | integer | true     | none         |       | none        |
| »» purchasedAt | string  | true     | none         |       | none        |
| » error        | null    | true     | none         |       | none        |

## GET All Purchase

GET /purchase

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Purchase history loaded",
  "data": [
    {
      "id": 1,
      "bookTitle": "Jacker",
      "bookType": "NEW_RELEASE",
      "quantity": 1,
      "totalPrice": 10000,
      "purchasedAt": "2025-04-20T20:15:00.538929"
    },
    {
      "id": 2,
      "bookTitle": "Jacker",
      "bookType": "NEW_RELEASE",
      "quantity": 1,
      "totalPrice": 10000,
      "purchasedAt": "2025-04-21T13:37:25.051455"
    },
    {
      "id": 3,
      "bookTitle": "LOGIKA MISTIKA",
      "bookType": "OLD_EDITION",
      "quantity": 1,
      "totalPrice": 10000,
      "purchasedAt": "2025-04-22T15:48:32.911475"
    },
    {
      "id": 4,
      "bookTitle": "LOGIKA MISTIKA",
      "bookType": "OLD_EDITION",
      "quantity": 6,
      "totalPrice": 60000,
      "purchasedAt": "2025-04-22T22:04:37.21091"
    }
  ],
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type     | Required | Restrictions | Title | description |
|----------------|----------|----------|--------------|-------|-------------|
| » status       | boolean  | true     | none         |       | none        |
| » message      | string   | true     | none         |       | none        |
| » data         | [object] | true     | none         |       | none        |
| »» id          | integer  | true     | none         |       | none        |
| »» bookTitle   | string   | true     | none         |       | none        |
| »» bookType    | string   | true     | none         |       | none        |
| »» quantity    | integer  | true     | none         |       | none        |
| »» totalPrice  | integer  | true     | none         |       | none        |
| »» purchasedAt | string   | true     | none         |       | none        |
| » error        | null     | true     | none         |       | none        |

## POST Loyalty Point

POST /loyalty/points

> Body Parameters

```json
{
  "email": "string"
}
```

### Params

| Name    | Location | Type   | Required | Description |
|---------|----------|--------|----------|-------------|
| body    | body     | object | no       | none        |
| » email | body     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Total loyalty points retrieved",
  "data": 14,
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

## GET Get User Loyal Point

GET /loyalty/points-user

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Total loyalty points retrieved",
  "data": 14,
  "error": null
}
```

> 403 Response

```json
{
  "status": false,
  "message": "Forbidden: You don't have permission to access this resource",
  "data": null,
  "error": "You are not authorized to access this resource"
}
```

### Responses

| HTTP Status Code | Meaning                                                        | Description | Data schema |
|------------------|----------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)        | none        | Inline      |
| 403              | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name      | Type    | Required | Restrictions | Title | description           |
|-----------|---------|----------|--------------|-------|-----------------------|
| » status  | boolean | true     | none         |       | none                  |
| » message | string  | true     | none         |       | none                  |
| » data    | integer | true     | none         |       | this is loyalti point |
| » error   | null    | true     | none         |       | none                  |

HTTP Status Code **403**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | null    | true     | none         |       | none        |
| » error   | string  | true     | none         |       | none        |

# Reward

## POST Add Reward

POST /rewards

MenamBah Kan Jenis Reward

> Body Parameters

```json
{
  "title": "string",
  "bookId": 0,
  "requiredPoints": 0,
  "stock": 0,
  "isActive": true
}
```

### Params

| Name             | Location | Type    | Required | Description |
|------------------|----------|---------|----------|-------------|
| body             | body     | object  | no       | none        |
| » title          | body     | string  | yes      | none        |
| » bookId         | body     | integer | yes      | none        |
| » requiredPoints | body     | integer | yes      | none        |
| » stock          | body     | integer | yes      | none        |
| » isActive       | body     | boolean | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Reward created successfully",
  "data": {
    "id": 6,
    "title": "HAdiah 5",
    "requiredPoints": 50,
    "stock": 1,
    "isActive": true,
    "bookTitle": "LOGIKA MISTIKA"
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name              | Type    | Required | Restrictions | Title | description |
|-------------------|---------|----------|--------------|-------|-------------|
| » status          | boolean | true     | none         |       | none        |
| » message         | string  | true     | none         |       | none        |
| » data            | object  | true     | none         |       | none        |
| »» id             | integer | true     | none         |       | none        |
| »» title          | string  | true     | none         |       | none        |
| »» requiredPoints | integer | true     | none         |       | none        |
| »» stock          | integer | true     | none         |       | none        |
| »» isActive       | boolean | true     | none         |       | none        |
| »» bookTitle      | string  | true     | none         |       | none        |
| » error           | null    | true     | none         |       | none        |

## GET Gett All Reward

GET /rewards

Melihat List Reward

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Reward list fetched successfully",
  "data": [
    {
      "id": 3,
      "title": "HAdiah 3",
      "requiredPoints": 30,
      "stock": 1,
      "isActive": true,
      "bookTitle": "LORD OF THE RINGS"
    },
    {
      "id": 4,
      "title": "HAdiah 4",
      "requiredPoints": 40,
      "stock": 1,
      "isActive": true,
      "bookTitle": "SHAPIENS"
    },
    {
      "id": 5,
      "title": "HAdiah 5",
      "requiredPoints": 50,
      "stock": 1,
      "isActive": true,
      "bookTitle": "LOGIKA MISTIKA"
    },
    {
      "id": 1,
      "title": "HAdiah 1",
      "requiredPoints": 10,
      "stock": 0,
      "isActive": true,
      "bookTitle": "Jacker"
    },
    {
      "id": 6,
      "title": "HAdiah 5",
      "requiredPoints": 50,
      "stock": 1,
      "isActive": true,
      "bookTitle": "LOGIKA MISTIKA"
    },
    {
      "id": 7,
      "title": "HAdiah 5",
      "requiredPoints": 50,
      "stock": 1,
      "isActive": true,
      "bookTitle": "LOGIKA MISTIKA"
    }
  ],
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

## PUT Update Reward

PUT /rewards/{id}

> Body Parameters

```json
{
  "title": "string",
  "bookId": 0,
  "requiredPoints": 0,
  "stock": 0,
  "isActive": true
}
```

### Params

| Name             | Location | Type    | Required | Description |
|------------------|----------|---------|----------|-------------|
| id               | path     | string  | yes      | none        |
| body             | body     | object  | no       | none        |
| » title          | body     | string  | yes      | none        |
| » bookId         | body     | integer | yes      | none        |
| » requiredPoints | body     | integer | yes      | none        |
| » stock          | body     | integer | yes      | none        |
| » isActive       | body     | boolean | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Reward updated successfully",
  "data": {
    "id": 1,
    "title": "Hadiah 1",
    "requiredPoints": 10,
    "stock": 1,
    "isActive": false,
    "bookTitle": "Jacker"
  },
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

## DELETE Delete Reward

DELETE /rewards/{id}

### Params

| Name | Location | Type   | Required | Description |
|------|----------|--------|----------|-------------|
| id   | path     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Reward deleted (soft) successfully",
  "data": "Reward ID 7 has been marked as deleted",
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

## POST Reedem Reward

POST /rewards/redeem

> Body Parameters

```json
{
  "email": "string",
  "rewardId": 0
}
```

### Params

| Name       | Location | Type    | Required | Description |
|------------|----------|---------|----------|-------------|
| body       | body     | object  | no       | none        |
| » email    | body     | string  | yes      | none        |
| » rewardId | body     | integer | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Reward redeemed successfully",
  "data": "You have successfully redeemed reward with ID: 1",
  "error": null
}
```

> 400 Response

```json
{
  "status": true,
  "message": "string",
  "data": null,
  "error": "string"
}
```

### Responses

| HTTP Status Code | Meaning                                                          | Description | Data schema |
|------------------|------------------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | none        | Inline      |
| 400              | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | string  | true     | none         |       | none        |
| » error   | null    | true     | none         |       | none        |

HTTP Status Code **400**

| Name      | Type    | Required | Restrictions | Title | description |
|-----------|---------|----------|--------------|-------|-------------|
| » status  | boolean | true     | none         |       | none        |
| » message | string  | true     | none         |       | none        |
| » data    | null    | true     | none         |       | none        |
| » error   | string  | true     | none         |       | none        |

## POST Redem Hystory History

POST /rewards/history

> Body Parameters

```json
{
  "email": "string"
}
```

### Params

| Name    | Location | Type   | Required | Description |
|---------|----------|--------|----------|-------------|
| body    | body     | object | no       | none        |
| » email | body     | string | yes      | none        |

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Redeem history retrieved",
  "data": [
    {
      "rewardTitle": "Hadiah 1",
      "pointsUsed": 10,
      "redeemedAt": "2025-04-22T16:16:29.662973"
    },
    {
      "rewardTitle": "Hadiah 1",
      "pointsUsed": 10,
      "redeemedAt": "2025-04-22T22:35:36.155238"
    }
  ],
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type     | Required | Restrictions | Title | description |
|----------------|----------|----------|--------------|-------|-------------|
| » status       | boolean  | true     | none         |       | none        |
| » message      | string   | true     | none         |       | none        |
| » data         | [object] | true     | none         |       | none        |
| »» rewardTitle | string   | true     | none         |       | none        |
| »» pointsUsed  | integer  | true     | none         |       | none        |
| »» redeemedAt  | string   | true     | none         |       | none        |
| » error        | null     | true     | none         |       | none        |

## GET User Redem Hystory

GET /rewards/redeem-history

> Response Examples

> 200 Response

```json
{
  "status": true,
  "message": "Redeem history retrieved",
  "data": [
    {
      "rewardTitle": "Hadiah 1",
      "pointsUsed": 10,
      "redeemedAt": "2025-04-22T16:16:29.662973"
    },
    {
      "rewardTitle": "Hadiah 1",
      "pointsUsed": 10,
      "redeemedAt": "2025-04-22T22:35:36.155238"
    }
  ],
  "error": null
}
```

### Responses

| HTTP Status Code | Meaning                                                 | Description | Data schema |
|------------------|---------------------------------------------------------|-------------|-------------|
| 200              | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1) | none        | Inline      |

### Responses Data Schema

HTTP Status Code **200**

| Name           | Type     | Required | Restrictions | Title | description |
|----------------|----------|----------|--------------|-------|-------------|
| » status       | boolean  | true     | none         |       | none        |
| » message      | string   | true     | none         |       | none        |
| » data         | [object] | true     | none         |       | none        |
| »» rewardTitle | string   | true     | none         |       | none        |
| »» pointsUsed  | integer  | true     | none         |       | none        |
| »» redeemedAt  | string   | true     | none         |       | none        |
| » error        | null     | true     | none         |       | none        |

# Data Schema
