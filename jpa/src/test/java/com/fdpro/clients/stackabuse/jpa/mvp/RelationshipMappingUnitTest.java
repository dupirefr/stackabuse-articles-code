package com.fdpro.clients.stackabuse.jpa.mvp;

import com.fdpro.clients.stackabuse.jpa.domain.Course;
import com.fdpro.clients.stackabuse.jpa.domain.CourseMaterial;
import com.fdpro.clients.stackabuse.jpa.domain.Student;
import com.fdpro.clients.stackabuse.jpa.domain.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationshipMappingUnitTest {

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManagerAndTransaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Nested
    class OneToManyRelationship {
        @Test
        void givenTeacher_whenFindEntity_thenFoundWithCourses() {
            Teacher foundTeacher = entityManager.find(Teacher.class, 1L);

            assertThat(foundTeacher.id()).isEqualTo(1L);
            assertThat(foundTeacher.lastName()).isEqualTo("Doe");
            assertThat(foundTeacher.firstName()).isEqualTo("Jane");
            assertThat(foundTeacher.courses())
              .extracting(Course::title)
              .containsExactly("Java 101", "SQL 101", "JPA 101");
        }
    }

    @Nested
    class ManyToOneRelationship {
        @Test
        void givenCourse_whenFindEntity_thenFoundWithTeacher() {
            Course foundCourse = entityManager.find(Course.class, 1L);

            assertThat(foundCourse.id()).isEqualTo(1L);
            assertThat(foundCourse.title()).isEqualTo("Java 101");
            assertThat(foundCourse.teacher().lastName()).isEqualTo("Doe");
            assertThat(foundCourse.teacher().firstName()).isEqualTo("Jane");
        }

        @Test
        void givenCourseWithoutTeacherAllowed_whenPersist_thenOkay() {
            Course course = new Course("C# 101");
            entityManager.persist(course);
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
//            entityManager.persist(teacher);

            Course course = new Course("C# 101");
            course.setTeacher(teacher);

            entityManager.persist(course);
        }
    }

    @Nested
    class OneToOneRelationship {
        @Test
        void givenCourseWithoutMaterial_whenFindEntity_thenFoundButNoMaterial() {
            Course courseWithoutMaterial = entityManager.find(Course.class, 30L);

            assertThat(courseWithoutMaterial).isNotNull();
            assertThat(courseWithoutMaterial.material()).isNull();
        }

        @Test
        void givenCourseWithMaterial_whenFindEntity_thenFoundWithMaterial() {
            Course courseWithMaterial = entityManager.find(Course.class, 31L);

            assertThat(courseWithMaterial).isNotNull();
            assertThat(courseWithMaterial.material()).isNotNull();
            assertThat(courseWithMaterial.material().url()).isEqualTo("https://example.com/courses/sql101/material");
        }

        @Test
        void givenMaterial_whenFindEntity_thenFoundWithCourse() {
            CourseMaterial courseMaterial = entityManager.find(CourseMaterial.class, 40L);

            assertThat(courseMaterial).isNotNull();
            assertThat(courseMaterial.url()).isEqualTo("https://example.com/courses/sql101/material");
            assertThat(courseMaterial.course()).isNotNull();
            assertThat(courseMaterial.course().title()).isEqualTo("SQL 101");
        }
    }

    @Nested
    class ManyToMany {
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
            Course courseWithMultipleStudents = entityManager.find(Course.class, 30L);

            assertThat(courseWithMultipleStudents).isNotNull();
            assertThat(courseWithMultipleStudents.students())
              .hasSize(2)
              .extracting(Student::firstName)
              .containsExactly("John", "Will");
        }
    }
}
