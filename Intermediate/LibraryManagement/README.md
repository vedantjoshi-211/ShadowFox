# Java Library Management System (SQLite Edition)

This project is a **console-based** Library Management System written in Java that demonstrates:

* SQLite persistence using JDBC
* Simple user account authentication
* Book CRUD operations
* Borrow/return workflow
* Random book recommendations
* Optional integration with the OpenLibrary API to fetch book descriptions by ISBN

## Prerequisites

* **Java 17** or later
* **Gradle 8** (or use the Gradle wrapper by running `./gradlew` on Unix or `gradlew.bat` on Windows)
* Internet connection (only if you want to fetch descriptions from OpenLibrary)

## Getting Started

```bash
# Clone or download the project, then:
cd LibraryManagementSystem

# Compile and run
gradle run
```

On first launch, the program creates a local **`library.db`** SQLite file in the working directory.

## Key Classes / Packages

| Package               | Responsibility                          |
|-----------------------|------------------------------------------|
| `com.library`         | Entry point (`Main`) and DB bootstrap    |
| `com.library.models`  | Plain Java objects (`Book`, `User`)      |
| `com.library.dao`     | Data‑access layer (JDBC CRUD helpers)    |
| `com.library.service` | Business logic (recommendations, API)    |
| `com.library.ui`      | Text‑based user interface (`CLI`)        |

## Extending

* Swap the `CLI` for a Swing or JavaFX UI.
* Implement a smarter recommendation algorithm (e.g. collaborative filtering).
* Encrypt passwords using BCrypt instead of plain text.
* Add unit tests with JUnit.

## License

MIT — do whatever you like, just keep this header.
