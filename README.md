# PeopleAI Platform

A full-stack talent/recruiting platform inspired by the public Kodiva site, using original PeopleAI branding and sample content.

## Stack
- React 18 + Vite
- Java 17, Spring Boot 3.3, Maven multi-module microservices
- MySQL 8 for jobs, candidates, and contact leads
- Kafka (KRaft) for lead-submitted events
- Docker Compose and Jenkins declarative pipeline

## Run with Docker
Requires Docker Engine and Compose plugin. From repository root:

```bash
docker compose up --build
```

Open http://localhost:3000. Gateway: http://localhost:8080. Stop with `docker compose down`; add `-v` to remove local database data. The credentials in Compose are local-development examples only; replace them for any shared or deployed environment.

## Build/run without Docker
Start MySQL and Kafka locally, create database `peopleai`, then build:

```bash
cd backend
mvn clean verify
```
Run each service in separate terminals:
```bash
mvn -pl peopleai-api-gateway spring-boot:run
mvn -pl peopleai-auth-service spring-boot:run
mvn -pl peopleai-jobs-service spring-boot:run
mvn -pl peopleai-candidates-service spring-boot:run
mvn -pl peopleai-contact-service spring-boot:run
```
Ports: gateway 8080, auth 8081, jobs 8082, candidates 8083, contact 8084. Start frontend locally with `cd frontend && npm install && npm run dev`; Vite uses the same-origin proxy for `/api` requests.

## API
Gateway routes: `GET /api/health`, `GET /api/dashboard`, `POST /api/auth/login`, CRUD `/api/jobs`, CRUD `/api/candidates`, and `POST /api/contact/lead`. Lead submissions are saved in MySQL and emitted to `peopleai.leads.submitted`.

## Jenkins
The Jenkinsfile verifies Maven modules, builds the frontend, validates Compose, and builds containers on `main`. Configure a Jenkins agent with Java 17, Maven, Node/npm, Docker and Compose. Deployment credentials and deployment stages must be configured for your environment; none are hard-coded.

## Scope
This is an extensible demo starter, not a production-ready recruiting product. Login is a demo response; it does not provide secure authentication. Metrics are illustrative; there is no real candidate sourcing, AI scoring, email delivery, or production access control. Add authentication, authorization, privacy/security controls, migrations, monitoring, and real integrations before using real personal data. This project is not affiliated with Kodiva.
