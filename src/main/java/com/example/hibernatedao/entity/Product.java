package com.example.hibernatedao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name", referencedColumnName = "name")
    private Person person;

    public Product() {}

    public Product(String productName, Person person) {
        this.productName = productName;
        this.person = person;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}
