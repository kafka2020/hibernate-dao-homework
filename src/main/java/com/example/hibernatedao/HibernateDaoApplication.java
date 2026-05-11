package com.example.hibernatedao;

import com.example.hibernatedao.entity.Person;
import com.example.hibernatedao.entity.Product;
import com.example.hibernatedao.repository.PersonRepository;
import com.example.hibernatedao.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateDaoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(PersonRepository personRepository, ProductRepository productRepository) {
        return args -> {
            Person ivan = new Person("Ivan", "Ivanov", 25, "+79001234567", "Moscow");
            Person petr = new Person("Petr", "Petrov", 30, "+79007654321", "Moscow");
            Person anna = new Person("Anna", "Sidorova", 22, "+79001112233", "Saint Petersburg");
            personRepository.save(ivan);
            personRepository.save(petr);
            personRepository.save(anna);

            productRepository.save(new Product("Laptop", ivan));
            productRepository.save(new Product("Phone", petr));
            productRepository.save(new Product("Tablet", anna));
        };
    }
}
