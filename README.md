## Global API Response Structure

All API responses follow a standardized structure to ensure consistency across the application.

| Name    | Type           | Required | Description                                                                                                |
|---------|----------------|----------|------------------------------------------------------------------------------------------------------------|
| status  | boolean        | true     | Indicates whether the API request was successful (`true`) or resulted in an error (`false`).               |
| message | string         | true     | A human-readable message providing additional context about the result.                                    |
| data    | object \| null | true     | The actual response payload. This can be an object, list, or null depending on the endpoint.               |
| error   | object \| null | true     | Contains error details when `status` is `false`. It can be a string message, validation object, or `null`. |

---

### ✅ Example: 200 OK (Success)

```json
{
  "status": true,
  "message": "Book created successfully",
  "data": {
    "id": 8,
    "title": "Jojo Bizarre Adventure",
    "basePrice": 10000,
    "type": "OLD_EDITION",
    "createdAt": "2025-04-22T21:59:44.346Z",
    "updatedAt": null
  },
  "error": null
}
```

### ❌ Example: 401 Unauthorized

```json
{
  "status": false,
  "message": "Unauthorized access",
  "data": null,
  "error": "Missing or invalid authentication token"
}
```

### Example: 500 Internal Server Error

```json
{
  "status": false,
  "message": "An unexpected error occurred",
  "data": null,
  "error": "NullPointerException at PurchaseService: line 38"
}

```

## 📌 Role Access Matrix

### Admin Access

| **Endpoint**           | **Method** | **Description**                                |
|------------------------|------------|------------------------------------------------|
| `/auth/register`       | POST       | Register a regular user                        |
| `/auth/login`          | POST       | Login and obtain a token                       |
| `/auth/register-admin` | POST       | Register an admin account                      |
| `/users/me`            | GET        | View own data                                  |
| `/books`               | GET        | View all books                                 |
| `/books/{id}`          | GET        | View specific book details                     |
| `/books`               | POST       | Add a new book                                 |
| `/books/{id}`          | PUT        | Update book data                               |
| `/books/{id}`          | DELETE     | Soft delete a book                             |
| `/purchases`           | POST       | Record purchases (by cashier/admin)            |
| `/purchase`            | GET        | View purchase history                          |
| `/loyalty/points-user` | GET        | View own loyalty points as admin               |
| `/loyalty/points`      | POST       | View loyalty points of another user (by email) |
| `/rewards`             | GET        | View all rewards                               |
| `/rewards`             | POST       | Add a new reward                               |
| `/rewards/{id}`        | PUT        | Edit reward details                            |
| `/rewards/{id}`        | DELETE     | Soft delete a reward                           |
| `/rewards/redeem`      | POST       | Redeem a reward for a user (by email)          |
| `/rewards/history`     | POST       | View reward redemption history for other users |

---

### Regular User Access (Non-Admin)

| Endpoint                  | Method | Description                                    |
|---------------------------|--------|------------------------------------------------|
| `/auth/register`          | POST   | Register akun pengguna                         |
| `/auth/login`             | POST   | Login and get token                            |
| `/users/me`               | GET    | View your own profile                          |
| `/loyalty/points-user`    | GET    | View your own loyalty points                   |
| `/rewards/redeem-history` | GET    | View the history of rewards exchanged yourself |
