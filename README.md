# WiraDayCare

A Spring Boot daycare management application with Keycloak authentication, PostgreSQL database, and Docker containerization.

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Git** - for cloning the repository
- **Java 17 or later** - for building the Spring Boot application
- **Maven 3.6+** - for dependency management and building
- **Docker & Docker Compose** - for containerized deployment

### Installing Prerequisites

#### Windows:
```powershell
# Install Java (using Chocolatey)
choco install openjdk17

# Install Maven (using Chocolatey)
choco install maven

# Install Docker Desktop from https://docker.com/products/docker-desktop
```

#### macOS:
```bash
# Install Java (using Homebrew)
brew install openjdk@17

# Install Maven (using Homebrew)
brew install maven

# Install Docker Desktop from https://docker.com/products/docker-desktop
```

#### Linux (Ubuntu/Debian):
```bash
# Install Java
sudo apt update
sudo apt install openjdk-17-jdk

# Install Maven
sudo apt install maven

# Install Docker and Docker Compose
sudo apt install docker.io docker-compose
sudo usermod -aG docker $USER
```

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Wirabumi-Software/wiradaycare.git
cd wiradaycare
```

### 2. Build the Application

```bash
# Clean, compile, and install the application (skip tests for faster build)
mvn clean compile install -DskipTests
```

### 3. Start the Services

```bash
# Build and start all services using Docker Compose
docker-compose up --build
```

**Note**: If you encounter issues with the `pgadmin` service due to organizational restrictions, you can comment out the pgAdmin section in `docker-compose.yml`.

### 4. Verify the Setup

Once all services are running, you should see output indicating:
- PostgreSQL database is ready to accept connections
- Keycloak is importing realm configuration
- WiraDayCare application is starting with Spring Boot

The setup is complete when you see the Spring Boot banner and "Started WiraDayCareApplication" in the logs.

### 5. Access the Application

- **WiraDayCare API**: http://localhost:8081
- **Swagger API Documentation**: http://localhost:8081/swagger-ui/index.html
- **Keycloak Admin Console**: http://localhost:8080 (admin/admin)
- **Health Check**: http://localhost:8081/actuator/health

## API Documentation

### Swagger UI
Access the interactive API documentation at:
```
http://localhost:8081/swagger-ui/index.html
```

The Swagger UI provides:
- Complete API endpoint documentation
- Interactive testing interface
- Request/response examples
- JWT authentication support

### Authentication in Swagger
1. First, obtain a JWT token by calling the `/auth/login` endpoint
2. Click the "Authorize" button in Swagger UI
3. Enter your JWT token in the format: `Bearer <your-jwt-token>`
4. Now you can test authenticated endpoints

## Pre-configured Users

The application comes with pre-configured test users:

| Username | Password | Role      |
|----------|----------|-----------|
| alice    | alice123 | ADMIN     |
| bob      | bob123   | CAREGIVER |
| carol    | carol123 | PARENT    |

## Managing the Application

### Starting Services
```bash
# First time setup - build and start all services
docker-compose up --build

# Start existing services (after first setup)
docker-compose start

# Start services in the background (detached mode)
docker-compose up -d
```

### Stopping Services
```bash
# Stop all services (keeps containers and data)
docker-compose stop

# Stop services started in detached mode
docker-compose stop
```

### Recompiling and Redeploying (Development Workflow)

When you make code changes and need to redeploy only the application:

```bash
# 1. Stop only the application container (keep DB and Keycloak running)
docker stop wiradaycare-app

# 2. Rebuild the Maven project
mvn clean compile install -DskipTests

# 3. Rebuild and start the application container
docker-compose up --build app
```

**Alternative: Full restart with all services**
```bash
# Stop all services
docker-compose stop

# Rebuild and restart all services
docker-compose up --build
```

**Quick redeploy for app-only changes:**
```bash
# One-liner for fast development cycle
docker stop wiradaycare-app && mvn clean compile install -DskipTests && docker-compose up --build app
```

### Complete Removal (Use with caution)
```bash
# Stop and remove all containers (but keep volumes/data)
docker-compose down

# Stop and remove containers AND volumes (WARNING: This will delete all data)
docker-compose down -v
```

## Troubleshooting

### Common Issues:

1. **Port conflicts**: If ports 8080, 8081, or 5432 are in use, modify the port mappings in `docker-compose.yml`

2. **Docker build fails**: Ensure you've run `mvn clean compile install -DskipTests` before `docker-compose up --build`

3. **Database connection issues**: Wait for PostgreSQL to fully start before the application attempts to connect

4. **Keycloak startup issues**: Ensure port 8080 is available and wait for Keycloak to fully initialize

## Accessing Actuator Health Endpoint

To check the application's health status, open your browser or use a tool like curl to access:

```
http://localhost:8081/actuator/health
```

This endpoint returns a JSON response with the application's health information.

## Accessing HTML UI

Open your browser and go to:

```
http://localhost:8081/
```

This will load the application's HTML user interface.

## Accessing Keycloak

1. Open your browser and go to:
   http://localhost:8080

2. Log in with the admin credentials:
   - Username: admin
   - Password: admin

This will take you to the Keycloak admin console, where you can manage realms, users, and clients.

If you need to access Keycloak from another machine, replace `localhost` with your host's IP address.

## Accessing PostgreSQL Database

### Using pgAdmin (web UI)

**Note**: If pgAdmin is blocked by your organization's Docker registry policies, you can comment out the pgAdmin service in `docker-compose.yml`.

If pgAdmin is available:
1. Open your browser and go to:
   http://localhost:5050
2. Log in with:
   - Email: hi@wirabumisoftware.com
   - Password: secret
3. Add a new server in pgAdmin:
   - Name: wiradaycare-db (or any name)
   - Host: db (or localhost if connecting externally)
   - Port: 5432
   - Username: wiradaycare
   - Password: secret
   - Database: wiradaycare

### Using a PostgreSQL client
- Host: localhost (if connecting from your machine)
- Port: 5432
- Username: wiradaycare
- Password: secret
- Database: wiradaycare
