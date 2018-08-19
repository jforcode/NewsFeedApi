# News Feed - Front End

News Feed displays news feed from a set of sources. This is a practice project to build something cool and learn a lot of stuff on the journey. Although looking elementary, the news feed project offers a lot of logical and design challenges, and is really a great project for devs of all levels to learn a lot.

This is the REST based API server.

## Tech Stack

- Java 8
- Maven 3.3.9 for build.
- Spring Boot for faster development.
- REST based API using Spring Boot.
- MySQL 5.7 for persistence.

## Installation

```
Setup db, and change credentials in src/main/resources/hikari.properties

mvn clean install

Run src/main/java/com/jeevan/FeedReader.java to setup the required tables.

Run src/main/java/com/jeevan/Application.java to start the API server.

Go to localhost:8080 to see a "Hello There!" message indicating that the server is running.

```

## Current Features

- Displays paginated news feed.
- Search based on title or publisher.
- Sort by publisher or published on.
- Filter by category or publisher.

## Further plans

- 100% test coverage.
- An overall personalized app with authentication, bookmarks, favorites, notifications etc.
- A proper backend microservices based architecture. Authentication, Integrations with other news apps, Data refresh service for 404 and images etc.
- Solidifying the application. Better usage of Spring and it's patterns.