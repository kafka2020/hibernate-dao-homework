package com.example.hibernatedao.repository;

import com.example.hibernatedao.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }

    public String getProductName(String personName) {
        return entityManager
                .createQuery(
                        "SELECT p.productName FROM Product p WHERE p.person.name = :name",
                        String.class)
                .setParameter("name", personName)
                .getSingleResult();
    }
}
