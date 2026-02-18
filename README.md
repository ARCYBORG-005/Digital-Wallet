# Digital-Wallet
java backend


# Digital Wallet Application (Java + MySQL)

##  Project Overview

This is a **Java-based Digital Wallet Application** that allows users to:

* Create and manage user accounts
* Add money to wallet
* Track expenses
* Validate and process transactions
* View transaction history

The application follows a layered architecture using:

* DAO Layer
* Service Layer
* Model Layer
* Database Layer

It connects to **MySQL** using the MySQL Connector JAR.



## Technologies Used

* Java (Core Java)
* JDBC
* MySQL
* MySQL Connector (JAR)
* Git & GitHub



##  Project Structure

DigitalWallet/
 db/
   ├── DBConnection.java
  └── TestDB.java

├── dao/
  ├── ExpenseDao.java
   ├── UserDao.java
   └── WalletDao.java

├── model/
   ├── Expense.java
  ├── Transaction.java
  └── User.java

├── service/
  ├── ExpenseService.java
  ├── UserService.java
 └── WalletService.java

├── main/
   └── MainApp.java

└── lib/
    └── mysql-connector-j.jar


##  Architecture Explanation

###  Model Layer

Contains entity classes:

* `User`
* `Expense`
* `Transaction`

These represent database tables.



### DAO Layer (Data Access Layer)

Handles database operations using JDBC:

* Insert user
* Add expense
* Update wallet balance
* Validate transactions

---

###  Service Layer

Contains business logic:

* User validation
* Balance checking
* Transaction processing
* Expense calculation



###  DB Layer

* `DBConnection.java` establishes connection with MySQL.
* Uses MySQL Connector JAR.
* `TestDB.java` verifies database connectivity.



##  Features

 User Registration
 Add Money to Wallet
 Record Expenses
 View Transaction History
 Validate Transactions
 Check Wallet Balance



##  Key Concepts Demonstrated

* Object-Oriented Programming (OOP)
* Layered Architecture
* JDBC Connectivity
* Exception Handling
* Separation of Concerns
* Database Integration
* CRUD Operations



##  Database Used

* MySQL Database
* Tables:

  * users
  * expenses
  * transactions
  * wallet


##  How to Run the Project

1. Install MySQL and create required database & tables.
2. Update database credentials in `DBConnection.java`.
3. Add MySQL Connector JAR to project classpath.
4. Run `MainApp.java`.


 (Short Version)

> This project is a Java-based Digital Wallet system built using layered architecture.
> It demonstrates JDBC connectivity with MySQL, DAO pattern implementation, transaction validation, and expense tracking.
> The application ensures separation between business logic and database access using service and DAO layers.

