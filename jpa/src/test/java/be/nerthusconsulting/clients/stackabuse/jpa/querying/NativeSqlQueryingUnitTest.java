package be.nerthusconsulting.clients.stackabuse.jpa.querying;

import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NativeSqlQueryingUnitTest {
    private EntityManager entityManager;

    @BeforeEach
    void openEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
          "guide-to-jpa-with-hibernate-querying");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void closeEntityManager() {
        entityManager.close();
    }

    @Test
    void givenSqlQueryString_whenExecutingQueryThroughEntityManager_thenListReturned() {
        Query query = entityManager.createNativeQuery("select * from PERSON where LASTNAME = ?", Person.class);
        query.setParameter(1, "Doe");

        List<Person> people = query.getResultList();

        assertThat(people)
          .hasSize(2)
          .allMatch(person -> person.lastName().equals("Doe"));
    }

    @Test
    void givenSqlQueryString_whenExecutingQueryThroughEntityManagerWithNamedParameter_thenListReturned() {
        Query query = entityManager.createNativeQuery("select * from PERSON where LASTNAME = :lastName", Person.class);
        query.setParameter("lastName", "Doe");

        List<Person> people = query.getResultList();

        assertThat(people)
          .hasSize(2)
          .allMatch(person -> person.lastName().equals("Doe"));
    }

    @Test
    void givenSqlQueryString_whenExecutingProjectionQueryThroughEntityManager_thenListReturned() {
        Query query = entityManager.createNativeQuery("select CITY, STREET, NUMBER from PERSON", Address.class);

        assertThrows(PersistenceException.class, query::getResultList);
    }
}
