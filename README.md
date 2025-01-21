# Java Spring application with SQLite

## Getting Started

If you are configuring Hibernate to use SQLite in your `application.properties` file for a Spring Boot project, you should specify the correct Hibernate dialect. For SQLite, you can use `org.hibernate.community.dialect.SQLiteDialect` (Hibernate 6 or later).
```properties
# SQLite database configuration
spring.datasource.url=${DB_URL}   # Example: jdbc:sqlite:database.db
spring.datasource.driver-class-name=org.sqlite.JDBC

# Hibernate dialect for SQLite
spring.jpa.properties.hibernate.dialect=org.hibernate.community.dialect.SQLiteDialect
```
**Note**: Hibernate expects `BIGINT` for the `id` field in `Note` entity, which corresponds to Java's `Long`. SQLite does not have a native `BIGINT`, but in SQLite the `INTEGER` type can store up to 8-byte signed integers, which is sufficient for Java's `Long`. However, Hibernate is expecting a column type explicitly mapped to `BIGINT`, and SQLite's `INTEGER` is not recognized as such during schema validation. To solve this we can explicitly specify the SQL column type in the `@Column` annotation:
```Java
@Column(name = "id", columnDefinition = "INTEGER")
private Long id;
```

### Prerequisites

- **Java 21**: Ensure Java 21 is installed on your system.
- **Gradle**: This project uses Gradle for dependency management and build tasks.

### Installation

1. Clone the repository:
```shell
git clone git@github.com:ruslanaprus/spring-app-sqlite.git
cd spring-app-sqlite
```
2. Database Configuration: Copy the `.env.example` file into `.env`, and populate it with your DB details (key: [DB_URL]). This file will be used to set DB properties for your application.

3. Build the project:
```shell
./gradlew clean build
```
4. Run the application:
```shell
./gradlew bootRun
```