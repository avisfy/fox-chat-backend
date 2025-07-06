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
### To start infrastructure for project, run 
``` bash
docker-compose -p fox-chat-infra -f docker-compose.infra.yml -f docker-compose.infra.dev.yml up -d
```
This will start:
- PostgreSQL for user and chat services
- Kafka broker (with KRaft mode)
- Kafka UI for topic monitoring

### To start backend application services, run
``` bash
docker-compose -p fox-chat-backend -f docker-compose.yml up -d
```