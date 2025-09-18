# WiraDayCare

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
1. Open your browser and go to:
   http://localhost:5050
2. Log in with:
   - Email: hi@wirabumisoftware.com
   - Password: secret
3. Add a new server in pgAdmin:
   - Name: wiradaycare-db (or any name)
   - Host: db
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
