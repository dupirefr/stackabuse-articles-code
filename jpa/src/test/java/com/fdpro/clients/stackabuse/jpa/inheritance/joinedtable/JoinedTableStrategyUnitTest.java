package com.fdpro.clients.stackabuse.jpa.inheritance.joinedtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static java.time.Month.FEBRUARY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class JoinedTableStrategyUnitTest {

    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-inheritance-joinedtable");
    }

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManagerAndTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void givenStudent_whenFindAsPerson_thenFoundAsPerson() {
        Person foundPerson = entityManager.find(Person.class, 10L);
        
        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.lastName()).isEqualTo("Doe");
        assertThat(foundPerson.firstName()).isEqualTo("John");
        assertThat(foundPerson.birthDate()).isEqualTo(LocalDate.of(2000, FEBRUARY, 18));
        assertThat(foundPerson.gender()).isEqualTo(Person.Gender.MALE);
        assertThat(foundPerson.address().equals(new Address("Baker Street", "221B", "London")));
    }

    @Test
    void givenStudent_whenFindAsStudent_thenFoundAsStudent() {
        Student foundStudent = entityManager.find(Student.class, 10L);

        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.lastName()).isEqualTo("Doe");
        assertThat(foundStudent.firstName()).isEqualTo("John");
        assertThat(foundStudent.birthDate()).isEqualTo(LocalDate.of(2000, FEBRUARY, 18));
        assertThat(foundStudent.gender()).isEqualTo(Person.Gender.MALE);
        assertThat(foundStudent.address()).isEqualTo(new Address("Baker Street", "221B", "London"));
        assertThat(foundStudent.wantsNewsletter()).isTrue();
    }

    @Test
    void givenTeacher_whenFindAsPerson_thenFoundAsPerson() {
        Person foundPerson = entityManager.find(Person.class, 11L);

        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.lastName()).isEqualTo("Doe");
        assertThat(foundPerson.firstName()).isEqualTo("Jane");
        assertThat(foundPerson.birthDate()).isEqualTo(LocalDate.of(1980, OCTOBER, 15));
        assertThat(foundPerson.gender()).isEqualTo(Person.Gender.FEMALE);
        assertThat(foundPerson.address().equals(new Address("Washington Avenue", "23", "Oxford")));
    }

    @Test
    void givenTeacher_whenFindAsTeacher_thenFoundAsTeacher() {
        Teacher foundStudent = entityManager.find(Teacher.class, 11L);

        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.lastName()).isEqualTo("Doe");
        assertThat(foundStudent.firstName()).isEqualTo("Jane");
        assertThat(foundStudent.birthDate()).isEqualTo(LocalDate.of(1980, OCTOBER, 15));
        assertThat(foundStudent.gender()).isEqualTo(Person.Gender.FEMALE);
        assertThat(foundStudent.address()).isEqualTo(new Address("Washington Avenue", "23", "Oxford"));
    }
}
