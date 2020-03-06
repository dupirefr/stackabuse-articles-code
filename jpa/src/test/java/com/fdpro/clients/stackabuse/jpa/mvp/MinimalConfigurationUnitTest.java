package com.fdpro.clients.stackabuse.jpa.mvp;

import com.fdpro.clients.stackabuse.jpa.domain.Student;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

class MinimalConfigurationUnitTest {

    @Test
    void givenMinimalConfiguration_whenFindEntity_thenFound() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student();
        student.setId(1L);

        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Student foundStudent = entityManager.find(Student.class, 1L);

        assertThat(foundStudent.id()).isEqualTo(student.id());

        entityManager.close();
    }
}
