package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class RelationshipMappingUnitTest {

    protected EntityManager entityManager;

    @BeforeEach
    void openEntityManagerAndTransaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-relationships");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
