package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OneToManyRelationshipUnitTest extends RelationshipMappingUnitTest {

    @Test
    void givenTeacher_whenFindEntity_thenFoundWithCourses() {
        Teacher foundTeacher = entityManager.find(Teacher.class, 10L);

        assertThat(foundTeacher.id()).isEqualTo(10L);
        assertThat(foundTeacher.lastName()).isEqualTo("Doe");
        assertThat(foundTeacher.firstName()).isEqualTo("Jane");
        assertThat(foundTeacher.courses())
          .extracting(Course::title)
          .containsExactly("Java 101", "SQL 101", "JPA 101");
    }
}
