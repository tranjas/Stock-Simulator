# Stock Simulator Backend - README
Welcome to the Stock Simulator Backend! This Spring Boot application provides APIs to manage users, portfolios, stocks, and transactions. It forms the backbone of a stock simulation platform, allowing operations such as user management, stock trading, and portfolio updates.

## 🏗️ Project Structure
The backend is structured using a layered architecture to ensure separation of concerns:

Controllers: Expose REST APIs for interaction.

Services: Handle business logic and processing.

Repositories: Interface with the database for CRUD operations.

DTOs: Data Transfer Objects for managing structured data flow between layers.

## 📖 API Documentation
1. User Management
APIs to create, update, and delete user profiles.

Create User
Endpoint: POST /users
Request Body:
```
{
    "firstName": "Jason",
    "lastName": "Tran",
    "email": "jason.tran@example.com",
    "dateOfBirth": "1990-01-01"
}
```
Response: Returns the created user details.
HTTP Status: 201 Created
Update User
Endpoint: PUT /users/{id}
Request Body:
json
```
{
    "firstName": "Jason",
    "lastName": "Tran",
    "email": "new.email@example.com",
    "dateOfBirth": "1990-01-01"
}
```
Response: Updated user details.
HTTP Status: 200 OK
Delete User
Endpoint: DELETE /users/{id}
Response:
204 No Content if successful.
404 Not Found if the user does not exist.
2. Portfolio Management
APIs to create, retrieve, update, and delete portfolios.

Create Portfolio
Endpoint: POST /portfolios/{id}
Path Variable:
id: User ID.
Response: Returns the created portfolio.
HTTP Status: 201 Created
Get Portfolio
Endpoint: GET /portfolios/{id}
Path Variable:
id: User ID.
Response: Portfolio details.
HTTP Status: 200 OK
Update Buying Power
Endpoint: PUT /portfolios/{id}/{amount}
Path Variables:
id: User ID.
amount: Amount to update the buying power.
Response: No content.
HTTP Status: 204 No Content
Delete Portfolio
Endpoint: DELETE /portfolios/{id}
Path Variable:
id: User ID.
Response:
204 No Content if successful.
404 Not Found if the portfolio does not exist.
3. Stock Management
APIs to add, update, and delete stocks within a portfolio.

Add Stock
Endpoint: POST /stocks/{id}/{symbol}/{amount}
Path Variables:
id: Portfolio ID.
symbol: Stock symbol.
amount: Quantity to add.
Response: Details of the added stock.
HTTP Status: 201 Created
Update Stock
Endpoint: PUT /stocks/{id}/{symbol}/{amount}
Path Variables:
id: Portfolio ID.
symbol: Stock symbol.
amount: Updated quantity.
Response: Updated stock details.
HTTP Status: 200 OK
Delete Stock
Endpoint: DELETE /stocks/{id}/{symbol}
Path Variables:
id: Portfolio ID.
symbol: Stock symbol.
Response:
204 No Content if successful.
404 Not Found if the stock does not exist.
4. Transaction Management
APIs to log stock transactions (buy/sell).

Create Transaction
Endpoint: POST /transaction/{id}/{symbol}/{action}/{amount}/{total_price}
Path Variables:
id: User ID.
symbol: Stock symbol.
action: Transaction type (Buy or Sell).
amount: Quantity of stock.
total_price: Total price of the transaction.
Response: Details of the created transaction.
HTTP Status: 201 Created
🚀 How to Run the Application
Clone the Repository

bash
Copy code
git clone https://github.com/Tranjas1/stock-simulator-backend.git  
cd stock-simulator-backend
Build the Project

bash
Copy code
./mvnw clean install
Run the Application

bash
Copy code
./mvnw spring-boot:run
Access the APIs
The application runs on http://localhost:8080 by default. Use tools like Postman or cURL to interact with the APIs.

🌟 Future Enhancements
Implement authentication and authorization.
Add detailed stock analytics.
Enhance error handling with custom exception handling.