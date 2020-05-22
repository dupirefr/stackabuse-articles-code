package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static com.fdpro.clients.stackabuse.jpa.relationships.Student.Gender.*;
import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;

class ManyToManyRelationshipUnitTest {

    private EntityManager entityManager;

    private long studentId;
    private long courseId;

    @BeforeEach
    void beforeEach() {
        openEntityManagerAndTransaction();
        dataSet();
    }

    private void openEntityManagerAndTransaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-relationships-many-to-many");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void dataSet() {
        Student studentJohnDoe = studentJohnDoe();
        entityManager.persist(studentJohnDoe);
        studentId = studentJohnDoe.id();

        Student studentWillDoe = studentWillDoe();
        entityManager.persist(studentWillDoe);

        Teacher teacherJaneDoe = teacherJaneDoe();
        entityManager.persist(teacherJaneDoe);

        Course javaCourse = javaCourse(teacherJaneDoe, studentJohnDoe, studentWillDoe);
        entityManager.persist(javaCourse);
        courseId = javaCourse.id();

        Course sqlCourse = sqlCourse(teacherJaneDoe, studentJohnDoe);
        entityManager.persist(sqlCourse);

        entityManager.flush();
        entityManager.clear();
    }

    private Student studentJohnDoe() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setBirthDateAsLocalDate(LocalDate.of(2000, FEBRUARY, 18));
        student.setGender(MALE);
        student.setWantsNewsletter(true);
        student.setAddress(new Address("Baker Street", "221B", "London"));
        return student;
    }

    private Student studentWillDoe() {
        Student student = new Student();
        student.setFirstName("Will");
        student.setLastName("Doe");
        student.setBirthDateAsLocalDate(LocalDate.of(2001, APRIL, 4));
        student.setGender(MALE);
        student.setWantsNewsletter(false);
        student.setAddress(new Address("Washington Avenue", "23", "Oxford"));
        return student;
    }

    private Teacher teacherJaneDoe() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Jane");
        teacher.setLastName("Doe");
        return teacher;
    }

    private Course javaCourse(Teacher teacher, Student... students) {
        Course course = new Course("Java 101");
        course.setTeacher(teacher);
        Arrays.stream(students).forEach(course::addStudent);
        return course;
    }

    private Course sqlCourse(Teacher teacher, Student... students) {
        Course course = new Course("SQL 101");
        course.setTeacher(teacher);
        Arrays.stream(students).forEach(course::addStudent);
        return course;
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void givenStudentWithMultipleCourses_whenFindEntity_thenFoundWithCourses() {
        Student studentWithMultipleCourses = entityManager.find(Student.class, studentId);

        assertThat(studentWithMultipleCourses).isNotNull();
        assertThat(studentWithMultipleCourses.courses())
          .hasSize(2)
          .extracting(Course::title)
          .containsExactly("Java 101", "SQL 101");
    }

    @Test
    void givenCourseWithMultipleStudents_whenFindEntity_thenFoundWithStudents() {
        Course courseWithMultipleStudents = entityManager.find(Course.class, courseId);

        assertThat(courseWithMultipleStudents).isNotNull();
        assertThat(courseWithMultipleStudents.students())
          .hasSize(2)
          .extracting(Student::firstName)
          .containsExactly("John", "Will");
    }
}
