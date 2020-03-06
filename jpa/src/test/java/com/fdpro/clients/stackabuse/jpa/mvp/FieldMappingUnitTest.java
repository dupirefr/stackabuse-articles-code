package com.fdpro.clients.stackabuse.jpa.mvp;

import com.fdpro.clients.stackabuse.jpa.domain.Address;
import com.fdpro.clients.stackabuse.jpa.domain.Student;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static com.fdpro.clients.stackabuse.jpa.domain.Student.*;
import static org.assertj.core.api.Assertions.assertThat;

class FieldMappingUnitTest {

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void closeEntityManager() {
        entityManager.close();
    }

    @Test
    void givenStudent_whenFindEntity_thenFoundAndWellMapped() {
        Student foundStudent = entityManager.find(Student.class, 2L);

        assertThat(foundStudent.id()).isEqualTo(2L);
        assertThat(foundStudent.lastName()).isEqualTo("Doe");
        assertThat(foundStudent.firstName()).isEqualTo("John");
        assertThat(foundStudent.birthDateAsDate()).isEqualTo(DateUtil.parse("2000-02-18"));
        assertThat(foundStudent.birthDateAsLocalDate()).isEqualTo(LocalDate.parse("2000-02-18"));
        assertThat(foundStudent.gender()).isEqualTo(Gender.MALE);
        assertThat(foundStudent.wantsNewsletter()).isTrue();

        Address address = new Address("Baker Street", "221B", "London");
        assertThat(foundStudent.address()).isEqualTo(address);
    }
}
