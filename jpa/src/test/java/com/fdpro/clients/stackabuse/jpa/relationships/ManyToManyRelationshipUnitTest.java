package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ManyToManyRelationshipUnitTest extends RelationshipMappingUnitTest {

    @Test
    void givenStudentWithMultipleCourses_whenFindEntity_thenFoundWithCourses() {
        Student studentWithMultipleCourses = entityManager.find(Student.class, 10L);

        assertThat(studentWithMultipleCourses).isNotNull();
        assertThat(studentWithMultipleCourses.courses())
          .hasSize(2)
          .extracting(Course::title)
          .containsExactly("Java 101", "SQL 101");
    }

    @Test
    void givenCourseWithMultipleStudents_whenFindEntity_thenFoundWithStudents() {
        Course courseWithMultipleStudents = entityManager.find(Course.class, 10L);

        assertThat(courseWithMultipleStudents).isNotNull();
        assertThat(courseWithMultipleStudents.students())
          .hasSize(2)
          .extracting(Student::firstName)
          .containsExactly("John", "Will");
    }
}
