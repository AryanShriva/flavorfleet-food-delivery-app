# Progress Log
## Day 1: September 14, 2025
- Initialized GitHub repo `flavorfleet`.
- Created folder structure with placeholders (.gitkeep) for backend microservices and frontend.
- Added detailed README to clarify FlavorFleet as a food delivery platform.
- Set up Kafka via Docker.

## Day 2: September 14, 2025
- Configured MySQL (port 3307) in docker-compose.yml to avoid conflicts.
- Set up Kafka in KRaft mode (confluentinc/cp-kafka:7.4.0, port 9092) with valid CLUSTER_ID.
- Added Redis (port 6379) for caching.
- Created `food_delivery_db` schema with tables for users, restaurants, menu_items, orders, and order_items in Docker MySQL instance.
- Ran Docker Compose and verified MySQL, Kafka, and Redis services.
- Tested database connection via CLI and confirmed schema.
- Removed Zookeeper to simplify setup (using KRaft mode).

## Day 3: September 15, 2025
- Restarted Docker services after system shutdown.
- Verified MySQL schema in `food_delivery_db`.
- Confirmed Kafka, Redis, and Zookeeper functionality.
- Signed up for Azure and GCP accounts with free credits.
- Created Azure resource group `FlavorFleet` and GCP project `flavorfleet-project`.
- Updated README with cloud setup details.
- Pushed changes to GitHub.

## Day 4: September 16, 2025
- Created `user-service` using Spring Initializr (Java 17, Spring Boot 3.x, Web, JPA, Security, MySQL, JJWT).
- Configured `application.yml` for MySQL connection (port 3307, food_delivery_db).
- Created `User` entity and `UserRepository` for the `users` table.
- Dockerized `user-service` and added to `docker-compose.yml` (port 8081).
- Tested service locally with Postman to confirm MySQL connection.
- Pushed changes to GitHub.

## Day 5: September 18, 2025
- Implemented registration and login APIs in `user-service`.
- Added JWT generation and security filters with Spring Security.
- Configured password encryption with BCrypt.
- Updated Docker image for `user-service` and tested with Postman.
- Verified registration, login, and JWT token generation.
- Pushed changes to GitHub.