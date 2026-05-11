# Hibernate DAO Homework

Netology homework 8.4 — ORM, Hibernate

## Task 1 (branch: main)
**DAO layer with Hibernate**

Spring Boot REST application with a `PERSONS` table.
- Entity `Person` with composite primary key (`name`, `surname`, `age`)
- `PersonRepository` using `EntityManager` to query persons by city
- REST endpoint: `GET /persons/by-city?city=Moscow`

### Run
```bash
mvn spring-boot:run
```

### Test
```
http://localhost:8080/persons/by-city?city=Moscow
```

## Task 2 (branch: hibernate)
**Two tables with Hibernate**

Two related entities `Person` and `Product` with `ManyToOne` relation.
- REST endpoint: `GET /products/fetch-product?name=Ivan`

## Task 3 (branch: migration-hibernate)
**Migrations with Hibernate**

Task 1 + Flyway migrations for schema management.
- Schema created by Flyway from `db/migration/V1__init.sql`
- Hibernate set to `validate` mode
