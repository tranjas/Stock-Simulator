Stock Simulator Backend - README
Welcome to the Stock Simulator Backend! This Spring Boot application provides APIs for managing users, portfolios, stocks, and transactions, creating a foundation for a stock simulation platform. The backend supports operations like user management, stock purchases/sales, and portfolio updates.

Project Structure
The backend is built using Spring Boot and follows a layered architecture:

Controllers: Expose REST APIs.
Services: Handle business logic.
Repositories: Interface with the database.
DTOs: Data Transfer Objects for transferring structured data between layers.
API Endpoints
1. User Management
APIs to create, update, and delete users.

Create User
Endpoint: POST /users
Request Body:
json
Copy code
{
    "firstName": "Jason",
    "lastName": "Tran",
    "email": "jason.tran@example.com",
    "dateOfBirth": "1990-01-01"
}
Response: Returns the created user details.
HTTP Status: 201 Created
Update User
Endpoint: PUT /users/{id}
Request Body:
json
Copy code
{
    "firstName": "Jason",
    "lastName": "Tran",
    "email": "new.email@example.com",
    "dateOfBirth": "1990-01-01"
}
Response: Updated user details.
HTTP Status: 200 OK
Delete User
Endpoint: DELETE /users/{id}
Response: No content if successful or 404 Not Found if the user does not exist.
HTTP Status: 204 No Content
2. Portfolio Management
APIs for creating, retrieving, updating, and deleting portfolios.

Create Portfolio
Endpoint: POST /portfolios/{id}
Path Variable: id - User ID.
Response: Returns the created portfolio.
HTTP Status: 201 Created
Get Portfolio
Endpoint: GET /portfolios/{id}
Path Variable: id - User ID.
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
Path Variable: id - User ID.
Response: No content if successful or 404 Not Found if the portfolio does not exist.
HTTP Status: 204 No Content
3. Stock Management
APIs for adding, updating, and deleting stocks within a portfolio.

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
Response: No content if successful or 404 Not Found if the stock does not exist.
HTTP Status: 204 No Content
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
How to Run the Application
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
Access the APIs The application runs on http://localhost:8080 by default. Use tools like Postman or cURL to interact with the APIs.

Future Enhancements
Implement authentication and authorization.
Add more detailed stock analytics.
Enhance error handling with custom exception handling.
