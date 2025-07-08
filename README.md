# FoxChat Backend
This project implements a chat backend using microservices architecture, Java and Spring Boot, with JWT authentication support.  

## Technologies
- Java 21
- Spring Boot
- PostgreSQL
- Kafka (KRaft mode)
- Docker/Docker Compose
- JWT Authentication
- Gradle


## How to Run
### Start project infrastructure
To start the *basic infrastructure*, run:
```bash
docker-compose -p fox-chat-infra -f docker-compose.infra.yml up -d
```
This will start:
- PostgreSQL for user and chat services
- Kafka broker (KRaft mode)

To start the *dev version* with Kafka UI enabled for topic monitoring, run:
```bash
docker-compose -p fox-chat-infra -f docker-compose.infra.yml -f docker-compose.infra.dev.yml up -d 
```
- This will additionally start Kafka UI on http://localhost:8085

### Start backend application services
The backend services require some environment variables to run.
1. Create a `.env` file based on `.env.example` and provide your own values
2. After it, start backend applications, using:
    ```bash
    docker-compose -p fox-chat-backend -f docker-compose.yml up -d --build
    ```