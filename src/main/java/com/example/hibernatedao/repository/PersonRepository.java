package com.example.hibernatedao.repository;

import com.example.hibernatedao.entity.Person;
import com.example.hibernatedao.entity.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId> {

    // Найти всех людей по городу
    List<Person> findByCityOfLiving(String city);

    // Найти всех людей младше указанного возраста, отсортированных по возрасту по возрастанию
    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    // Найти человека по имени и фамилии (Optional)
    Optional<Person> findByNameAndSurname(String name, String surname);
}
