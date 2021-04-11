package be.nerthusconsulting.clients.stackabuse.jpa.querying;

import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class OldStyleSqlQueryingUnitTest {
    private EntityManager entityManager;

    @BeforeEach
    void setUpDatabase() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
          "guide-to-jpa-with-hibernate-querying");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void closeEntityManager() {
        entityManager.close();
    }

    @Test
    void givenSqlQueryString_whenExecutingQueryOverJdbc_thenResultSetReturned() throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:default", "user", "password")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from PERSON where LASTNAME = ?")) {
                preparedStatement.setString(1, "Doe");

                List<Person> people = new ArrayList<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Person person = new Person(
                      resultSet.getLong("ID"),
                      resultSet.getString("LASTNAME"),
                      resultSet.getString("FIRST_NAME"),
                      LocalDate.parse(resultSet.getString("BIRTHDATE"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                      Person.Gender.of(resultSet.getString("GENDER")),
                      new Address(
                        resultSet.getString("STREET"),
                        resultSet.getString("NUMBER"),
                        resultSet.getString("CITY")
                      )
                    );

                    people.add(person);
                }

                assertThat(people)
                  .hasSize(2)
                  .allMatch(person -> person.lastName().equals("Doe"));
            }
        }
    }

}
