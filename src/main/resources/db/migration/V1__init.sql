CREATE TABLE IF NOT EXISTS PERSONS (
    name            VARCHAR(100) NOT NULL,
    surname         VARCHAR(100) NOT NULL,
    age             INT          NOT NULL,
    phone_number    VARCHAR(20),
    city_of_living  VARCHAR(100),
    CONSTRAINT pk_persons PRIMARY KEY (name, surname, age)
);
