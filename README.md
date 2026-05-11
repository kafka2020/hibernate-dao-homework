# Домашнее задание — ORM, Hibernate

Домашнее задание к занятию **«ORM, Hibernate»** курса Java-разработчик.

---

## Задача 1 — Слой DAO с Hibernate (ветка `main`)

### Описание

Spring Boot REST-приложение для работы с таблицей `PERSONS` через Hibernate и `EntityManager`.

### Что делает программа

- Сущность `Person` с **составным первичным ключом** (`name`, `surname`, `age`) — реализована через `@IdClass`
- Репозиторий `PersonRepository` с методом `getPersonsByCity(String city)` — поиск по городу через JPQL-запрос
- REST-контроллер обрабатывает `GET /persons/by-city?city=Москва` и возвращает список жителей в формате JSON
- При старте приложения автоматически заполняется БД тестовыми данными (4 пользователя из разных городов)
- Используется H2 in-memory база данных; H2 Console доступна по адресу `http://localhost:8080/h2-console`

### Структура проекта

```
src/main/java/com/example/hibernatedao/
├── HibernateDaoApplication.java   # точка входа + инициализация данных
├── entity/
│   ├── Person.java                # Entity-класс (таблица PERSONS)
│   └── PersonId.java              # класс составного первичного ключа
├── repository/
│   └── PersonRepository.java      # DAO-слой через EntityManager
└── controller/
    └── PersonController.java      # REST-контроллер
src/main/resources/
└── application.properties         # настройки Spring Boot + H2
```

### Запуск

```bash
mvn spring-boot:run
```

### Проверка

```
GET http://localhost:8080/persons/by-city?city=Moscow
```

Пример ответа:

```json
[
  { "name": "Ivan", "surname": "Ivanov", "age": 25, "phoneNumber": "+79001234567", "cityOfLiving": "Moscow" },
  { "name": "Petr", "surname": "Petrov", "age": 30, "phoneNumber": "+79007654321", "cityOfLiving": "Moscow" }
]
```

---

## Задача 2 — Две таблицы с Hibernate ★ (ветка `hibernate`)

### Описание

Расширение задачи 1: добавляется сущность `Product`, связанная с `Person` отношением **ManyToOne**. Приложение переписано с JDBC на Hibernate.

### Что делает программа

- Две Entity: `Person` и `Product` — связаны через `@ManyToOne` (у одного человека много продуктов)
- Внешний ключ `person_name` в таблице `PRODUCTS` ссылается на поле `name` таблицы `PERSONS`
- Репозиторий `ProductRepository` использует JPQL с навигацией по связям: `p.person.name`
- REST-контроллер обрабатывает `GET /products/fetch-product?name=Ivan` и возвращает название продукта

### Проверка

```
GET http://localhost:8080/products/fetch-product?name=Ivan
```

---

## Задача 3 — Миграции с Hibernate ★ (ветка `migration-hibernate`)

### Описание

Задача 1 + поддержка **Flyway** для управления версиями схемы базы данных.

### Что делает программа

- Схема БД создаётся Flyway из файла `db/migration/V1__init.sql` (а не Hibernate)
- Hibernate переводится в режим `validate` — только проверяет соответствие Entity и таблиц, не изменяет схему
- При добавлении нового файла `V2__...sql` Flyway автоматически применит миграцию при следующем запуске
- Flyway ведёт таблицу `flyway_schema_history` с историей всех выполненных миграций

### Ключевые изменения по сравнению с задачей 1

| Параметр | Задача 1 | Задача 3 |
|---|---|---|
| `ddl-auto` | `create-drop` | `validate` |
| Создание схемы | Hibernate | Flyway (`V1__init.sql`) |
| Версионирование | нет | есть |

---

## Технологии

- Java 17
- Spring Boot 3.2
- Spring Data JPA / Hibernate
- H2 (in-memory)
- Flyway (задача 3)
- Maven
