package com.fdpro.clients.stackabuse.jpa.relationships;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OneToOneRelationshipUnitTest extends RelationshipMappingUnitTest {

    @Test
    void givenCourseWithoutMaterial_whenFindEntity_thenFoundButNoMaterial() {
        Course courseWithoutMaterial = entityManager.find(Course.class, 10L);

        assertThat(courseWithoutMaterial).isNotNull();
        assertThat(courseWithoutMaterial.material()).isNull();
    }

    @Test
    void givenCourseWithMaterial_whenFindEntity_thenFoundWithMaterial() {
        Course courseWithMaterial = entityManager.find(Course.class, 11L);

        assertThat(courseWithMaterial).isNotNull();
        assertThat(courseWithMaterial.material()).isNotNull();
        assertThat(courseWithMaterial.material().url()).isEqualTo("https://example.com/courses/sql101/material");
    }

    @Test
    void givenMaterial_whenFindEntity_thenFoundWithCourse() {
        CourseMaterial courseMaterial = entityManager.find(CourseMaterial.class, 10L);

        assertThat(courseMaterial).isNotNull();
        assertThat(courseMaterial.url()).isEqualTo("https://example.com/courses/sql101/material");
        assertThat(courseMaterial.course()).isNotNull();
        assertThat(courseMaterial.course().title()).isEqualTo("SQL 101");
    }
}
