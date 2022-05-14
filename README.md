# Horizon

A software made to manage issues in horizontal properties.

## Tech Stack

**Client:** Vue, Pinia, Bootstrap, vue-router, axios

**Server:** Java, Spring, Spring JPA, Spring Web.


## Deployment

We use Docker and Docker-Compose for deployment.  
This facilitates the installation and configuration of the system.

### Build the images
```bash
docker-compose build
```

### Start the server
```bash
docker-compose up
```


## Environment Variables

To run this project, you will need to set the following environment variables in the `docker-compose.yaml` to match your needs.

- `POSTGRES_DB`: Database name.
- `POSTGRES_USER`: PostgreSQL username.
- `POSTGRES_PASSWORD`: PostgreSQL POSTGRES_PASSWORD.

These environment variables must be consistent with the following variables of Spring.

- `SPRING_DATASOURCE_URL`: The JDBC URI pointing to the runtime of the database including its database. Ex: `jdbc:postgresql://db:[DB_PORT]/[DB_NAME]`
- `SPRING_DATASOURCE_USERNAME`: PostgreSQL username.
- `SPRING_DATASOURCE_PASSWORD`: PostgreSQL password.
