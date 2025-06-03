# Project 6 – PayMyBuddy

**PayMyBuddy** is the 6th project of the Java Developer path at OpenClassrooms.  
It is a peer-to-peer money transfer application designed to simplify reimbursements among friends, whether for groceries, a restaurant, or a shared activity.

## Project Objectives

The goal was to deliver a functional prototype of the application with the following features:

- Account creation
- User authentication
- Money transfers
- Adding friends
- Editing user profile information

## Developed Features

- CRUD operations are available for `User` and `Transaction` entities.
- Custom queries allow:
  - Retrieving all transactions for a given user
  - Accessing the user's connections using the database relationships

## Security

- Authentication is handled using **Spring Security** with a session-based mechanism.
- A consistent filter chain secures the routes according to authentication and user roles.

## Database

- A **MySQL** database is used to store users, transactions, and their relationships.
- All required SQL scripts are included in the project.

## Frontend

- Built with **ReactJS**:
  - Enables advanced front-end control, such as caching and page routing.
  - Integrated with the Spring Boot backend and packaged into a single `.jar` file.
  - The full stack runs on a single port.

## Testing

- A complete unit test campaign was implemented to ensure backend functionality.

## Tech Stack

- Java
- Spring Boot
- Spring Web Starter
- Spring Data JPA
- Spring Security
- MySQL
- JUnit
- ReactJS

## Visual Representation

Here's a screenshot of Pay My Buddy's physical database model.  
Screenshot taken from the DBeaver database management application.

![Database Schema Screenshot](sqldata/modele_physique_de_données.png)

## Folder Structure

- **back-end/**: Contains all Spring Boot applications required for the backend.
- **resources/static/**: Contains the build version of the frontend so that both run on the same server.
- **front-end/**: Contains the full React application for the user interface.
- **sql data/**: Contains SQL files for generating and populating the database.

## Configuration

To connect to the database with Spring Data:
1. Create an `.env` file.
2. Define the variables as required by the `application.properties` file.
3. Enter your database connection information.