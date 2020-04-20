package com.fdpro.clients.stackabuse.jpa.basic;

import com.fdpro.clients.stackabuse.jpa.basic.Address;
import com.fdpro.clients.stackabuse.jpa.basic.Student;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class FieldMappingUnitTest {

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-basic");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void closeEntityManager() {
        entityManager.close();
    }

    @Test
    void givenStudent_whenFindEntity_thenFoundAndWellMapped() {
        Student student = entityManager.find(Student.class, 10L);

        assertThat(student.id()).isEqualTo(10L);
        assertThat(student.lastName()).isEqualTo("Doe");
        assertThat(student.firstName()).isEqualTo("John");
        assertThat(student.birthDateAsDate()).isEqualTo(DateUtil.parse("2000-02-18"));
        assertThat(student.birthDateAsLocalDate()).isEqualTo(LocalDate.parse("2000-02-18"));
        assertThat(student.gender()).isEqualTo(Student.Gender.MALE);
        assertThat(student.wantsNewsletter()).isTrue();

        Address address = new Address("Baker Street", "221B", "London");
        assertThat(student.address()).isEqualTo(address);
    }
}
