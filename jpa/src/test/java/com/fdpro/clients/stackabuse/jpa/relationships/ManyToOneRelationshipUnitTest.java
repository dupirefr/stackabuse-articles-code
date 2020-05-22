package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManyToOneRelationshipUnitTest extends RelationshipMappingUnitTest {

    @Test
    void givenCourse_whenFindEntity_thenFoundWithTeacher() {
        Course foundCourse = entityManager.find(Course.class, 10L);

        assertThat(foundCourse.id()).isEqualTo(10L);
        assertThat(foundCourse.title()).isEqualTo("Java 101");
        assertThat(foundCourse.teacher().lastName()).isEqualTo("Doe");
        assertThat(foundCourse.teacher().firstName()).isEqualTo("Jane");
    }

    @Test
    void givenCourseWithoutTeacherAllowed_whenPersist_thenOkay() {
        Course course = new Course("C# 101");

        // Remove comment when optional
        // entityManager.persist(course);
    }

    @Test
    void givenCourseWithoutTeacherNotAllowed_whenPersist_thenException() {
        Course course = new Course("C# 101");
        assertThrows(Exception.class, () -> entityManager.persist(course));
    }

    @Test
    void givenCourseWithTeacher_whenPersist_thenOkay() {
        Teacher teacher = new Teacher();
        teacher.setLastName("Doe");
        teacher.setFirstName("Will");

        // Remove comment when no cascade
        // entityManager.persist(teacher);

        Course course = new Course("C# 101");
        course.setTeacher(teacher);

        entityManager.persist(course);
    }
}
