package com.example.hibernatedao.controller;

import com.example.hibernatedao.entity.Person;
import com.example.hibernatedao.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // GET /persons/by-city?city=Moscow
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // GET /persons/by-age?age=30  — вернёт всех младше 30, по возрастанию
    @GetMapping("/by-age")
    public List<Person> getPersonsYoungerThan(@RequestParam int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    // GET /persons/by-name?name=Ivan&surname=Ivanov
    @GetMapping("/by-name")
    public ResponseEntity<Person> getPersonByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        return personRepository.findByNameAndSurname(name, surname)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
