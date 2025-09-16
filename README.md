# FlavorFleet: A Microservices-Based Food Delivery Platform

**FlavorFleet** is an end-to-end food delivery application built to demonstrate a modern microservices architecture using Java, Spring Boot, Angular, and cloud deployments on Azure and GCP. Users can register, browse restaurant menus, place orders, track deliveries, and receive real-time notifications.

## Project Purpose
This project showcases skills in microservices, event-driven architecture (Kafka), JWT security, containerization (Docker), and cloud deployment (Azure/GCP) for a resume-worthy portfolio.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.x (Web, Data JPA, Security, Cloud OpenFeign, Kafka, Cache)
- **Frontend**: Angular 18 (HttpClient, Bootstrap)
- **Database**: MySQL 8.0
- **Messaging**: Apache Kafka
- **Caching**: Redis/Ehcache
- **Security**: JWT
- **Containerization**: Docker & Docker Compose
- **Deployment**: Azure Container Apps, GCP Cloud Run
- **Tools**: IntelliJ, VS Code, Postman, GitHub

## Setup Instructions
### Prerequisites
- Docker Desktop
- Git
- Java 17, Node.js 18.x, Angular CLI
- MySQL Workbench (optional)
- Postman (for testing)

### Local Setup
1. Clone the repo: `git clone [https://github.com/AryanShriva/flavorfleet-food-delivery-app.git](https://github.com/AryanShriva/flavorfleet-food-delivery-app.git)`
2. Navigate to `flavorfleet-food-delivery-app/`.
3. Run `docker-compose up -d` to start MySQL, Kafka, Redis, and Zookeeper.
4. Verify MySQL schema: `docker exec -it flavorfleet-mysql mysql -uroot -proot`, then `USE food_delivery_db; SHOW TABLES;`.
5. Verify Kafka: `docker exec -it flavorfleet-kafka kafka-topics --create --topic test --bootstrap-server localhost:9093 --partitions 1 --replication-factor 1`.
6. Verify Redis: `docker exec -it redis redis-cli ping`.

### Cloud Setup
- **GCP**:
  - Signed up for GCP free account ($300 credit).
  - Created project: `flavorfleet-project`.
  - Free tier services: Cloud Run, Cloud SQL.

## Progress
See `progress.md` for daily updates and milestones.
