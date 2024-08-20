# Library Management System

## Overview

This project is a collaborative effort by three developers to create a Library Management System using Java. The system is designed to manage the operations of a library, including book management, request handling, and user interactions.

### Current Features

- Admin Role: The Admin can add or remove books from the library's collection.
- Librarian Role: The Librarian can approve or reject book reservation requests made by members.
- Member Role: Members can view the list of available books and submit reservation requests.

### Planned Features

- Integration with a database to persist data.
- Enhanced search functionality for books.
- Notification system for approved/rejected requests.
- Additional user roles with more functionalities.

## Project Structure

The project is organized into the following structure within the src directory:

Library-Project/
│
├── .idea/                        # IDE configuration files (IntelliJ IDEA)
├── out/                          # Compiled output files
├── src/                          # Source code directory
│   ├── Book.java                 # Represents a book in the library
│   ├── BookStatus.java           # Enum for the status of a book (e.g., Available, Borrowed)
│   ├── DataBase.java             # Manages the database connection and operations
│   ├── Main.java                 # Entry point of the application
│   ├── Request.java              # Handles requests related to book borrowing
│   ├── RequestStatus.java        # Enum for the status of a request (e.g., Pending, Approved)
│   ├── Role/                     # Directory containing role-related classes
│   │   ├── BookReader.java       # Class for users who can read and borrow books
│   │   ├── Librarian.java        # Class representing a librarian
│   │   ├── Manager.java          # Class representing a manager of the library
│   │   └── User.java             # Base class for all types of users in the system
│
├── Library.iml                   # IntelliJ IDEA project file
├── mysql-connector-j-8.0.31-javadoc.jar   # MySQL connector library for database interaction
└── README.md                     # Project documentation file

- admin/: Contains the Admin class which handles the functionalities related to book management.
- librarian/: Contains the Librarian class which handles the functionalities related to request management.
- member/: Contains the Member class which handles the functionalities related to library members.
- book/: Contains the Book class which represents the book entity in the system.

## Version

- Current Version: 0.1.0

This is the initial version of the project, currently supporting the basic roles and their functionalities. Future versions will include database integration and additional features.
